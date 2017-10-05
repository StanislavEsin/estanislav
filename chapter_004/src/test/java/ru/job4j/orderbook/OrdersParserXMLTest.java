package ru.job4j.orderbook;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.util.List;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 05.10.2017
 */
public class OrdersParserXMLTest {
    /**
     * Test add.
     */
    @Test(timeout = 2160000)
    public void whenSaxParseThenNotMoreThanSixMinutes() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        OrdersParserXML parserXML = new OrdersParserXML();
        List<Book> books = parserXML.saxParser(new File("orders.xml"));
        books.forEach(Book::printOrderBook);
    }

    /**
     * Test add.
     */
    @Test(timeout = 2160000)
    public void whenStaxParseThenNotMoreThanSixMinutes() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        OrdersParserXML parserXML = new OrdersParserXML();
        List<Book> books = parserXML.staxParser(new File("orders.xml"));
        books.forEach(Book::printOrderBook);
    }
}