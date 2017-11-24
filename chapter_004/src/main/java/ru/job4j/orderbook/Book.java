package ru.job4j.orderbook;

import java.util.*;

/**
 * Book.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 26.09.2017
 */
public class Book {
    /**
     */
    private String name;
    /**
     */
    private TreeMap<Float, Map<Integer, Order>> buy = new TreeMap<>((o1, o2) -> Float.compare(o2, o1));
    /**
     */
    private TreeMap<Float, Map<Integer, Order>> sell = new TreeMap<>();

    /**
     * Constructor.
     * @param name - String.
     */
    public Book(String name) {
        this.name = name;
    }

    /**
     * addOrder.
     * @param order - Order
     * @return boolean
     */
    public boolean addOrder(Order order) {
        boolean result = true;

        if (!matching(order)) {
            result = false;
        } else {
            putInRepository(order.getType() == Order.Type.BUY ? buy : sell, order);
        }

        return result;
    }

    /**
     * matching.
     * @param order - Order
     * @return boolean
     */
    private boolean matching(Order order) {
        boolean result = true;

        TreeMap<Float, Map<Integer, Order>> mapForMatching = order.getType() == Order.Type.BUY ? sell : buy;

        Iterator<Float> iteratorMapForMatching = mapForMatching.keySet().iterator();
        while (order.getVolume() != 0 && iteratorMapForMatching.hasNext()) {
            Float keyPrice = iteratorMapForMatching.next();

            if ((order.getType() == Order.Type.BUY && order.getPrice() < keyPrice)
                    || (order.getType() == Order.Type.SELL && order.getPrice() > keyPrice)) {
                break;
            }

            Map<Integer, Order> mapOrders = mapForMatching.get(keyPrice);
            Iterator<Order> iteratorMapOrders = mapOrders.values().iterator();
            while (order.getVolume() != 0 && iteratorMapOrders.hasNext()) {
                Order orderInMap = iteratorMapOrders.next();

                if (order.getVolume() > orderInMap.getVolume()) {
                    order.setVolume(order.getVolume() - orderInMap.getVolume());
                    iteratorMapOrders.remove();
                } else if (order.getVolume() < orderInMap.getVolume()) {
                    orderInMap.setVolume(orderInMap.getVolume() - order.getVolume());
                    order.setVolume(0);
                } else {
                    order.setVolume(0);
                    iteratorMapOrders.remove();
                }
            }

            if (mapOrders.size() == 0) {
                iteratorMapForMatching.remove();
            }
        }

        if (order.getVolume() == 0) {
            result = false;
        }

        return result;
    }

    /**
     * putInRepository.
     * @param map - TreeMap<Float, Map<Integer, Order>>
     * @param order - Order
     * @return boolean
     */
    private boolean putInRepository(TreeMap<Float, Map<Integer, Order>> map, Order order) {
        Map<Integer, Order> mapOrders = map.get(order.getPrice());

        if (mapOrders == null) {
            mapOrders = new HashMap<>();
            mapOrders.put(order.getId(), order);
            map.put(order.getPrice(), mapOrders);
        } else {
            mapOrders.put(order.getId(), order);
        }

        return true;
    }

    /**
     * deleteOrder.
     * @param order - Order
     * @return boolean
     */
    public boolean deleteOrder(Order order) {
        boolean result = false;

        Iterator<Map<Integer, Order>> iteratorOrders = buy.values().iterator();
        while (iteratorOrders.hasNext()) {
            Map<Integer, Order> orders = iteratorOrders.next();

            if (orders.remove(order.getId()) != null) {
                if (orders.size() == 0) {
                    iteratorOrders.remove();
                }

                result = true;
                break;
            }
        }

        if (!result) {
            iteratorOrders = sell.values().iterator();
            while (iteratorOrders.hasNext()) {
                Map<Integer, Order> orders = iteratorOrders.next();

                if (orders.remove(order.getId()) != null) {
                    if (orders.size() == 0) {
                        iteratorOrders.remove();
                    }

                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    /**
     * printOrderBook.
     */
    public void printOrderBook() {
        System.out.println("");
        System.out.printf("     Order book: %s", this.name);
        System.out.println("");
        System.out.println("_____________________________");
        System.out.println("Volume@Price  |  Volume@Price");

        Iterator<Map.Entry<Float, Map<Integer, Order>>> iteratorBuy = buy.entrySet().iterator();
        Iterator<Map.Entry<Float, Map<Integer, Order>>> iteratorSell = sell.entrySet().iterator();

        boolean iteratorBuyHasNext = iteratorBuy.hasNext();
        boolean iteratorSellHasNext = iteratorSell.hasNext();

        while (iteratorBuyHasNext || iteratorSellHasNext) {
            int buyVolume = 0; float buyPrice = 0;
            int sellVolume = 0; float sellPrice = 0;

            Map.Entry<Float, Map<Integer, Order>> entryBuy;
            if (iteratorBuyHasNext) {
                entryBuy = iteratorBuy.next();
                Map<Integer, Order> ordersBuy = entryBuy.getValue();

                for (Order order : ordersBuy.values()) {
                    buyVolume += order.getVolume();
                }

                buyPrice = entryBuy.getKey();

                iteratorBuyHasNext = iteratorBuy.hasNext();
            }

            Map.Entry<Float, Map<Integer, Order>> entrySell;
            if (iteratorSellHasNext) {
                entrySell = iteratorSell.next();
                Map<Integer, Order> ordersSell = entrySell.getValue();

                for (Order order : ordersSell.values()) {
                    sellVolume += order.getVolume();
                }

                sellPrice = entrySell.getKey();

                iteratorSellHasNext = iteratorSell.hasNext();
            }

            Formatter formatterString = new Formatter();
            formatterString.format(Locale.ENGLISH, buyVolume == 0 ? "------------- - " : "%4d@%-8.2f - ", buyVolume, buyPrice);
            formatterString.format(Locale.ENGLISH, sellVolume == 0 ? "-------------" : "%4d@%-8.2f", sellVolume, sellPrice);

            System.out.println(formatterString);
        }
    }

    /**
     * getBuyOrders.
     * @return TreeMap<Float, Map<Integer, Order>>
     */
    public TreeMap<Float, Map<Integer, Order>> getBuyOrders() {
        return buy;
    }

    /**
     * getSellOrders.
     * @return TreeMap<Float, Map<Integer, Order>>
     */
    public TreeMap<Float, Map<Integer, Order>> getSellOrders() {
        return sell;
    }
}