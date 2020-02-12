package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.dao.UserDao;
import com.softserve.boardgameShack.entity.User;
import com.softserve.boardgameShack.service.validator.EmailValidator;
import com.softserve.boardgameShack.service.validator.PasswordValidator;
import com.softserve.boardgameShack.service.validator.UsernameValidator;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDao();
    private EmailValidator emailValidator = new EmailValidator();
    private PasswordValidator passwordValidator = new PasswordValidator();
    private UsernameValidator usernameValidator = new UsernameValidator();

    @Override
    public List<User> getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void add(User model, String repeatPassword) throws IllegalArgumentException {
        passwordValidator.exists(model.getPassword());
        passwordValidator.validate(model.getPassword(), repeatPassword);
        usernameValidator.validate(model.getName());
        emailValidator.exists(model.getEmail());
        emailValidator.validate(model.getEmail());
        dao.add(model);
    }
}
