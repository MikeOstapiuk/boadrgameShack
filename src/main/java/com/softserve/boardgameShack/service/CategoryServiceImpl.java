package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.dao.CategoryDao;
import com.softserve.boardgameShack.entity.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDao();

    @Override
    public Category getByName (String name){
        return categoryDao.getByName(name);
    }

    @Override
    public List<Category> getAll() {
        return categoryDao.getAll();
    }
}
