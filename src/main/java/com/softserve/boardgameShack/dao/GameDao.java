package com.softserve.boardgameShack.dao;

import com.softserve.boardgameShack.entity.Category;
import com.softserve.boardgameShack.entity.Game;
import com.softserve.boardgameShack.entity.PublishingHouse;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao implements GenericDao <Game> {

    private static Logger logger = Logger.getLogger(GameDao.class.getName());

    private PublishingHouseDao publishingHouseDao = new PublishingHouseDao();
    private CategoryDao categoryDao = new CategoryDao();

    private static final String GET_BY_NAME = "select * from games where name = ?";
    private static final String GET_BY_NAME_WILDCARD = "select * from games where name like ?";
    private static final String GET_BY_ID = "select * from games where id = ?";
    private static final String GET_ALL = "select * from games";
    private static final String CREATE_GAME = "insert into games (name, price, time_to_play, player_number," +
            " rating, description, language, publishing_house_id, image) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_GAME = "update games set name = ?, price = ?, time_to_play = ?, " +
            "player_number = ?, rating = ?, description = ?, language = ?, publishing_house_id = ?, image = ? where id = ?";
    private static final String DELETE_GAME = "delete from games where id = ?";
    private static final String SET_CATEGORIES = "insert into games_categories (game_id, category_id) values (?, ?)";
    private static final String GET_CATEGORIES = "select c.name from categories c inner join games_categories gc " +
            "on c.id = gc.category_id where gc.game_id = ?";

    public List<Game> getByName(String name) {
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME)){

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                games.add(convertToGame(resultSet));
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
                games.add(convertToGame(resultSet));
            }

        }catch (SQLException e) {
            logger.error("Issue with getting games from database");
            e.printStackTrace();
        }

        return games;
    }

    public Game getById(long id) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)){

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Game game = convertToGame(resultSet);
                setCategoriesToGame(connection, resultSet, game);
                return game;
            }

        }catch (SQLException e) {
            logger.error("Issue with getting game from database");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Game> getAll() {
        List<Game> games = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()){
                games.add(convertToGame(resultSet));
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
            convertToStatement(model, preparedStatement);

            preparedStatement.execute();
            Game game = getByName(model.getName()).get(0);
            if (model.getCategories() != null && !model.getCategories().isEmpty()) {
                for (Category category : model.getCategories()){
                    try(PreparedStatement preparedStatement1 = connection.prepareStatement(SET_CATEGORIES)){
                        preparedStatement1.setLong(1, game.getId());
                        preparedStatement1.setLong(2, category.getId());
                        preparedStatement1.execute();
                    }
                }
            }
        }catch (SQLException e){
            logger.error("Issue with adding new game to database");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Game model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_GAME)){
            convertToStatement(model, preparedStatement);

            preparedStatement.setLong(10, model.getId());

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

    private void convertToStatement(Game model, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, model.getName());
        preparedStatement.setDouble(2, model.getPrice());
        preparedStatement.setString(3, model.getTimeToPlay().isEmpty() ? null : model.getTimeToPlay());
        preparedStatement.setString(4, model.getPlayerNumber().isEmpty() ? null : model.getPlayerNumber());
        preparedStatement.setDouble(5, model.getRating());
        preparedStatement.setString(6, model.getDescription().isEmpty() ? null : model.getDescription());
        preparedStatement.setString(7, model.getLanguage().isEmpty() ? null : model.getLanguage());
        if (model.getPublishingHouse() == null) {
            preparedStatement.setNull(8, Types.BIGINT);
        } else {
            preparedStatement.setLong(8, model.getPublishingHouse().getId());
        }
        preparedStatement.setString(9, model.getImage());
    }

    private Game convertToGame(ResultSet resultSet) throws SQLException {
        Game game = new Game();
        game.setId(resultSet.getLong(1));
        game.setName(resultSet.getString(2));
        game.setPrice(resultSet.getDouble(3));
        game.setTimeToPlay(resultSet.getString(4));
        game.setPlayerNumber(resultSet.getString(5));
        game.setRating(resultSet.getDouble(6));
        game.setDescription(resultSet.getString(7));
        game.setLanguage(resultSet.getString(8));
        PublishingHouse house = publishingHouseDao.getById(resultSet.getLong(9));
        if(house != null) {
            game.setPublishingHouse(publishingHouseDao.getById(resultSet.getLong(9)));
        }
        game.setImage(resultSet.getString(10));
        return game;
    }

    private void setCategoriesToGame(Connection connection, ResultSet resultSet, Game game) throws SQLException {
        try(PreparedStatement preparedStatement1 = connection.prepareStatement(GET_CATEGORIES)){
            preparedStatement1.setLong(1, game.getId());
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            List<Category> categories = new ArrayList<>();
            while (resultSet1.next()){
                categories.add(categoryDao.getByName(resultSet1.getString("name")));
            }
            if (!categories.isEmpty()){
                game.setCategories(categories);
            }
        }
    }
}
