package ru.job4j.mapforbank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Bank.
 *
 * @author Stanislav (376825@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Bank {
    /**
     */
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * addUser.
     * @param user - User
     */
    public void addUser(User user) {
        users.put(user, new ArrayList<>());
    }

    /**
     * deleteUser.
     * @param user - User
     */
    public void deleteUser(User user) {
        users.remove(user);
    }

    /**
     * addAccountToUser.
     * @param user - User
     * @param account - Account
     */
    public void addAccountToUser(User user, Account account) {
        users.get(user).add(account);
    }

    /**
     * deleteAccountFromUser.
     * @param user - User
     * @param account - Account
     */
    public void deleteAccountFromUser(User user, Account account) {
        users.get(user).remove(account);
    }

    /**
     * getUserAccounts.
     * @param user - User
     * @return return - List<Account>
     */
    public List<Account> getUserAccounts(User user) {
        return users.get(user);
    }

    /**
     * transferMoney.
     * @param srcUser - User
     * @param srcAccount - Account
     * @param dstUser - User
     * @param dstAccount - Account
     * @param amount - double
     * @return return - boolean
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean result = false;

        List<Account> tmpSrcAccounts = users.get(srcUser);
        List<Account> tmpDstAccounts = users.get(dstUser);

        if (tmpSrcAccounts != null && tmpDstAccounts != null) {
            if (tmpSrcAccounts.contains(srcAccount) && tmpDstAccounts.contains(dstAccount)) {
                double srcValue = srcAccount.getValue();
                if (srcValue >= amount) {
                    srcAccount.setValue(srcValue - amount);
                    dstAccount.setValue(dstAccount.getValue() + amount);
                    result = true;
                }
            }
        }

        return result;
    }
}