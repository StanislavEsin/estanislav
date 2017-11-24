package ru.job4j.monitore;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 22.10.2017
 */
public class ParallerSearchTest {
    /**
     * Test.
     */
    @Test
    @Ignore("demonstration")
    public void demonstration() {
        List<String> fileFormat = new ArrayList<>();
        fileFormat.add("txt");

        try {
            List<String> listFileEntries = new ParallerSearch("D:\\", "abhor", fileFormat).search();
            System.out.println(listFileEntries);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}