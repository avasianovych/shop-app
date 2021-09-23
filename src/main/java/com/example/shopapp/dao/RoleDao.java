package com.example.shopapp.dao;

import com.example.shopapp.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RoleDao extends AbstractDao<Role> {

    private static RoleDao instance;

    public static RoleDao getInstance() {
        if (instance == null) {
            instance = new RoleDao();
        }
        return instance;
    }


    public String findByLogin(String login) {
        DBManager dbManager = DBManager.getInstance();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Role role = new Role();
        try (Connection connection = dbManager.getConnection()) {
            stmt = connection.prepareStatement(SQLConstants.FIND_ROLE_BY_USER_LOGIN);
            stmt.setString(1, login);
            rs = stmt.executeQuery();
            while (rs.next()) {
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return role.getName();
    }
}
