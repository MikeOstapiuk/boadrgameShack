package com.softserve.boardgameShack.service.validator;

import com.softserve.boardgameShack.dao.UserDao;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

    private UserDao userDao = new UserDao();

    public void validate(String email) throws IllegalArgumentException{
        String emailString="^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
        Pattern emailPattern=Pattern.compile(emailString);
        Matcher emailMatcher=emailPattern.matcher(email);
        if (!emailMatcher.matches()) {
            throw new IllegalArgumentException("Email is not valid.");
        }
    }

    public void exists(String email) throws IllegalArgumentException{
        if (userDao.getByEmail(email) != null){
            throw new IllegalArgumentException("This mail is already registered. Please type another.");
        }
    }

}
