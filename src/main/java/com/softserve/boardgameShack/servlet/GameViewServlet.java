package com.softserve.boardgameShack.servlet;

import com.softserve.boardgameShack.entity.Game;
import com.softserve.boardgameShack.service.GameService;
import com.softserve.boardgameShack.service.GameServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/gameView")
public class GameViewServlet extends HttpServlet {

    private GameService gameService = new GameServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.valueOf(req.getParameter("id"));
        Game view = gameService.getById(id);
        req.setAttribute("model", view);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/gameView.jsp");
        requestDispatcher.forward(req, resp);
    }
}
