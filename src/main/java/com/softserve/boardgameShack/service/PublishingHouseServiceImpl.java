package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.dao.PublishingHouseDao;
import com.softserve.boardgameShack.entity.PublishingHouse;

import java.util.List;

public class PublishingHouseServiceImpl implements PublishingHouseService{

    private PublishingHouseDao dao = new PublishingHouseDao();

    @Override
    public List<PublishingHouse> getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public List<PublishingHouse> getAll() {
        return dao.getAll();
    }

    @Override
    public void add(PublishingHouse model) {
        dao.add(model);
    }
}
