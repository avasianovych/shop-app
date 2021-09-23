package com.example.shopapp.service;

import com.example.shopapp.entity.User;
import com.example.shopapp.exception.DaoException;
import com.example.shopapp.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserService {

//    List<User> findByLogin(String login);
    User findByLogin(String login) throws ServiceException;
    void update(int userId, String action) throws ServiceException;
    List<User> findAll() throws ServiceException;
    boolean isLoginExist(String login) throws ServiceException;
    void add(User user) throws ServiceException;
    User findUserByOrderId(int orderId) throws ServiceException;
}
