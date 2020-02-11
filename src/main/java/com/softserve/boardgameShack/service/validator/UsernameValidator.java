package com.softserve.boardgameShack.service.validator;

import com.softserve.boardgameShack.dao.UserDao;

public class UsernameValidator {

    private UserDao userDao = new UserDao();

    public void validate (String username) throws IllegalArgumentException{
        if (userDao.getByName(username).size() != 0){
            throw new IllegalArgumentException("\"" + username + "\"" +
                    " username already exist. Please choose another");
        }
    }
}
