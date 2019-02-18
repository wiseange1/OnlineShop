package com.study.OnlineShop.entity;

public class User {

    private int id;
    private String login;
    private String sole;
    private String role;

    public User() {
    }

    public User(int id, String login, String sole, String role) {
        this.id = id;
        this.login = login;
        this.sole = sole;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSole() {
        return sole;
    }

    public void setSole(String sole) {
        this.sole = sole;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
