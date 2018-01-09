package ru.job4j.crud_servlet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.crud_servlet.model.User;
import ru.job4j.crud_servlet.utils.HibernateUtil;

import java.util.Arrays;

/**
 * UserStore.
 *
 * @author Stanislav (376825@mail.ru)
 * @since 06.01.2018
 */
public final class UserStore {
    private static UserStore instance = new UserStore();

    private UserStore() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    }

    public static UserStore getInstance() {
        return instance;
    }

    public void createUser(User user) throws DAOException {
        Transaction txn = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            txn = session.beginTransaction();

            session.save(user);

            txn.commit();
        } catch (ExceptionInInitializerError e) {
            throw new DAOException("Initial SessionFactory creation failed. ", e);
        } catch (Exception e) {
            if (txn != null && txn.isActive()) {
                txn.rollback();
            }

            throw new DAOException(Arrays.toString(e.getStackTrace()), e);
        }
    }

    public User readUserById(int id) throws DAOException {
        User result = null;

        Transaction txn = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            txn = session.beginTransaction();

            result = session.get(User.class, id);

            txn.commit();
        } catch (ExceptionInInitializerError e) {
            throw new DAOException("Initial SessionFactory creation failed. ", e);
        } catch (Exception e) {
            if (txn != null && txn.isActive()) {
                txn.rollback();
            }

            throw new DAOException(Arrays.toString(e.getStackTrace()), e);
        }

        return result;
    }

    public void updateUser(User user) throws DAOException {
        Transaction txn = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            txn = session.beginTransaction();

            session.update("User", user);

            txn.commit();
        } catch (ExceptionInInitializerError e) {
            throw new DAOException("Initial SessionFactory creation failed. ", e);
        } catch (Exception e) {
            if (txn != null && txn.isActive()) {
                txn.rollback();
            }

            throw new DAOException(Arrays.toString(e.getStackTrace()), e);
        }
    }

    public boolean deleteUserById(int id) throws DAOException {
        boolean result = false;

        Transaction txn = null;
        try (Session session = HibernateUtil.getSessionFactory().getCurrentSession()) {
            txn = session.beginTransaction();

            String hql = "DELETE FROM User WHERE id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            int delete = query.executeUpdate();

            if (delete > 0) {
                result = true;
            }

            txn.commit();

        } catch (ExceptionInInitializerError e) {
            throw new DAOException("Initial SessionFactory creation failed. ", e);
        } catch (Exception e) {
            if (txn != null && txn.isActive()) {
                txn.rollback();
            }

            throw new DAOException(Arrays.toString(e.getStackTrace()), e);
        }

        return result;
    }
}