package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.entity.Game;

import java.util.List;

public interface GameService {

    List<Game> getByName(String name);

    List<Game> getByNameWildcard(String name);

    Game getById(long id);

    List<Game> getAll();

    void add (Game model);

    void update (Game model);

    void delete (Game model);
}
