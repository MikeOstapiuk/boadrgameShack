package com.softserve.boardgameShack.filter;

import com.softserve.boardgameShack.entity.User;
import com.softserve.boardgameShack.entity.UserRole;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminAuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("user");

        if (session == null || user == null){
            resp.sendRedirect("/loginForm");
        } else if(user.getUserRole() == (UserRole.ADMIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            resp.sendRedirect("/loginForm");
        }
    }

    @Override
    public void destroy() {

    }
}
