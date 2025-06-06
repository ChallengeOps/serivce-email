package com.emailservice.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_confirmation")
public class EmailConfirmation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "confirmation_token", nullable = false, unique = true, length = 255)
    private String confirmationToken;

    @Column(name = "token_expiry_date", nullable = false)
    private LocalDateTime tokenExpiryDate;

    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Status status;  // Pode usar ENUM depois se quiser.

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum Status {
        PENDING,
        CONFIRMED
    }

    public EmailConfirmation(){}

    public EmailConfirmation(Integer userId, String email, String confirmationToken, LocalDateTime tokenExpiryDate, Status status, LocalDateTime createdAt) {
        this.userId = userId;
        this.email = email;
        this.confirmationToken = confirmationToken;
        this.tokenExpiryDate = tokenExpiryDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
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

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public LocalDateTime getTokenExpiryDate() {
        return tokenExpiryDate;
    }

    public void setTokenExpiryDate(LocalDateTime tokenExpiryDate) {
        this.tokenExpiryDate = tokenExpiryDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}