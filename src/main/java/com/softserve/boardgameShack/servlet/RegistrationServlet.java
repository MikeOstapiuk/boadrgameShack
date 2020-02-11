package com.softserve.boardgameShack.servlet;

import com.softserve.boardgameShack.entity.User;
import com.softserve.boardgameShack.entity.UserRole;
import com.softserve.boardgameShack.service.UserService;
import com.softserve.boardgameShack.service.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(RegistrationServlet.class.getName());
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/registrationForm.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        user.setName(req.getParameter("name"));
        user.setPassword(req.getParameter("password"));
        user.setEmail(req.getParameter("email"));
        user.setPhone(req.getParameter("phone"));
        user.setUserRole(UserRole.USER);
        String repeatPassword = req.getParameter("repeatPassword");
        try {
            userService.add(user, repeatPassword);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/homepage.jsp");
            requestDispatcher.forward(req, resp);
        }catch (IllegalArgumentException e){
            req.setAttribute("error-msg", e.getMessage());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("jsp/registrationForm.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
}
