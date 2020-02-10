package com.softserve.boardgameShack.dao;

public interface LoginDao {

    boolean validate (String name, String password);
}
