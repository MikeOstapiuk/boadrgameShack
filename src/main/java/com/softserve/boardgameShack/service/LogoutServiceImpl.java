package com.softserve.boardgameShack.service;

import javax.servlet.http.HttpSession;

public class LogoutServiceImpl implements LogoutService {

    @Override
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
