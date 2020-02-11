package com.softserve.boardgameShack.servlet;

import com.softserve.boardgameShack.service.LogoutService;
import com.softserve.boardgameShack.service.LogoutServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static Logger logger = Logger.getLogger(LogoutServlet.class.getName());
    private LogoutService logoutService = new LogoutServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        logoutService.logout(session);
        resp.sendRedirect("/homepage");
    }
}
