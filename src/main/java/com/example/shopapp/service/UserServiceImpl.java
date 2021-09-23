package com.example.shopapp.service;

import com.example.shopapp.dao.DBManager;
import com.example.shopapp.dao.SQLConstants;
import com.example.shopapp.dao.SQLQueryMapper;
import com.example.shopapp.dao.UserDao;
import com.example.shopapp.entity.User;
import com.example.shopapp.exception.DaoException;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.util.ConstantNames;
import com.example.shopapp.util.Validator;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = UserDao.getInstance();
    private static UserServiceImpl instance;

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        User user;
        try{
            user = userDao.findByLogin(login);
        } catch (DaoException e){
            e.printStackTrace();
            throw new ServiceException("error while trying to find user by login", e);
        }
        return user;
    }

    @Override
    public boolean isLoginExist(String login) throws ServiceException {
        try {
            if (userDao.isLoginExist(login)) {
                return true;
            }
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(ConstantNames.ERROR_EMAIL_EXIST);
        }
        return false;
    }

    @Override
    public void update(int userId, String action) {
        if (action.equals("block")) {
            userDao.update(userId, SQLQueryMapper.BLOCK_USER);
        } else if (action.equals("unblock")) {
            userDao.update(userId, SQLQueryMapper.UNBLOCK_USER);
        }
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public void add(User user) throws ServiceException {
        if (!Validator.validateLoginWithRegex(user.getLogin())) {
            throw new ServiceException("1");
        } else if (!Validator.validatePasswordWithRegex(user.getPassword())) {
            throw new ServiceException("2");
        }
        try {
            userDao.add(user);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("error while trying to add new user");
        }
    }
}
