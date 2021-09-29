package com.example.shopapp.dao;

import com.example.shopapp.entity.Entity;
import com.example.shopapp.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public abstract class AbstractDao<T extends Entity> {
    private static final Logger LOGGER = LogManager.getLogger(AbstractDao.class);

    List<T> findAll() throws DaoException {
        return null;
    }

    void update(int id, String name) throws DaoException {
    }

    protected void close(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                LOGGER.log(Level.INFO, e);
            }
        }
    }
}
