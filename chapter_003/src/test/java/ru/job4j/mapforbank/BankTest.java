package ru.job4j.mapforbank;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class BankTest {
    /**
     * Test add.
     */
    @Test
    public void whenTransferMoneyThenWithAccountOfSourceOfCalculationOfReceiverComes() {
        User userA = new User("TestUserA", "0108R954");
        Account accountA = new Account(500, "21043435986");
        User userB = new User("TestUserB", "0408R634");
        Account accountB = new Account(200, "42073985986");
        Bank bank = new Bank();
        bank.addUser(userA);
        bank.addAccountToUser(userA, accountA);
        bank.addUser(userB);
        bank.addAccountToUser(userB, accountB);

        bank.transferMoney(userA, accountA, userB, accountB, 400);
        double result = accountB.getValue();

        double expected = 600;
        assertThat(result, is(expected));
    }
}