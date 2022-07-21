package com.example.cardiacrecorder.Model;

public class UserSignUpModel {

    public String email;
    public String userName;
    public String password;
    public String userKey;

    public UserSignUpModel() {
    }

    public UserSignUpModel(String email, String userName, String password, String userKey) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.userKey = userKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }
}
