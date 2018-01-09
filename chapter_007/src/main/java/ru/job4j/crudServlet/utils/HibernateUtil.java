package ru.job4j.crudServlet.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 06.01.2018
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        sessionFactory.close();
    }
}