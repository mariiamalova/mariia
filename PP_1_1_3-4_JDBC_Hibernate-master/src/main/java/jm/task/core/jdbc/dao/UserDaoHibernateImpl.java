package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction;

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS User " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS User;").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("INSERT INTO mydbtest.User (name, lastname, age) VALUES ('" + name + "','" + lastName + "','" + age + "')");
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("delete User where id = :id")
                    .setParameter("id", id).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession();) {
            List<User> list = new ArrayList<>();
            list = session.createQuery("from User order by name").list();
            // list = list.stream().map(x -> (User) x).collect(Collectors.toList());
            return list;
        }

    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession();) {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("DELETE from mydbtest.User").addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (IllegalStateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
