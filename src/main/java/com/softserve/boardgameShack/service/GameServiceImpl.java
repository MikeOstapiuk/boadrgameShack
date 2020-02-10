package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.dao.GameDao;
import com.softserve.boardgameShack.entity.Game;

import java.util.List;

public class GameServiceImpl implements GameService{

    private GameDao dao = new GameDao();

    @Override
    public List<Game> getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public List<Game> getByNameWildcard(String name) {
        return dao.getByNameWildcard(name);
    }

    @Override
    public Game getById(long id) {
        return dao.getById(id);
    }

    @Override
    public List<Game> getAll() {
        return dao.getAll();
    }

    public void add(Game model) {
        dao.add(model);
    }

    @Override
    public void update(Game model) {
        dao.update(model);
    }

    @Override
    public void delete(Game model) {
        dao.delete(model);
    }
}
