package com.example.shopapp.service;

import com.example.shopapp.dao.ProductDao;
import com.example.shopapp.dao.RoleDao;
import com.example.shopapp.exception.DaoException;
import com.example.shopapp.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RoleServiceImpl implements RoleService{
    private static final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);
    RoleDao roleDao = RoleDao.getInstance();

    private static RoleServiceImpl instance;

    public static RoleServiceImpl getInstance() {
        if (instance == null) {
            instance = new RoleServiceImpl();
        }
        return instance;
    }

    @Override
    public String findByLogin(String login) throws ServiceException {
        try {
            return roleDao.findByLogin(login);
        }catch (DaoException e){
            LOGGER.log(Level.ERROR, e);
            throw new ServiceException("an error occurred while trying to find role by login", e);
        }
    }
}
