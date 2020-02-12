package com.softserve.boardgameShack.servlet;

import com.softserve.boardgameShack.entity.Category;
import com.softserve.boardgameShack.entity.Game;
import com.softserve.boardgameShack.service.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/admin/gameSave")
public class GameSaveServlet extends HttpServlet {

    private GameService gameService = new GameServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.getAll();
        req.setAttribute("categories", categories);
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
        String[] categoryArray = req.getParameterValues("categoryArray");
        List <String> categoryNames = Arrays.asList(categoryArray);

        gameService.add(game, publishingHouse, categoryNames);
        resp.sendRedirect("/homepage");
    }
}
