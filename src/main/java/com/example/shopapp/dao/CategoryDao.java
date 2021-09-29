package com.example.shopapp.dao;

import com.example.shopapp.entity.Category;
import com.example.shopapp.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends AbstractDao<Category> {

    private static final Logger LOGGER = LogManager.getLogger(CategoryDao.class);
    private static final CategoryDao INSTANCE = new CategoryDao();

    public static CategoryDao getInstance() {
        return INSTANCE;
    }

    private CategoryDao(){}

    public List<Category> findAll() throws DaoException {
        DBManager dbManager = DBManager.getInstance();
        ResultSet rs = null;
        List<Category> categoryList = new ArrayList<>();
        try(Connection connection = dbManager.getConnection();
        PreparedStatement stmt = connection.prepareStatement(SQLConstants.FIND_ALL_CATEGORIES)){
            rs = stmt.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                categoryList.add(category);
            }
        }catch (SQLException e){
            LOGGER.log(Level.ERROR, e);
            throw new DaoException("an error occurred while trying to find all categories", e);
        }finally {
            close(rs);
        }
        return categoryList;
    }
}
