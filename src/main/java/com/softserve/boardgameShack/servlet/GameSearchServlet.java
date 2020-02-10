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

@WebServlet("/gameSearch")
public class GameSearchServlet extends HttpServlet {

    private GameService gameService = new GameServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Game> games = new ArrayList<>();
        games = gameService.getByNameWildcard(req.getParameter("name"));

        if (games.size() == 0){
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/homepage.jsp");
            requestDispatcher.forward(req, resp);
        }else if ((games.size() == 1)) {
            Game view = games.get(0);
            req.setAttribute("model", view);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/gameView.jsp");
            requestDispatcher.forward(req, resp);
        }else {
            req.setAttribute("games", games);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/jsp/gameList.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
