package com.softserve.boardgameShack.dao;

import com.softserve.boardgameShack.entity.Game;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao implements GenericDao <Game> {

    private static Logger logger = Logger.getLogger(GameDao.class.getName());

    private static final String GET_BY_NAME = "select * from games where name = ?";
    private static final String GET_BY_NAME_WILDCARD = "select * from games where name like ?";
    private static final String GET_BY_ID = "select * from games where id = ?";
    private static final String GET_ALL = "select * from games";
    private static final String CREATE_GAME = "insert into games (name, price, time_to_play, player_number," +
            " rating, description, language, publishing_house_id) values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_GAME = "update games set name = ?, price = ?, time_to_play = ?, " +
            "player_number = ?, rating = ?, description = ?, language = ?, publishing_house_id = ? where id = ?";
    private static final String DELETE_GAME = "delete from games where id = ?";

    @Override
    public List<Game> getByName(String name) {
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME)){

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                games.add(convertGame(resultSet));
            }

        }catch (SQLException e) {
            logger.error("Issue with getting games from database");
            e.printStackTrace();
        }

        return games;
    }

    public List<Game> getByNameWildcard(String name) {
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME_WILDCARD)){

            String wildcard = "%" + name + "%";
            preparedStatement.setString(1, wildcard);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                games.add(convertGame(resultSet));
            }

        }catch (SQLException e) {
            logger.error("Issue with getting games from database");
            e.printStackTrace();
        }

        return games;
    }

    public Game getById(long id) {
        Game game = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)){

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            game = convertGame(resultSet);

        }catch (SQLException e) {
            logger.error("Issue with getting game from database");
            e.printStackTrace();
        }

        return game;
    }

    @Override
    public List<Game> getAll() {
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()){
                games.add(convertGame(resultSet));
            }

        }catch (SQLException e) {
            logger.error("Issue with getting games from database");
            e.printStackTrace();
        }

        return games;
    }

    @Override
    public void add(Game model) {

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_GAME)){
            fromModelToStatement(model, preparedStatement);

            preparedStatement.execute();

        }catch (SQLException e){
            logger.error("Issue with adding new game to database");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Game model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GAME)){
            fromModelToStatement(model, preparedStatement);

            preparedStatement.setLong(9, model.getId());

            preparedStatement.execute();

        }catch (SQLException e){
            logger.error("Issue with updating game");
            e.printStackTrace();
        }
    }

    public void delete(Game model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GAME)){

            preparedStatement.setLong(1, model.getId());

            preparedStatement.execute();

        }catch (SQLException e){
            logger.error("Issue with deleting game from database");
            e.printStackTrace();
        }
    }

    private void fromModelToStatement(Game model, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, model.getName());
        preparedStatement.setDouble(2, model.getPrice());
        preparedStatement.setString(3, model.getTimeToPlay());
        preparedStatement.setString(4, model.getPlayerNumber());
        preparedStatement.setDouble(5, model.getRating());
        preparedStatement.setString(6, model.getDescription());
        preparedStatement.setString(7, model.getLanguage());
        if (model.getPublishingHouse() == null) {
            preparedStatement.setNull(8, Types.BIGINT);
        } else {
            preparedStatement.setLong(8, model.getPublishingHouse().getId());
        }
    }

    private Game convertGame(ResultSet resultSet) throws SQLException {
        Game game = new Game();
        game.setId(Long.valueOf(resultSet.getString(1)));
        game.setName(resultSet.getString(2));
        game.setPrice(Double.valueOf(resultSet.getString(3)));
        game.setTimeToPlay(resultSet.getString(4));
        game.setPlayerNumber(resultSet.getString(5));
        game.setRating(Double.valueOf(resultSet.getString(6)));
        game.setDescription(resultSet.getString(7));
        return game;
    }
}
