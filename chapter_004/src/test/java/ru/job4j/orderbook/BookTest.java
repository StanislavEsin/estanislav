package ru.job4j.orderbook;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.nullValue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 03.10.2017
 */
public class BookTest {
    /**
     * Test add.
     */
    @Test
    public void whenAddBuyOrderThenBuyMapContainsIt() {
        Book book = new Book("book1");
        Order order = new Order(Order.Type.BUY, 1, 1, 1.0f);
        book.addOrder(order);

        Order result = book.getBuyOrders().get(1.0f).get(1);

        assertThat(result, is(order));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddSellOrderThenSellMapContainsIt() {
        Book book = new Book("book1");
        Order order = new Order(Order.Type.SELL, 1, 1, 1.0f);
        book.addOrder(order);

        Order result = book.getSellOrders().get(1.0f).get(1);

        assertThat(result, is(order));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddOrderWhoseVolumeIsMoreOfOppositeOrderThenDecreaseVolumeOfAddedOrderOnVolumeOfOpposite() {
        Book book = new Book("book1");
        Order sellOrder = new Order(Order.Type.SELL, 1, 4, 40.0f);
        book.addOrder(sellOrder);

        Order buyOrder = new Order(Order.Type.BUY, 2, 10, 50.0f);
        book.addOrder(buyOrder);
        int result = book.getBuyOrders().get(50.0f).get(2).getVolume();

        assertThat(result, is(6));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddOrderWhoseVolumeIsMoreOfOppositeOrderThenDeleteOppositeOrder() {
        Book book = new Book("book1");
        Order sellOrder = new Order(Order.Type.SELL, 1, 4,  40.0f);
        book.addOrder(sellOrder);

        Order buyOrder = new Order(Order.Type.BUY, 2, 10,  50.0f);
        book.addOrder(buyOrder);

        assertThat(book.getSellOrders().get(40.0f), is(nullValue()));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddOrderVolumeOfWhichLessOppositeOrderThenWeDecreaseVolumeOfOppositeOrderByVolumeOfAddedOrder() {
        Book book = new Book("book1");
        Order sellOrder = new Order(Order.Type.SELL, 1, 15, 40.0f);
        book.addOrder(sellOrder);

        Order buyOrder = new Order(Order.Type.BUY, 2, 10, 50.0f);
        book.addOrder(buyOrder);
        int result = book.getSellOrders().get(40.0f).get(1).getVolume();

        assertThat(result, is(5));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddOrderVolumeOfWhichLessOppositeOrderThenDoNotAddOrder() {
        Book book = new Book("book1");
        Order sellOrder = new Order(Order.Type.SELL, 1, 15, 40.0f);
        book.addOrder(sellOrder);

        Order buyOrder = new Order(Order.Type.BUY, 2, 10, 50.0f);
        book.addOrder(buyOrder);

        assertThat(book.getBuyOrders().get(50.0f), is(nullValue()));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddOrderWhoseVolumeEqualToOppositeOrderThenDeleteOpposite() {
        Book book = new Book("book1");
        Order sellOrder = new Order(Order.Type.SELL, 1, 10, 40.0f);
        book.addOrder(sellOrder);

        Order buyOrder = new Order(Order.Type.BUY, 2, 10, 50.0f);
        book.addOrder(buyOrder);

        assertThat(book.getBuyOrders().get(50.0f), is(nullValue()));
    }

    /**
     * Test add.
     */
    @Test
    public void whenAddOrderWhoseVolumeEqualToOppositeOrderThenDoNotAddOrder() {
        Book book = new Book("book1");
        Order sellOrder = new Order(Order.Type.SELL, 1, 10, 40.0f);
        book.addOrder(sellOrder);

        Order buyOrder = new Order(Order.Type.BUY, 2, 10, 50.0f);
        book.addOrder(buyOrder);

        assertThat(book.getSellOrders().get(40.0f), is(nullValue()));
    }

    /**
     * Test add.
     */
    @Test
    public void whenDeleteBuyOrderThenBuyMapDoNotContainsIt() {
        Book book = new Book("book1");
        Order buyOrder = new Order(Order.Type.BUY, 1, 10, 40.0f);
        book.addOrder(buyOrder);

        book.deleteOrder(buyOrder);

        assertThat(book.getBuyOrders().get(40.0f), is(nullValue()));
    }

    /**
     * Test add.
     */
    @Test
    public void whenDeleteSellOrderThenSellMapDoNotContainsIt() {
        Book book = new Book("book1");
        Order sellOrder = new Order(Order.Type.SELL, 1, 10, 40.0f);
        book.addOrder(sellOrder);

        book.deleteOrder(sellOrder);

        assertThat(book.getSellOrders().get(40.0f), is(nullValue()));
    }

    /**
     * Test add.
     */
    @Test
    public void whenPrintThenPrintOrderBook() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Book book = new Book("book1");
        book.addOrder(new Order(Order.Type.BUY, 1, 10, 10.0f));
        book.addOrder(new Order(Order.Type.SELL, 2, 5, 20.0f));
        book.addOrder(new Order(Order.Type.BUY, 3, 3, 30.0f));
        book.addOrder(new Order(Order.Type.SELL, 4, 12, 40.0f));
        book.addOrder(new Order(Order.Type.BUY, 5, 7, 50.0f));
        book.addOrder(new Order(Order.Type.SELL, 6, 20, 60.0f));
        book.addOrder(new Order(Order.Type.BUY, 7, 4, 70.0f));
        book.addOrder(new Order(Order.Type.SELL, 8, 2, 80.0f));

        book.printOrderBook();

        assertThat(out.toString(), is(System.lineSeparator()
                                            + "     Order book: book1" + System.lineSeparator()
                                            + "_____________________________" + System.lineSeparator()
                                            + "Volume@Price  |  Volume@Price" + System.lineSeparator()
                                            + "  10@10,00    -    3@40,00   " + System.lineSeparator()
                                            + "------------- -   20@60,00   " + System.lineSeparator()
                                            + "------------- -    2@80,00   " + System.lineSeparator()));
    }
}