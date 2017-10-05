package ru.job4j.orderbook;

/**
 * Order.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 24.09.2017
 */
public class Order {
    /**
     */
    private Type type;
    /**
     */
    private int id;
    /**
     */
    private int volume;
    /**
     */
    private float price;

    /**
     * Constructor.
     * @param type - Type.
     * @param orderId - int.
     * @param volume - int.
     * @param price - float.
     */
    public Order(Type type, int orderId, int volume, float price) {
        this.type = type;
        this.id = orderId;
        this.volume = volume;
        this.price = price;
    }

    /**
     * Type.
     */
    public enum Type {
        /**
         */
        SELL,
        /**
         */
        BUY
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
        return "Order{" + ", type=" + type
                + ", id=" + id
                + ", volume=" + volume
                + ", price=" + price + '}';
    }

    /**
     * getId.
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * getVolume.
     * @return int
     */
    public int getVolume() {
        return volume;
    }

    /**
     * setVolume.
     * @param volume - int
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * getPrice.
     * @return float
     */
    public float getPrice() {
        return price;
    }

    /**
     * getType.
     * @return Type
     */
    public Type getType() {
        return type;
    }
}