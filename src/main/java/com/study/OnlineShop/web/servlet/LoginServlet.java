package com.study.OnlineShop.web.servlet;

import com.study.OnlineShop.entity.User;
import com.study.OnlineShop.entity.UserRole;
import com.study.OnlineShop.service.UserService;
import com.study.OnlineShop.web.auth.AuthUtils;
import com.study.OnlineShop.web.template.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginServlet extends HttpServlet {

    private UserService userService;
    private List<String> tokens;

    public LoginServlet(UserService userService, List<String> tokens) {
        this.userService = userService;
        this.tokens = tokens;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> pageContent = new HashMap<>();

        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(PageGenerator.instance().getPage("login.html", pageContent));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        //System.out.println(login + "  " + password);

        User user = userService.getByLogin(login);

        if (user != null && password.equals(user.getSole())) {  // Authentification
            if (UserRole.getByLogin(user.getRole()) == UserRole.ADMIN) { // Authorization as Admin
                String token = AuthUtils.addNewToken();
                tokens.add(token);
                Cookie cookie = new Cookie("user-token", token);
                cookie.setMaxAge(60*60*2);
                resp.addCookie(cookie);
                resp.sendRedirect("/products"); // 302
            }
        }

        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("message", "Entered credentials are wrong");
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
        resp.getWriter().println(PageGenerator.instance().getPage("login.html", pageVariables));
    }
}