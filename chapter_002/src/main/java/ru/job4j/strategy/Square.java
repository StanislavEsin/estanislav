package ru.job4j.strategy;

/**
 * Square.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Square implements Shape {
    /**
     * ask.
     * @return String
     */
    @Override
    public String pic() {
        String separator = System.getProperty("line.separator");
        StringBuilder pic = new StringBuilder();
        pic.append("++++" + separator);
        pic.append("++++" + separator);
        pic.append("++++" + separator);
        pic.append("++++");
        return pic.toString();
    }
}