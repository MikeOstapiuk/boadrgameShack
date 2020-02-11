package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.entity.PublishingHouse;

import java.util.List;

public interface PublishingHouseService {

    PublishingHouse getByName(String name);

    List<PublishingHouse> getAll();

    void add (PublishingHouse model);
}
