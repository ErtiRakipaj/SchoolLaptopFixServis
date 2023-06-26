package com.task.SchoolLaptopFixServis.responses;

import com.task.SchoolLaptopFixServis.models.User;

public class LoginResponse {
    private User user;
    private String jwt;

    public LoginResponse(){
        super();
    }

    public LoginResponse(User user, String jwt) {
        this.user = user;
        this.jwt = jwt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
