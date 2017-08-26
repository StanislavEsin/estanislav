package ru.job4j;

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
public class UtilsTest {
    /**
     * Test add.
     */
    @Test
    public void whenHaveMoneyThenReturnTheNumberOfWaysChangeMoney() {
        Utils utils = new Utils();
        int[] coins = {1, 3, 5, 10};
        int money = 7;

        int result = utils.changeMoneyForSmallCoins(coins, money);

        int expected = 12;
        assertThat(result, is(expected));
    }
}