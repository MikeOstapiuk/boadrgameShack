package com.softserve.boardgameShack.service;

import javax.servlet.http.HttpSession;

public interface LogoutService {

    public void logout(HttpSession session);
}
