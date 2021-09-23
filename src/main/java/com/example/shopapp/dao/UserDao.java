package com.example.shopapp.dao;

import com.example.shopapp.entity.User;
import com.example.shopapp.exception.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<User> {
    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public void add(User user) throws DaoException {
        DBManager dbManager = DBManager.getInstance();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.ADD_NEW_USER)) {
            connection.setAutoCommit(true);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("error while trying to add new user", e);
        }
    }

    public User findByLogin(String login) throws DaoException {
//        DBManager dbManager = DBManager.getInstance();
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        User user = new User();
//        try (Connection connection = dbManager.getConnection()) {
//            stmt = connection.prepareStatement(SQLConstants.FIND_USER_BY_LOGIN);
//            stmt.setString(1, login);
//            rs = stmt.executeQuery();
//            while (rs.next()) {
//                user.setId(rs.getInt("id"));
//                user.setName(rs.getString("name"));
//                user.setSurname(rs.getString("surname"));
//                user.setRole_id(rs.getInt("role_id"));
//                user.setLogin(rs.getString("login"));
//                user.setPassword(rs.getString("password"));
//                user.setBlock(rs.getBoolean("isBlock"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
            throw new DaoException("error while trying find user by login");
//        } finally {
//            close(stmt);
//            close(rs);
//        }
        //        return user;
    }

    public void update(int userId, String query) {
        DBManager dbManager = DBManager.getInstance();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            connection.setAutoCommit(true);
            stmt.setInt(1, userId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAll() {
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.FIND_ALL_USERS)) {
            rs = stmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setRole_id(rs.getInt("role_id"));
                user.setLogin(rs.getString("login"));
                user.setBlock(rs.getBoolean("isBlock"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }
        return userList;
    }

    public boolean isLoginExist(String login) throws DaoException {
        boolean isExist = false;
        ResultSet rs = null;
        DBManager dbManager = DBManager.getInstance();
        try (Connection connection = dbManager.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SQLConstants.FIND_USER_BY_LOGIN)) {
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            while (rs.next()) {
                isExist = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("error while looking for a existing login", e);
        } finally {
            close(rs);
        }
        return isExist;
    }
}
