package com.example.shopapp.service;

import com.example.shopapp.exception.ServiceException;

public interface RoleService {

    String findByLogin(String login) throws ServiceException;

}
