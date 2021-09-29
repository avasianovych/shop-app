package com.example.shopapp.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {
    private static final Logger LOGGER = LogManager.getLogger(DBManager.class);
    private static DBManager instance;
    private DataSource ds;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/bikeshopdb");
        } catch (NamingException ex) {
            LOGGER.log(Level.INFO, ex);
            throw new IllegalStateException("Cannot init DBManager", ex);
        }
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
