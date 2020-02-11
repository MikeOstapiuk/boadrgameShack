package com.softserve.boardgameShack.servlet;

import com.softserve.boardgameShack.entity.Game;
import com.softserve.boardgameShack.service.GameService;
import com.softserve.boardgameShack.service.GameServiceImpl;
import com.softserve.boardgameShack.service.PublishingHouseService;
import com.softserve.boardgameShack.service.PublishingHouseServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/gameSave")
public class GameSaveServlet extends HttpServlet {

    private GameService gameService = new GameServiceImpl();
    private PublishingHouseService publishingHouseService = new PublishingHouseServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/gameSaveForm.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game game = new Game();
        game.setName(req.getParameter("name"));
        game.setPrice(Double.valueOf(req.getParameter("price")));
        game.setTimeToPlay(req.getParameter("timeToPlay"));
        game.setPlayerNumber(req.getParameter("playerNumber"));
        game.setDescription(req.getParameter("description"));
        game.setLanguage(req.getParameter("language"));
        String publishingHouse = req.getParameter("publishingHouse");

        gameService.add(game, publishingHouse);
        resp.sendRedirect("/homepage");
    }
}
