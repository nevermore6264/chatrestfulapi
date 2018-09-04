package com.spiralg.chatrestful.model.api.view;

public class ApiError {
    private String message;
    private String user;

    public ApiError() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
