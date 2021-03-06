package com.softserve.boardgameShack.service;

import com.softserve.boardgameShack.dao.LoginDao;
import com.softserve.boardgameShack.dao.LoginDaoimpl;

public class LoginServiceImpl implements LoginService {

    private LoginDao loginDao = new LoginDaoimpl();

    @Override
    public boolean checkUserProperties(String name, String password) {
        return loginDao.checkUserProperties(name, password);
    }
}
