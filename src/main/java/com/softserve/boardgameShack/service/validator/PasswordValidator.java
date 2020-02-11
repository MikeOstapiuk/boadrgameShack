package com.softserve.boardgameShack.service.validator;

public class PasswordValidator {

    public void isExist(String password) throws IllegalArgumentException{
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password length must be at least 8 symbols");
        }
    }

    public void validate (String password, String passwordRepeat) throws IllegalArgumentException{
        if (!password.equals(passwordRepeat)){
            throw new IllegalArgumentException("Repeat password must equals password");
        }
    }
}
