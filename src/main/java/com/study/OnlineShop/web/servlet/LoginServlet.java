package com.study.OnlineShop.web.servlet;

import com.study.OnlineShop.entity.User;
import com.study.OnlineShop.entity.UserRole;
import com.study.OnlineShop.service.UserService;
import com.study.OnlineShop.service.impl.DefaultSecurityService;
import com.study.OnlineShop.web.auth.AuthUtils;
import com.study.OnlineShop.web.auth.Session;
import com.study.OnlineShop.web.template.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginServlet extends HttpServlet {

    private DefaultSecurityService securityService;

    public LoginServlet(DefaultSecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Map<String, Object> pageContent = new HashMap<>();
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(PageGenerator.instance().getPage("login.html"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        //System.out.println(login + "  " + password);

        Session session = securityService.login(login, password);
        if (session != null) {
            Cookie cookie = new Cookie("user-token", session.getToken());
            //cookie.setMaxAge(60*60*2);
            Duration cookieExpireTime = Duration.between(LocalDateTime.now(), session.getExpireDate());
            cookie.setMaxAge((int) cookieExpireTime.getSeconds());
            resp.addCookie(cookie);
            resp.sendRedirect("/products"); // 302
        } else {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("message", "Entered credentials are wrong");
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            resp.getWriter().println(PageGenerator.instance().getPage("login.html", pageVariables));
        }
    }

}