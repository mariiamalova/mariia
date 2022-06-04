package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static Session sessionClass;


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            sessionClass = session;
            sessionClass.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)").addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionClass.getTransaction().rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            sessionClass = session;
            sessionClass.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS users;").addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionClass.getTransaction().rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession();) {
            sessionClass = session;
            sessionClass.beginTransaction();
            Query query = session.createSQLQuery("INSERT INTO users (name, lastname, age) VALUES ('" + name + "','" + lastName + "','" + age + "')");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionClass.getTransaction().rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession();) {
            sessionClass = session;
            sessionClass.beginTransaction();
            Query query = session.createSQLQuery("delete from users where id = :id")
                    .setParameter("id", id).addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            sessionClass.getTransaction().rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession();) {
            sessionClass = session;
            sessionClass.beginTransaction();
            List<User> allUsers = session.createQuery("from User", User.class).getResultList();
            session.getTransaction().commit();
            return allUsers;
        } catch (Exception e) {
            sessionClass.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            sessionClass = session;
            sessionClass.beginTransaction();
            Query query = session.createSQLQuery("DELETE from users").addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("запрос не удался");
            sessionClass.getTransaction().rollback();
        }
    }
}
