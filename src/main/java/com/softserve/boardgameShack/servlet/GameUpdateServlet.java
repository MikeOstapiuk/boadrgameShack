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

@WebServlet("/admin/gameUpdate")
public class GameUpdateServlet extends HttpServlet {

    private GameService gameService = new GameServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        Game game = gameService.getById(id);
        req.setAttribute("game", game);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/gameUpdateForm.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game game = gameService.getById(Long.valueOf(req.getParameter("id")));
        game.setName(req.getParameter("name"));
        game.setPrice(Double.valueOf(req.getParameter("price")));
        game.setTimeToPlay(req.getParameter("timeToPlay"));
        game.setPlayerNumber(req.getParameter("playerNumber"));
        game.setDescription(req.getParameter("description"));
        game.setLanguage(req.getParameter("language"));
        gameService.update(game);
        resp.sendRedirect("/homepage");
    }
}
