package com.example.shopapp.dao;

import com.example.shopapp.entity.Role;
import com.example.shopapp.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RoleDao extends AbstractDao<Role> {
    private static final Logger LOGGER = LogManager.getLogger(RoleDao.class);
    private static final RoleDao INSTANCE = new RoleDao();

    public static RoleDao getInstance() {
        return INSTANCE;
    }

    private RoleDao() {
    }

    public String findByLogin(String login) throws DaoException {
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
            LOGGER.log(Level.ERROR, e);
            throw new DaoException("an error occurred while trying to find role by login", e);
        } finally {
            close(stmt);
            close(rs);
        }
        return role.getName();
    }
}
