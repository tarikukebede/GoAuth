package com.GoSchool.Auth.Config;

public enum Role {
    USER ("User"), ADMIN("Admin"), SUPER_ADMIN("Super_Admin");

    private String role;

    Role(String role){
        this.role = role;
    }
}
