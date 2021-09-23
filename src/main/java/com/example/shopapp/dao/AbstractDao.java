package com.example.shopapp.dao;

import com.example.shopapp.entity.Entity;

import java.util.List;


public abstract class AbstractDao<T extends Entity> {

    List<T> findAll() {
        return null;
    }

    void update(int id, String name) {
    }

    protected void close(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
