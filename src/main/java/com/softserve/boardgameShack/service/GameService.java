package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.entity.Game;
import com.softserve.boardgameShack.entity.PublishingHouse;

import java.util.List;

public interface GameService {

    List<Game> getByName(String name);

    List<Game> getByNameWildcard(String name);

    Game getById(long id);

    List<Game> getAll();

    void add (Game model, String houseName);

    void update (Game model, String houseName);

    void delete (Game model);
}
