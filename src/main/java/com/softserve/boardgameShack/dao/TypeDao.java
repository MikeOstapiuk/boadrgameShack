package com.softserve.boardgameShack.dao;

import com.softserve.boardgameShack.entity.PublishingHouse;
import com.softserve.boardgameShack.entity.Type;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeDao implements GenericDao<Type> {
    private static Logger logger = Logger.getLogger(GameDao.class.getName());

    private static final String GET_BY_NAME = "select * from types where name = ?";
    private static final String GET_BY_ID = "select * from types where id = ?";
    private static final String GET_ALL = "select * from types";
    private static final String ADD_TYPE = "insert into types (name) values (?)";
    private static final String UPDATE_TYPE = "update types set name = ? where id = ?";
    private static final String DELETE_TYPE = "delete from types where id = ?";

    @Override
    public List<Type> getByName(String name) {
        List<Type> types = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Type type = new Type();
                type.setName(resultSet.getString(2));
                types.add(type);
            }
        }catch (SQLException e){
            logger.error("Issue with getting type from database");
            e.printStackTrace();
        }
        return types;
    }

    @Override
    public Type getById(long id) {
        Type type = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)){

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            type.setId(Long.valueOf(resultSet.getString(1)));
            type.setName(resultSet.getString(2));

        }catch (SQLException e) {
            logger.error("Issue with getting type from database");
            e.printStackTrace();
        }

        return type;
    }

    @Override
    public List<Type> getAll() {
        List<Type> types = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                Type type = new Type();
                type.setName(resultSet.getString(2));
                types.add(type);
            }
        }catch (SQLException e){
            logger.error("Issue with getting type from database");
            e.printStackTrace();
        }
        return types;
    }

    @Override
    public void add(Type model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_TYPE)) {
            preparedStatement.setString(1, model.getName());
            preparedStatement.execute();
        }catch (SQLException e){
            logger.error("Issue with adding new type to database");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Type model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TYPE)) {

            preparedStatement.setLong(1, model.getId());
            preparedStatement.setString(2, model.getName());
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("Issue with updating type");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Type model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TYPE)){

            preparedStatement.setLong(1, model.getId());

            preparedStatement.execute();

        }catch (SQLException e){
            logger.error("Issue with deleting type from database");
            e.printStackTrace();
        }
    }
}
