package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.dao.GameDao;
import com.softserve.boardgameShack.dao.PublishingHouseDao;
import com.softserve.boardgameShack.entity.Game;
import com.softserve.boardgameShack.entity.PublishingHouse;

import java.util.List;

public class GameServiceImpl implements GameService{

    private GameDao gameDao = new GameDao();
    private PublishingHouseDao publishingHouseDao = new PublishingHouseDao();

    @Override
    public List<Game> getByName(String name) {
        return gameDao.getByName(name);
    }

    @Override
    public List<Game> getByNameWildcard(String name) {
        return gameDao.getByNameWildcard(name);
    }

    @Override
    public Game getById(long id) {

        return gameDao.getById(id);
    }

    @Override
    public List<Game> getAll() {
        return gameDao.getAll();
    }

    public void add(Game model, String houseName) {

        addPublishingHouse(model, houseName);
        gameDao.add(model);
    }

    @Override
    public void update(Game model, String houseName) {
        addPublishingHouse(model, houseName);
        gameDao.update(model);
    }

    @Override
    public void delete(Game model) {
        gameDao.delete(model);
    }

    private void addPublishingHouse(Game model, String houseName) {
        if(houseName.equals("")){
            return;
        }
        List<PublishingHouse> publishingHouseList = publishingHouseDao.getByName(houseName);
        if (publishingHouseList.size() != 0) {
            model.setPublishingHouse(publishingHouseList.get(0));
        } else {
            PublishingHouse publishingHouse = new PublishingHouse();
            publishingHouse.setName(houseName);
            publishingHouseDao.add(publishingHouse);
            model.setPublishingHouse(publishingHouseDao.getByName(houseName).get(0));
        }
    }
}
