package com.softserve.boardgameShack.dao;

import com.softserve.boardgameShack.entity.Category;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements GenericDao<Category> {
    private static Logger logger = Logger.getLogger(CategoryDao.class.getName());

    private static final String GET_BY_NAME = "select * from categories where name = ?";
    private static final String GET_BY_ID = "select * from categories where id = ?";
    private static final String GET_ALL = "select * from categories";
    private static final String ADD_CATEGORY = "insert into categories (name) values (?)";
    private static final String UPDATE_CATEGORY = "update categories set name = ? where id = ?";
    private static final String DELETE_CATEGOTY = "delete from categories where id = ?";

    public Category getByName(String name) {
        Category category = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getLong(1));
                category.setName(resultSet.getString(2));
            }
        }catch (SQLException e){
            logger.error("Issue with getting category" +
                    " from database");
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public Category getById(long id) {
        Category category = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)){

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                category = new Category();
                category.setId(Long.valueOf(resultSet.getString(1)));
                category.setName(resultSet.getString(2));
            }
        }catch (SQLException e) {
            logger.error("Issue with getting category from database");
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(1);
                category.setName(resultSet.getString(2));
                categories.add(category);
            }
        }catch (SQLException e){
            logger.error("Issue with getting category" +
                    " from database");
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void add(Category model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_CATEGORY)) {
            preparedStatement.setString(1, model.getName());
            preparedStatement.execute();
        }catch (SQLException e){
            logger.error("Issue with adding new category" +
                    " to database");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY)) {

            preparedStatement.setLong(1, model.getId());
            preparedStatement.setString(2, model.getName());
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("Issue with updating category" +
                    "");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGOTY)){

            preparedStatement.setLong(1, model.getId());

            preparedStatement.execute();

        }catch (SQLException e){
            logger.error("Issue with deleting category" +
                    " from database");
            e.printStackTrace();
        }
    }
}
