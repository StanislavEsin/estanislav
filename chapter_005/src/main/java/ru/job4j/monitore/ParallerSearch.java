package ru.job4j.monitore;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import java.util.stream.Stream;

/**
 * ParallerSearch - It traverses the file system and searches for the specified text in the file system.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 22.10.2017
 */
public class ParallerSearch {
    /**
     * Path to the folder from where you need to search.
     */
    private final String root;
    /**
     * Specified text.
     */
    private final String text;
    /**
     * Extensions of files in which you need to do a search.
     */
    private final List<String> exts;

    /**
     * Constructor.
     * @param root - String
     * @param text - String
     * @param exts - List<String>
     */
    public ParallerSearch(final String root, final String text, final List<String> exts) {
        this.root = root;
        this.text = text;
        this.exts = exts;
        Collections.sort(exts);
    }

    /**
     * search.
     * @throws IOException - IOException.
     * @return List<String> - list of all files in which there is a specified text.
     */
    public List<String> search() throws IOException {
        if (!new File(this.root).exists()) {
            throw new FileNotFoundException();
        }

        FilesProcess processFiles = new FilesProcess();
        Files.walkFileTree(Paths.get(this.root), processFiles);
        List<Future<String>> listFuture = processFiles.getListFuture();

        List<String> result = new ArrayList<>();

        for (Future future : listFuture) {
            try {
                String futureGet = (String) future.get();
                if (futureGet != null) {
                    result.add(futureGet);
                }
            } catch (InterruptedException | ExecutionException e) {
               continue;
            }
        }

        return result;
    }

    /**
     * FilesProcess.
     */
    private class FilesProcess extends SimpleFileVisitor<Path> {
        /**
         */
        private final List<Future<String>> listFuture = new ArrayList<>();
        /**
         */
        private final ExecutorService executor = Executors.newCachedThreadPool();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (checkFileFormat(file)) {
                Future<String> future = executor.submit(new SearchInFile(file.toString()));
                listFuture.add(future);
            }

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            return FileVisitResult.CONTINUE;
        }

        /**
         * @param file - Path.
         * @return boolean.
         */
        private boolean checkFileFormat(Path file) {
            boolean result = false;

            int index = file.toString().lastIndexOf('.');
            if (index != -1) {
                if (Collections.binarySearch(ParallerSearch.this.exts, file.toString().substring(index + 1)) >= 0) {
                    result = true;
                }
            }

            return result;
        }

        /**
         * @return listFuture - List<Future<String>>.
         */
        List<Future<String>> getListFuture() {
            return this.listFuture;
        }
    }

    /**
     * SearchInFile.
     */
    private class SearchInFile implements Callable<String> {
        /**
         */
        private final String file;

        /**
         * Constructor.
         * @param file - File
         */
        SearchInFile(final String file) {
            this.file = file;
        }

        @Override
        public String call() throws Exception {
            String result = null;

            try (Stream<String> stream = Files.lines(Paths.get(this.file))) {
                if (stream.filter(ParallerSearch.this.text::equals).count() > 0) {
                    result = this.file;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    }
}