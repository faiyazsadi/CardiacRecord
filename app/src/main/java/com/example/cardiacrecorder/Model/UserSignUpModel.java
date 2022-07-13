package com.example.cardiacrecorder.Model;

public class UserSignUpModel {

    public String signUpEmail;
    public String signUpUserName;
    public String signUpPassword;
    public String signUpConfirmPassword;


    public UserSignUpModel(String signUpEmail, String signUpUserName, String signUpPassword, String signUpConfirmPassword) {
        this.signUpEmail = signUpEmail;
        this.signUpUserName = signUpUserName;
        this.signUpPassword = signUpPassword;
        this.signUpConfirmPassword = signUpConfirmPassword;
    }

    public String getSignUpEmail() {
        return signUpEmail;
    }

    public void setSignUpEmail(String signUpEmail) {
        this.signUpEmail = signUpEmail;
    }

    public String getSignUpUserName() {
        return signUpUserName;
    }

    public void setSignUpUserName(String signUpUserName) {
        this.signUpUserName = signUpUserName;
    }

    public String getSignUpPassword() {
        return signUpPassword;
    }

    public void setSignUpPassword(String signUpPassword) {
        this.signUpPassword = signUpPassword;
    }

    public String getSignUpConfirmPassword() {
        return signUpConfirmPassword;
    }

    public void setSignUpConfirmPassword(String signUpConfirmPassword) {
        this.signUpConfirmPassword = signUpConfirmPassword;
    }

}
