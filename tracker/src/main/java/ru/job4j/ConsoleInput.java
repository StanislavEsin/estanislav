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
    /**
     * ask.
     *
     * @param question - String
     * @return String
     */
    @Override
    public String ask(String question) {
        System.out.print(question);
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * ask.
     *
     * @param question - String
     * @param range    - int[]
     * @return int
     */
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask("question"));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }

        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
    }
}