package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.entity.Category;

import java.util.List;

public interface CategoryService {

//    Category getById(long id);

    Category getByName(String name);

    List<Category> getAll();
}
