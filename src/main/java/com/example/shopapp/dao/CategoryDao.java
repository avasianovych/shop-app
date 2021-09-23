package com.example.shopapp.dao;

import com.example.shopapp.entity.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao extends AbstractDao<Category> {

    private static CategoryDao instance;

    public static CategoryDao getInstance() {
        if (instance == null) {
            instance = new CategoryDao();
        }
        return instance;
    }

    int findByName(String categoryName){
        DBManager dbManager = DBManager.getInstance();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Category category = new Category();
        try(Connection connection = dbManager.getConnection()){
            stmt = connection.prepareStatement(SQLConstants.FIND_CATEGORY_ID_BY_NAME);
            stmt.setString(1,categoryName);
            rs = stmt.executeQuery();
            while (rs.next()){
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
                close(stmt);
                close(rs);
        }
        return category.getId();
    }

    public List<Category> findAll(){
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
            e.printStackTrace();
        }finally {
            close(rs);
        }
        return categoryList;
    }
}
