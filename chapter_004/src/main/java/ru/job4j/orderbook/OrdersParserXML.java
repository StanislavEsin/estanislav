package ru.job4j.orderbook;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.io.FileInputStream;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * OrdersParserXML.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 26.09.2017
 */
public class OrdersParserXML {
    /**
     * SAXParser.
     * @param path - String
     * @return List<Book>
     */
    public List<Book> saxParser(File path) {
        Map<String, Book> books = new HashMap<>();

        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {
                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    if ("AddOrder".equals(qName) && attributes.getLength() == 5) {
                        Order order = new Order(
                                Order.Type.valueOf(attributes.getValue("operation")),
                                Integer.valueOf(attributes.getValue("orderId")),
                                Integer.valueOf(attributes.getValue("volume")),
                                Float.valueOf(attributes.getValue("price")));

                        addOrder(books, attributes.getValue("book"), order);

                    } else if ("DeleteOrder".equals(qName) && attributes.getLength() == 2) {
                        Order order = new Order(
                                null,
                                Integer.valueOf(attributes.getValue("orderId")),
                                0,
                                0f);

                        deleteOrder(books, attributes.getValue("book"), order);
                    }
                }
            };

            saxParser.parse(path, handler);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(books.values());
    }

    /**
     * staxParser.
     * @param path - String
     * @return List<Book>
     */
    public List<Book> staxParser(File path) {
        Map<String, Book> books = new HashMap<>();

        try {
            XMLStreamReader xmlsr = XMLInputFactory.newInstance().createXMLStreamReader(
                    path.toString(), new FileInputStream(path));

            while (xmlsr.hasNext()) {
                xmlsr.next();

                if (xmlsr.isStartElement()) {
                    if ("AddOrder".equals(xmlsr.getLocalName())) {
                        if (xmlsr.getAttributeCount() != 5) {
                            continue;
                        }

                        Order order = new Order(
                                Order.Type.valueOf(xmlsr.getAttributeValue(1)),
                                Integer.valueOf(xmlsr.getAttributeValue(4)),
                                Integer.valueOf(xmlsr.getAttributeValue(3)),
                                Float.valueOf(xmlsr.getAttributeValue(2)));

                        addOrder(books, xmlsr.getAttributeValue(0), order);

                    } else if ("DeleteOrder".equals(xmlsr.getLocalName())) {
                        if (xmlsr.getAttributeCount() != 2) {
                            continue;
                        }

                        Order order = new Order(
                                null,
                                Integer.valueOf(xmlsr.getAttributeValue(1)),
                                0,
                                0f);

                        deleteOrder(books, xmlsr.getAttributeValue(0), order);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(books.values());
    }

    /**
     * addOrder.
     * @param books - Map<String, Book>
     * @param book - String
     * @param order - Order
     */
    private void addOrder(Map<String, Book> books, String book, Order order) {
        Book bookObject = books.get(book);

        if (bookObject == null) {
            bookObject = new Book(book);
            books.put(book, bookObject);
        }

        bookObject.addOrder(order);
    }

    /**
     * deleteOrder.
     * @param books - Map<String, Book>
     * @param book - String
     * @param order - Order
     */
    private void deleteOrder(Map<String, Book> books, String book, Order order) {
        Book bookObject = books.get(book);

        if (bookObject != null) {
            bookObject.deleteOrder(order);
        }
    }
}