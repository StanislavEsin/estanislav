package ru.job4j.crudServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.crudServlet.dao.DAOException;
import ru.job4j.crudServlet.dao.UserStore;
import ru.job4j.crudServlet.model.User;

import java.time.LocalDateTime;

/**
 * Main.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 06.01.2018
 */
public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws DAOException, InterruptedException {
        User userTest_1 = new User();
        userTest_1.setLogin("login_1");
        userTest_1.setName("name_1");
        userTest_1.setEmail("email_1");
        userTest_1.setCreateDate(LocalDateTime.now());

//        UserStore store = UserStore.getInstance();


        for (int i = 6; i < 20; i++) {
            int f = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    User user = new User();
                    user.setName("f_" + f);
                    user.setLogin("f_" + f);
                    user.setEmail("f_" + f);
                    user.setCreateDate(LocalDateTime.now());
                    UserStore store = UserStore.getInstance();
                    try {
                        store.createUser(user);
                    } catch (DAOException e) {
                        System.out.println(e);
                    }
                }
            }).start();
        }



//        try {
//            store.createUser(userTest_1);
//        } catch (DAOException e) {
//            System.out.println(e);
//        }

//        try {
//            System.out.println(store.readUserById(userTest_1.getId()));
//        } catch (DAOException e) {
//            System.out.println(e);
//        }
//
//        try {
//            System.out.println(store.getUserByName(userTest_1.getName()));
//        } catch (DAOException e) {
//            System.out.println(e);
//        }
//
//
//        try {
//            store.deleteUserById(36);
//        } catch (DAOException e) {
//            System.out.println(e);
//        }

//        try {
//            User user = store.readUserById(36);
//            user.setName("test update");
//            store.updateUser(user);
//        } catch (DAOException e) {
//            System.out.println(e);
//        }



        Thread.sleep(5000);






//        HibernateUtil.shutdown();

    }
}