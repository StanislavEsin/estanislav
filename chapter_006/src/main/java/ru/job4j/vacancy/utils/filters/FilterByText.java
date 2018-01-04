package ru.job4j.vacancy.utils.filters;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * FilterByText.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 09.12.2017
 */
public final class FilterByText {
    private final ConcurrentHashMap<String, ArrayList<String>> map = new ConcurrentHashMap<>();

    public FilterByText addWord(String word) {
        this.map.put(word.toLowerCase(), new ArrayList<>());
        return this;
    }

    public FilterByText addWord(String word, String...exceptionWords) {
        this.map.put(word.toLowerCase(), new ArrayList<>(Arrays.asList(exceptionWords)));
        return this;
    }

    public FilterByText addExceptionWords(String word, String...exceptionWords) {
        ArrayList<String> array = this.map.get(word.toLowerCase());

        if (array == null) {
            array = new ArrayList<>();
        }

        array.addAll(Arrays.asList(exceptionWords));

        return this;
    }

    public FilterByText clearWords() {
        this.map.clear();
        return this;
    }

    public boolean check(String value) {
        boolean result = false;

        if (!value.isEmpty()) {

            result = map.size() == 0;

            String verificationStringTemp = value.toLowerCase();
            for (Map.Entry<String, ArrayList<String>> entryWord : this.map.entrySet()) {
                String word = entryWord.getKey();
                ArrayList<String> exceptionWords = entryWord.getValue();

                boolean completeCycle = false;
                while (!completeCycle) {
                    completeCycle = true;

                    int pvsWord = verificationStringTemp.indexOf(word);
                    if (pvsWord < 0) {
                        continue;
                    }

                    for (int i = 1; i <= exceptionWords.size(); i++) {
                        String exceptionWord = exceptionWords.get(i - 1).toLowerCase();
                        int pvsExceptionWord = verificationStringTemp.indexOf(exceptionWord);
                        if (pvsWord == pvsExceptionWord) {
                            completeCycle = false;
                            verificationStringTemp = verificationStringTemp.substring(
                                    pvsExceptionWord + exceptionWord.length());
                            break;
                        }
                    }

                    result = completeCycle;
                }

                if (result) {
                    break;
                }
            }
        }

        return result;
    }
}