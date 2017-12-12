package ru.job4j.xml_xslt_jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

/**
 * DatabasePostgreSQL - реализация базы данных Postgre SQL.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 01.12.2017
 */
public class DatabasePostgreSQL implements Database, Serializable {
    private transient static final Logger LOG = LoggerFactory.getLogger(DatabasePostgreSQL.class);
    private static final long serialVersionUID = 817138107667187883L;
    private String nameDB;
    private String username;
    private String password;
    private int numberRecords;

    @Override
    public void run() {
        if (checkSettings()) {
            String url = String.format("jdbc:postgresql://127.0.0.1:5432/%s", this.nameDB);

            try (Connection connection = DriverManager.getConnection(url, this.username, this.password)) {
                LOG.info("Connected to the database {}", this.nameDB);

                createData(connection);
                toXML(connection);
                transform();
                parsing();

            } catch (SQLException | IOException | XMLStreamException
                    | TransformerException | ParserConfigurationException
                    | SAXException | XPathExpressionException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * checkSettings - проверка заполнения свойств.
     * @return boolean
     */
    private boolean checkSettings() {
        boolean result = true;

        if (this.nameDB == null || this.nameDB.isEmpty()) {
            result = false;
            LOG.error("The name of the information database is not specified");
        }

        if (this.numberRecords < 1) {
            result = false;
            LOG.error("Not specified N-number of records");
        }

        return result;
    }

    /**
     * createData - добавляет данные в базу SQL.
     * @param con - Connection к базе.
     */
    private void createData(Connection con) throws SQLException {
        try {
            con.setAutoCommit(false);

            Statement statement = con.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS public.test ("
                    + "id serial PRIMARY KEY, field integer UNIQUE NOT NULL)");

            ResultSet rs = statement.executeQuery("SELECT id FROM public.test LIMIT 1");
            if (rs.next()) {
                statement.execute("DELETE FROM public.test");
                LOG.info("Cleared the table in the database {}", this.nameDB);
            }

            rs.close();
            statement.close();

            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO public.test (field) VALUES (?)");
            for (int i = 1; i <= this.numberRecords; i++) {
                preparedStatement.setInt(1, i);
                preparedStatement.addBatch();
            }
            int numberInsert = preparedStatement.executeBatch().length;
            preparedStatement.close();

            con.commit();
            con.setAutoCommit(true);

            LOG.info("In the table was recorded {} rows", numberInsert);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            con.rollback();
            con.setAutoCommit(true);
        }
    }

    /**
     * toXML - преобразует данные в формат XML и сохраняет в "1.xml".
     * @param con - Connection к базе.
     */
    private void toXML(Connection con) throws SQLException, IOException, XMLStreamException {
        ArrayList<Integer> data = getData(con);

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter("1.xml"));
        writer.writeStartDocument();
        writer.writeStartElement("entries");

        for (Integer value : data) {
            writer.writeStartElement("entry");
            writer.writeStartElement("field");
            writer.writeCharacters(String.valueOf(value));
            writer.writeEndElement();
            writer.writeEndElement();
        }

        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
        writer.close();
    }

    /**
     * getData - получает данные с базы SQL.
     * @param con - Connection к базе.
     * @return ArrayList<Integer>
     */
    private ArrayList<Integer> getData(Connection con) throws SQLException {
        ArrayList<Integer> result = new ArrayList<>();

        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("SELECT field FROM public.test");

        while (rs.next()) {
            result.add(rs.getInt("field"));
        }

        rs.close();
        statement.close();

        return result;
    }

    /**
     * transform - Посредством XSLT, приложение преобразует содержимое "1.xml".
     * Новый документ сохраняется в файловую систему как "2.xml" в папке с программой.
     */
    private void transform() throws TransformerException, XMLStreamException, IOException {
        createXSL();

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer(
                new StreamSource(System.getProperty("java.io.tmpdir") + "template.xsl"));
        transformer.transform(new StreamSource("1.xml"), new StreamResult("2.xml"));
        LOG.info("The transformation by means of XSLT was successful");
    }

    /**
     * createXSL - Во временной папке создаются правила преобразования XSLT.
     */
    private void createXSL() throws XMLStreamException, IOException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(
                new FileWriter(System.getProperty("java.io.tmpdir") + "template.xsl"));
        writer.writeStartDocument();
        writer.setPrefix("xsl", "http://www.w3.org/1999/XSL/Transform");
        writer.setDefaultNamespace("http://www.w3.org/1999/XSL/Transform");
        writer.writeStartElement("xsl", "stylesheet", "http://www.w3.org/1999/XSL/Transform");
        writer.writeAttribute("version", "1.0");
        writer.writeNamespace("xsl", "http://www.w3.org/1999/XSL/Transform");

        writer.writeEmptyElement("xsl", "output", "http://www.w3.org/1999/XSL/Transform");
        writer.writeAttribute("method", "xml");
        writer.writeAttribute("version", "1.0");

        writer.writeStartElement("xsl", "template", "http://www.w3.org/1999/XSL/Transform");
        writer.writeAttribute("match", "/");

        writer.writeStartElement("entries");

        writer.writeEmptyElement("xsl", "apply-templates", "http://www.w3.org/1999/XSL/Transform");
        writer.writeAttribute("select", "entries/entry/field");

        writer.writeEndElement();
        writer.writeEndElement();

        writer.writeStartElement("xsl", "template", "http://www.w3.org/1999/XSL/Transform");
        writer.writeAttribute("match", "field");

        writer.writeStartElement("xsl", "element", "http://www.w3.org/1999/XSL/Transform");
        writer.writeAttribute("name", "entry");

        writer.writeStartElement("xsl", "attribute", "http://www.w3.org/1999/XSL/Transform");
        writer.writeAttribute("name", "field");

        writer.writeEmptyElement("xsl", "value-of", "http://www.w3.org/1999/XSL/Transform");
        writer.writeAttribute("select", ".");

        writer.writeEndElement();
        writer.writeEndElement();
        writer.writeEndElement();
        writer.writeEndDocument();
        writer.flush();
        writer.close();

        LOG.info("Temporary XSLT for conversion created");
    }

    /**
     * parsing - Парсит файл "1.xml" и выводит в консоль арифметическую сумму значений всех атрибутов field.
     */
    private void parsing() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = documentBuilder.parse("2.xml");
        XPathFactory pathFactory = XPathFactory.newInstance();
        XPath xPath = pathFactory.newXPath();
        XPathExpression expr = xPath.compile("//entry");

        int sum = 0;
        NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodes.getLength(); i++) {
            sum += Integer.valueOf(nodes.item(i).getAttributes().getNamedItem("field").getNodeValue());
        }

        System.out.println(sum);
    }

    public void setNameDB(String nameDB) {
        this.nameDB = nameDB;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNumberRecords(int numberRecords) {
        this.numberRecords = numberRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DatabasePostgreSQL databasePostgreSQL = (DatabasePostgreSQL) o;

        if (nameDB != null ? !nameDB.equals(databasePostgreSQL.nameDB) : databasePostgreSQL.nameDB != null) {
            return false;
        }
        if (username != null ? !username.equals(databasePostgreSQL.username) : databasePostgreSQL.username != null) {
            return false;
        }
        return password != null ? password.equals(databasePostgreSQL.password) : databasePostgreSQL.password == null;
    }

    @Override
    public int hashCode() {
        int result = nameDB != null ? nameDB.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}