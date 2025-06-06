package com.emailservice.entity;

public class Content {
    private Integer userId;
    private String email;

    public Content() {
    }

    public Content(Integer userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Content{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                '}';
    }
}
