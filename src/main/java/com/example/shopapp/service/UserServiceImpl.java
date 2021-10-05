package com.example.shopapp.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.shopapp.dao.SQLQueryMapper;
import com.example.shopapp.dao.UserDao;
import com.example.shopapp.entity.User;
import com.example.shopapp.exception.DaoException;
import com.example.shopapp.exception.ServiceException;
import com.example.shopapp.util.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private final UserDao userDao = UserDao.getInstance();
    private static final UserService INSTANCE = new UserServiceImpl();

    public static UserService getInstance() {
        return INSTANCE;
    }

    @Override
    public User findByLogin(String login) throws ServiceException {
        User user;
        try {
            user = userDao.findByLogin(login);
        } catch (DaoException e) {
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
            throw new ServiceException("an error while trying to find exist login", e);
        }
        return false;
    }

    @Override
    public void update(int userId, String action) throws ServiceException {
        if (action.equals("block")) {
            try {
                userDao.update(userId, SQLQueryMapper.BLOCK_USER);
            } catch (DaoException e) {
                LOGGER.log(Level.ERROR, e);
                throw new ServiceException("an error occurred while trying to block user ", e);
            }
        } else if (action.equals("unblock")) {
            try {
                userDao.update(userId, SQLQueryMapper.UNBLOCK_USER);
            } catch (DaoException e) {
                LOGGER.log(Level.ERROR, e);
                throw new ServiceException("an error occurred while trying to unblock user", e);
            }
        }
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to find all users", e);
        }
    }

    @Override
    public void add(User user) throws ServiceException {
        if (!Validator.validateLoginWithRegex(user.getLogin())) {
            throw new ServiceException("1");
        } else if (!Validator.validatePasswordWithRegex(user.getPassword())) {
            throw new ServiceException("2");
        }
        try {
            user.setPassword(BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray()));
            userDao.add(user);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException("error while trying to add new user");
        }
    }
}
