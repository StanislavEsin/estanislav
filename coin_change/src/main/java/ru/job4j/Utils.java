package ru.job4j;

/**
 * Utils.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 1.0
 */
public class Utils {
    /**
     * changeMoneyForSmallCoins.
     * @param coins - int[].
     * @param money - int.
     * @return int.
     */
    public int changeMoneyForSmallCoins(int[] coins, int money) {
        if (money <= 2) {
            return 1;
        }

        int[] cache = new int[money + 1];

        cache[0] = 1;
        cache[1] = 1;
        cache[2] = 1;

        for (int i = 3; i <= money; i++) {
            cache[i] = 0;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] > i) {
                    break;
                }

                cache[i] += cache[i - coins[j]];
            }
            System.out.println("");
        }

        return cache[money];
    }
}