package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserDao userDao2 = new UserDaoHibernateImpl();
        userDao2.createUsersTable();

        userDao2.saveUser("Name3", "LastName1", (byte) 20);
        userDao2.saveUser("Name1121", "LastName1", (byte) 20);
        userDao2.saveUser("Name1231", "LastName1", (byte) 20);
        userDao2.saveUser("Name112", "LastName1", (byte) 20);
        userDao2.saveUser("Name1121", "LastName1", (byte) 20);
        userDao2.saveUser("Name1231", "LastName1", (byte) 20);
        userDao2.saveUser("Name112", "LastName1", (byte) 20);
        userDao2.removeUserById(3);
       userDao2.getAllUsers();
        for (User user : userDao2.getAllUsers()) {
            System.out.println(user);
        }
        userDao2.cleanUsersTable();
        userDao2.dropUsersTable();
    }
}