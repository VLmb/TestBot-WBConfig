package com.wb_configurator.wb_report_bot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    private Long telegramId;
    private String firstName;
    private String userName;
    private String wbApiKey;

    public User() {
    }

    public Long getTelegramId() {
        return telegramId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserName() {
        return userName;
    }

    public String getWbApiKey() {
        return wbApiKey;
    }

    public void setTelegramId(Long telegramId) {
        this.telegramId = telegramId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setWbApiKey(String wbApiKey) {
        this.wbApiKey = wbApiKey;
    }
}