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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gameList")
public class GameListServlet extends HttpServlet {

    private GameService gameService = new GameServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Game> games = new ArrayList<>();
        games = gameService.getAll();
        req.setAttribute("games", games);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/gameList.jsp");
        requestDispatcher.forward(req, resp);
    }
}
