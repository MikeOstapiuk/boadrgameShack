package com.softserve.boardgameShack.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static Logger logger = Logger.getLogger(ConnectionFactory.class.getName());

    private static final String USER = "mike";
    private static final String PASS = "mike";
    private static final String URL = "jdbc:mysql://localhost:3306/boardgameshack";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private ConnectionFactory (){
    }

    static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASS);
            logger.info("Connection OK");

        }catch (ClassNotFoundException | SQLException e){
            logger.error("Unable to connect with database");
            e.printStackTrace();
        }

        return connection;
    }
}