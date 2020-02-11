package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.dao.PublishingHouseDao;
import com.softserve.boardgameShack.entity.PublishingHouse;

import java.util.List;

public class PublishingHouseServiceImpl implements PublishingHouseService{

    private PublishingHouseDao dao = new PublishingHouseDao();

    @Override
    public PublishingHouse getByName(String name) {
        List<PublishingHouse> publishingHouseList = dao.getByName(name);
        if (publishingHouseList.size() == 0){
            throw new IllegalArgumentException("No publishing house with such name exists");
        }
        return publishingHouseList.get(0);
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
