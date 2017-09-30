package ru.job4j.order_book;

import java.util.List;
import java.util.Map;
import java.util.EnumMap;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Order.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 24.09.2017
 */
public class Order {
    private String book;
    private Type type;
    private int id;
    private int volume;
    private float price;

    public Order(String book, Type type, int orderId, int volume, float price) {
        this.book = book;
        this.type = type;
        this.id = orderId;
        this.volume = volume;
        this.price = price;
    }

    public enum Type {
        SELL,
        BUY
    }

    public boolean addInMap(EnumMap<Type, Map<Integer, Order>> map) {
        Map<Integer, Order> mapOrders = map.get(this.type);

        if (!matching(map.get(this.type == Type.BUY ? Type.SELL : Type.BUY))) {
            return false;
        }

        mapOrders.put(this.id, this);

        return true;
    }

    private boolean matching(Map<Integer, Order> map) {
        boolean result = true;

        Set<Map.Entry<Integer, Order>> set = map.entrySet();

        if (set.size() > 100) {
            List<Order> collect = set.stream()
                    .parallel()
                    .filter(s -> this.book.equals(s.getValue().getBook()))
                    .filter(s -> this.price >= s.getValue().getPrice())
                    .map(Map.Entry::getValue)
                    .sorted((o1, o2) -> Float.compare(o1.getPrice(), o2.getPrice()))
                    .collect(Collectors.toList());
        } else {
            List<Order> collect = set.stream()
                    .filter(s -> this.book.equals(s.getValue().getBook()))
                    .filter(s -> this.price >= s.getValue().getPrice())
                    .map(Map.Entry::getValue)
                    .sorted((o1, o2) -> Float.compare(o1.getPrice(), o2.getPrice()))
                    .collect(Collectors.toList());
        }


        return result;
    }

    public boolean removeFromMapWhenParsing(EnumMap<Type, Map<Integer, Order>> map) {
        Map<Integer, Order> mapOrdersBuy = map.get(Type.BUY);
        Map<Integer, Order> mapOrdersSell = map.get(Type.SELL);

        boolean result = mapOrdersBuy.remove(this.id, this);
        result = !result ? mapOrdersSell.remove(this.id, this) : result;

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Order order = (Order) o;

        return id == order.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "book='" + book + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", volume=" + volume +
                ", price=" + price +
                '}';
    }

    public String getBook() {
        return book;
    }

    public Type getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}