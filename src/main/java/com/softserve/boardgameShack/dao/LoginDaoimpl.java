package com.softserve.boardgameShack.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoimpl implements LoginDao{

    private static Logger logger = Logger.getLogger(LoginDaoimpl.class.getName());

    private static final String CHECK_LOGIN = "select * from users where name = ? and password = ?";

    @Override
    public boolean checkUserProperties(String name, String password) {
        boolean status = false;
        try (Connection connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN)){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            status = resultSet.next();
        }catch (SQLException e){
            logger.error("Issue in username validation");
            e.printStackTrace();
        }
        return status;
    }
}