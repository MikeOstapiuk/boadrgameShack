package com.softserve.boardgameShack.dao;

public interface LoginDao {

    boolean checkUserProperties(String name, String password);
}
