package com.softserve.boardgameShack.servlet;

import com.softserve.boardgameShack.entity.User;
import com.softserve.boardgameShack.service.LoginService;
import com.softserve.boardgameShack.service.LoginServiceImpl;
import com.softserve.boardgameShack.service.UserService;
import com.softserve.boardgameShack.service.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        if (loginService.validate(name, password)){
            List<User> userList = userService.getByName(name);
            User user = userList.get(0);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/homepage");
        }else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/invalidLogin.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
