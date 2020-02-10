package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.dao.UserDao;
import com.softserve.boardgameShack.entity.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDao();

    @Override
    public List<User> getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void add(User model) {
        dao.add(model);
    }
}
