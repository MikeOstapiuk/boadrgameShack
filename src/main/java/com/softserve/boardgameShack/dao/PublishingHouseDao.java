package com.softserve.boardgameShack.dao;

import com.softserve.boardgameShack.entity.PublishingHouse;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublishingHouseDao implements GenericDao<PublishingHouse> {

    private static Logger logger = Logger.getLogger(PublishingHouseDao.class.getName());

    private static final String GET_BY_NAME = "select * from publishing_houses where name = ?";
    private static final String GET_BY_ID = "select * from publishing_houses where id = ?";
    private static final String GET_ALL = "select * from publishing_houses";
    private static final String ADD_PUBLISHING_HOUSE = "insert into publishing_houses (name) values (?)";
    private static final String UPDATE_PH = "update publishing_houses set name = ? where id = ?";
    private static final String DELETE_PH = "delete from publishing_houses where id = ?";

    public List<PublishingHouse> getByName(String name) {
        List<PublishingHouse> publishingHouses = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PublishingHouse house = new PublishingHouse();
                house.setId(resultSet.getLong(1));
                house.setName(resultSet.getString(2));
                publishingHouses.add(house);
            }
        }catch (SQLException e){
            logger.error("Issue with getting publishing house from database");
            e.printStackTrace();
        }
        return publishingHouses;
    }

    @Override
    public PublishingHouse getById(long id) {
        PublishingHouse house = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)){

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                house = new PublishingHouse();
                house.setId(resultSet.getLong(1));
                house.setName(resultSet.getString(2));
            }

        }catch (SQLException e) {
            logger.error("Issue with getting publishing house from database");
            e.printStackTrace();
        }

        return house;
    }

    @Override
    public List<PublishingHouse> getAll() {
        List<PublishingHouse> publishingHouses = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                PublishingHouse house = new PublishingHouse();
                house.setName(resultSet.getString(2));
                publishingHouses.add(house);
            }
        }catch (SQLException e){
            logger.error("Issue with getting publishing house from database");
            e.printStackTrace();
        }
        return publishingHouses;
    }

    @Override
    public void add(PublishingHouse model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_PUBLISHING_HOUSE)) {
            preparedStatement.setString(1, model.getName());
            preparedStatement.execute();
        }catch (SQLException e){
            logger.error("Issue with adding new publishing house to database");
            e.printStackTrace();
        }
    }

    @Override
    public void update(PublishingHouse model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PH)) {

            preparedStatement.setLong(1, model.getId());
            preparedStatement.setString(2, model.getName());
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("Issue with updating publishing house");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PublishingHouse model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PH)){

            preparedStatement.setLong(1, model.getId());

            preparedStatement.execute();

        }catch (SQLException e){
            logger.error("Issue with deleting publishing house from database");
            e.printStackTrace();
        }
    }
}
