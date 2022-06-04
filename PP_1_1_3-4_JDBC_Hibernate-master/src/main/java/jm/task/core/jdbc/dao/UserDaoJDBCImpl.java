//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    private static PreparedStatement preparedStatement;
//
//    public UserDaoJDBCImpl() {
//    }
//
//    public void createUsersTable() {
//        try (Connection connection = Util.getConnection()){
//            preparedStatement = connection.prepareStatement("create table IF NOT EXISTS users (\n" +
//                    "id BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
//                    "name varchar(100) NOT NULL,\n" +
//                    "lastname varchar(100) NOT NULL,\n" +
//                    "age TINYINT NOT NULL\n" +
//                    ");");
//            preparedStatement.executeUpdate();
//            Util.getConnection().commit();
//        } catch (SQLException e) {
//            try {
//                Util.getConnection().rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("Не удалось создать таблицу!");
//        }
//    }
//
//    public void dropUsersTable() {
//        try (Connection connection = Util.getConnection()){
//            preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS users;");
//            preparedStatement.executeUpdate();
//            Util.getConnection().commit();
//        } catch (SQLException e) {
//            try {
//                Util.getConnection().rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("Не удалось удалить таблицу!");
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        try(Connection connection = Util.getConnection()) {
//
//            preparedStatement = connection
//                    .prepareStatement("INSERT INTO mydbtest.users (name, lastname, age) VALUES (?, ?, ?);");
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setByte(3, age);
//            preparedStatement.executeUpdate();
//            connection.commit();
//        } catch (SQLException e) {
//            try {
//                Util.getConnection().rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            e.printStackTrace();
//        }
//     }
//
//    public void removeUserById(long id) {
//        try (Connection connection = Util.getConnection()) {
//            preparedStatement = connection.prepareStatement("DELETE FROM users where id = ?");
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//            Util.getConnection().commit();
//        } catch (SQLException e) {
//            try {
//                Util.getConnection().rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            e.printStackTrace();
//        }
//    }
//
//    public List<User> getAllUsers() {
//        ResultSet resultSet = null;
//        List<User> listUsers = new ArrayList<>();
//
//        try (Connection connection = Util.getConnection()){
//            preparedStatement = connection.prepareStatement("SELECT * FROM mydbtest.users;");
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                User user = new User();
//                user.setId(resultSet.getLong("id"));
//                user.setName(resultSet.getString("name"));
//                user.setLastName(resultSet.getString("lastname"));
//                user.setAge(resultSet.getByte("age"));
//                listUsers.add(user);
//
//            }
//            Util.getConnection().commit();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            try {
//                assert resultSet != null;
//                resultSet.close();
//                Util.getConnection().rollback();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return listUsers;
//    }
//
//    public void cleanUsersTable() {
//        try (Connection connection = Util.getConnection()) {
//            preparedStatement = connection.prepareStatement("DELETE from mydbtest.users");
//            preparedStatement.executeUpdate();
//            Util.getConnection().commit();
//        } catch (SQLException e) {
//            try {
//                Util.getConnection().rollback();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("Не удалось очистить тамблицу!");
//        }
//    }
//}
