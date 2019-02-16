package com.study.OnlineShop.web.servlet;

import com.study.OnlineShop.web.auth.AuthUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LogoutServlet extends HttpServlet {

    private List<String> tokens;

    public LogoutServlet(List<String> tokens) {
        this.tokens = tokens;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie logoutCookie = AuthUtils.logout(req, tokens);
        resp.addCookie(logoutCookie);
        resp.sendRedirect("/login");
    }

}
