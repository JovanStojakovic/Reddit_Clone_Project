package com.example.RedditKopijaVezba.dto;

public class LoginDTO {
    //kad se neko loginuje stize nam username i password
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
