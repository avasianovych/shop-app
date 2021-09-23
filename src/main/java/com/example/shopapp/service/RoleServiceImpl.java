package com.example.shopapp.service;

import com.example.shopapp.dao.RoleDao;

public class RoleServiceImpl implements RoleService{
    RoleDao roleDao = RoleDao.getInstance();

    private static RoleServiceImpl instance;

    public static RoleServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoleServiceImpl();
        }
        return instance;
    }

    @Override
    public String findByLogin(String login) {
        return roleDao.findByLogin(login);
    }
}
