package com.study.OnlineShop.web.auth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthUtils {

    //private static List<String> tokens;
    private static List<String> tokens = new ArrayList<>();

    public AuthUtils() {

    }

    public static boolean isTokenValid(HttpServletRequest req, List<String> tokens) {
        Cookie[] cookies = req.getCookies();
        boolean validator = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user-token".equals(cookie.getName())) {
                    if (tokens.contains(cookie.getValue())) {
                        validator = true;
                    }
                }
            }
        }
        return validator;
    }

    public static Cookie logout(HttpServletRequest req, List<String> tokens) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("user-token")) {
                    tokens.remove(cookie.getValue());
                    cookie.setMaxAge(0);
                    return cookie;
                }
            }
        }
        return null;
    }

    public static List<String> getTokens() {
        return tokens;
    }

    public static String addNewToken() {
        String token = UUID.randomUUID().toString();
        tokens.add(token);
        return token;
    }

}
