package com.study.OnlineShop.web.servlet;

import com.study.OnlineShop.service.impl.DefaultSecurityService;
import com.study.OnlineShop.web.auth.AuthUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LogoutServlet extends HttpServlet {

    private DefaultSecurityService securityService;

    public LogoutServlet(DefaultSecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    securityService.removeByToken(cookie.getValue());
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
        resp.sendRedirect("/login");
    }

}
