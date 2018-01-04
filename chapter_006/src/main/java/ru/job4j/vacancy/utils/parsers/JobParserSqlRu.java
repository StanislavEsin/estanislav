package ru.job4j.vacancy.utils.parsers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.job4j.vacancy.model.Vacancy;
import ru.job4j.vacancy.utils.filters.FilterByDate;
import ru.job4j.vacancy.utils.filters.FilterByText;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * JobParserSqlRu.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 08.12.2017
 */
public final class JobParserSqlRu extends JobParser {
    private static final Logger LOG = LoggerFactory.getLogger(JobParserSqlRu.class);
    private final ConcurrentHashMap<String, Document> cache = new ConcurrentHashMap<>();

    public JobParserSqlRu() {
        super("http://www.sql.ru/forum/actualsearch.aspx?search=java&sin=1&bid=66&a=&ma=0&dt=-1&s=4&so=1&pg=%s");
    }

    public JobParserSqlRu(FilterByDate filterByDate, FilterByText filterByText) {
        super("http://www.sql.ru/forum/actualsearch.aspx?search=java&sin=1&bid=66&a=&ma=0&dt=-1&s=4&so=1&pg=%s");
        this.filterByDate = filterByDate;
        this.filterByText = filterByText;
    }

    private Document getCachedDocument(String url) throws IOException {
        Document result = this.cache.get(url);

        if (result == null) {
            result = Jsoup.connect(url).get();
            this.cache.put(url, result);
        }

        return result;
    }

    @Override
    public void parse(LinkedBlockingQueue<Vacancy> container) {
        ArrayDeque<String> links = new ArrayDeque<>();

        try {
            ExecutorService pool = Executors.newFixedThreadPool(
                    Runtime.getRuntime().availableProcessors() / 2);

            int maxNumberUrl = getMaxNumberUrl(String.format(this.url, 1));
            for (int i = 1; i <= maxNumberUrl; i++) {
                String lastTopicLink = getLastTopicLink(String.format(this.url, i));
                LocalDateTime lastTopicTimeCreate = getTimeCreate(lastTopicLink);

                links.push(String.format(this.url, i));

                if (this.filterByDate != null && !this.filterByDate.check(lastTopicTimeCreate)) {
                    break;
                }
            }

            while (links.peek() != null) {
                pool.execute(new InnerParser(links.pop(), container));
            }
        } catch (IOException e) {
            LOG.error("No URL is available. Problems with the Internet or with the site.", e);
        }
    }

    private int getMaxNumberUrl(String url) throws IOException {
        Document doc = getCachedDocument(url);
        return Integer.valueOf(doc.select(".forumtable_results").get(1).selectFirst("a:last-of-type").text());
    }

    private String getLastTopicLink(String url) throws IOException {
        Document doc = getCachedDocument(url);
        return doc.selectFirst(".forumTable tr:last-of-type").
                selectFirst(".postslisttopic a[href]:first-child").
                attr("href");
    }

    private LocalDateTime getTimeCreate(String url) throws IOException {
        Document doc = getCachedDocument(url);

        String textFooter = doc.selectFirst(".msgFooter").text();

        StringBuilder builder = new StringBuilder();

        int indexFirstOccurrence = textFooter.indexOf("сегодня");

        if (indexFirstOccurrence >= 0) {
            builder.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("d MMM yy"))).
                    append(textFooter.substring(textFooter.indexOf(","), textFooter.indexOf("[")).trim());
        } else {
            builder.append(textFooter.substring(0, textFooter.indexOf("[")).trim());
        }

        int indexNotCorrectFormat = builder.indexOf("май");
        if (indexNotCorrectFormat > 0) {
            builder.replace(builder.indexOf("май"), indexNotCorrectFormat + 3, "мая");
        }

        return LocalDateTime.parse(builder, DateTimeFormatter.ofPattern("d MMM yy, HH:mm"));
    }

    private class InnerParser implements Runnable {
        private final String url;
        private final LinkedBlockingQueue<Vacancy> container;

        private InnerParser(String url, LinkedBlockingQueue<Vacancy> container) {
            this.url = url;
            this.container = container;
        }

        @Override
        public void run() {
            try {
                parseDoc(this.url);
            } catch (IOException e) {
                LOG.error("No URL is available. Problems with the Internet or with the site.", e);
                Thread.currentThread().interrupt();
            } catch (InterruptedException e) {
                LOG.info("The thread was interrupted.", e);
                Thread.currentThread().interrupt();
            }
        }

        private void parseDoc(String url) throws IOException, InterruptedException {
            Elements rowsTable = getRowsForumTable(url);

            for (Element row : rowsTable) {
                String topicLink = getTopicLink(row);
                String topicName = getTopicName(row);

                if (topicLink.isEmpty()) {
                    continue;
                }

                if (filterByText != null && !filterByText.check(topicName)) {
                    continue;
                }

                LocalDateTime timeCreate = getTimeCreate(topicLink);

                if (filterByDate != null && !filterByDate.check(timeCreate)) {
                    break;
                }

                String topicText = getTopicText(topicLink);

                Vacancy vacancy = new Vacancy();
                vacancy.setTimeCreate(timeCreate);
                vacancy.setLink(topicLink);
                vacancy.setName(topicName);
                vacancy.setText(topicText);

                this.container.put(vacancy);
            }
        }

        private Elements getRowsForumTable(String url) throws IOException {
            Document doc = getCachedDocument(url);
            return doc.selectFirst(".forumTable tbody").select("tr");
        }

        private String getTopicText(String url) throws IOException {
            Document doc = getCachedDocument(url);
            return doc.selectFirst(".msgTable").select(".msgBody").get(1).text();
        }

        private String getTopicName(Element row) {
            return row.select(".postslisttopic > a[href]:first-child").text().trim();
        }

        private String getTopicLink(Element row) {
            StringBuilder result = new StringBuilder();
            String endStringLink = row.select(".postslisttopic > a[href]:last-child").attr("href");

            if (!endStringLink.isEmpty()) {
                result.append("http://www.sql.ru/forum/").append(endStringLink);
            }

            return result.toString();
        }
    }
}