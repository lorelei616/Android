package com.example.myapplication;

import androidx.annotation.NonNull;

public class User {
    private String id;
    private String nickname;
    private String password;
    private int rate;

    public User(String id, String nickname, String password) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.rate = 1;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" + "id='" + id + '\'' + ", password='" + password + '\'' + '}';
    }
}
