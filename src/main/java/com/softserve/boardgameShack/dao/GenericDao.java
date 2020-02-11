package com.softserve.boardgameShack.dao;

import java.util.List;

public interface GenericDao <T> {

    T getById(long id);

    List<T> getAll ();

    void add (T model);

    void update (T model);

    void delete (T model);
}
