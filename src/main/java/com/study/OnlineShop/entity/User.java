package com.study.OnlineShop.entity;

public class User {

    private int id;
    private String login;
    private String sole;
    private UserRole role;

    public User() {
    }

    public User(int id, String login, String sole, UserRole role) {
        this.id = id;
        this.login = login;
        this.sole = sole;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", sole='" + sole + '\'' +
                ", role=" + role +
                '}';
    }
}
