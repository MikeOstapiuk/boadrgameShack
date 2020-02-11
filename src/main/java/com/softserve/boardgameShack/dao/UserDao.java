package com.softserve.boardgameShack.dao;

import com.softserve.boardgameShack.entity.User;
import com.softserve.boardgameShack.entity.UserRole;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements GenericDao<User> {

    private static Logger logger = Logger.getLogger(UserDao.class.getName());

    private static final String GET_BY_NAME = "select * from users where name = ?";
    private static final String GET_BY_EMAIL = "select * from users where email = ?";
    private static final String GET_BY_ID = "select * from users where id = ?";
    private static final String GET_ALL = "select * from users";
    private static final String CREATE_USER = "insert into users (name, password, email, phone, user_role) " +
            "values (?, ?, ?, ?, ?)";
    private static final String UPDATE_USER = "update users set name = ?, password = ?, email = ?, " +
            "phone = ?, user_role = ? where id = ?";
    private static final String DELETE_USER = "delete from users where id = ?";

    public List<User> getByName(String name) {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_NAME)) {

            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(convertToUser(resultSet));
            }

        } catch (SQLException e) {
            logger.error("Issue with getting users from database");
            e.printStackTrace();
        }

        return users;
    }

    public User getByEmail(String email) {
        User user = null;
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_EMAIL)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                user = new User();
                user = convertToUser(resultSet);
            }

        } catch (SQLException e) {
            logger.error("Issue with getting users from database");
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User getById(long id) {
        User user = new User();
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)){

            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            user = convertToUser(resultSet);

        }catch (SQLException e) {
            logger.error("Issue with getting user from database");
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL);

            while (resultSet.next()) {
                users.add(convertToUser(resultSet));
            }

        } catch (SQLException e) {
            logger.error("Issue with getting users from database");
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void add(User model) {
        if (model.getUserRole() == null) {
            model.setUserRole(UserRole.USER);
        }
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            convertToStatement(model, preparedStatement);

            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("Issue with adding new user to database");
            e.printStackTrace();
        }
    }

    @Override
    public void update(User model) {
        if (model.getUserRole() == null) {
            model.setUserRole(UserRole.USER);
        }
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            convertToStatement(model, preparedStatement);

            preparedStatement.setLong(6, model.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("Issue with updating user");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User model) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)){

            preparedStatement.setLong(1, model.getId());
            preparedStatement.execute();

        }catch (SQLException e){
            logger.error("Issue with deleting user");
            e.printStackTrace();
        }
    }

    private void convertToStatement(User model, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, model.getName());
        preparedStatement.setString(2, model.getPassword());
        preparedStatement.setString(3, model.getEmail());
        if (model.getPhone().equals("")) {
            preparedStatement.setString(4, null);
        }else {
            preparedStatement.setString(4, model.getPhone());
        }
        preparedStatement.setString(5, model.getUserRole().name());
    }

    private User convertToUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setName(resultSet.getString(2));
        user.setPassword(resultSet.getString(3));
        user.setEmail(resultSet.getString(4));
        user.setPhone(resultSet.getString(5));
        user.setUserRole(UserRole.valueOf(resultSet.getString(6)));
        return user;
    }
}
