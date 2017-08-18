package ru.job4j;

import java.util.Scanner;

/**
 * ConsoleInput - используется для ввода пользовательских данных из консоли.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class ConsoleInput implements Input {
    @Override
    public String ask(String question) {
        System.out.print(question);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}
