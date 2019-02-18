package com.study.OnlineShop.entity;

public enum UserRole {
    ADMIN("admin"),
    USER("user"),
    GUEST("guest");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static UserRole getByLogin (String role){
        UserRole[] userRoles = values();
        for (UserRole userRole : userRoles){
            if (userRole.role.equalsIgnoreCase(role)){
                return userRole;
            }
        }
        throw new IllegalArgumentException("No role found for " + role);
    }
}

