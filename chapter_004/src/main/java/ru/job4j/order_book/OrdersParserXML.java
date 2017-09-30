package ru.job4j.order_book;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import java.io.IOException;

import java.util.Map;
import java.util.EnumMap;
import java.util.HashMap;

/**
 * OrdersParserXML.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 26.09.2017
 */
public class OrdersParserXML {
    private EnumMap<Order.Type, Map<Integer, Order>> map = new EnumMap<>(Order.Type.class);

    public OrdersParserXML() {
        this.map = new EnumMap<>(Order.Type.class);
        this.map.put(Order.Type.BUY, new HashMap<>());
        this.map.put(Order.Type.SELL, new HashMap<>());
    }

    public void SAXParser() {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    if ("AddOrder".equals(qName) && attributes.getLength() == 5) {
                        Order order = new Order(attributes.getValue("book"),
                                Order.Type.valueOf(attributes.getValue("operation")),
                                Integer.valueOf(attributes.getValue("orderId")),
                                Integer.valueOf(attributes.getValue("volume")),
                                Float.valueOf(attributes.getValue("price")));

                        order.addInMap(map);
                    } else if ("DeleteOrder".equals(qName) && attributes.getLength() == 2) {
                        Order order = new Order(attributes.getValue("book"),
                                null,
                                Integer.valueOf(attributes.getValue("orderId")),
                                0,
                                0f);

                        order.removeFromMapWhenParsing(map);
                    }
                }
            };

            saxParser.parse("orders.xml", handler);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EnumMap<Order.Type, Map<Integer, Order>> getMap() {
        return map;
    }
}