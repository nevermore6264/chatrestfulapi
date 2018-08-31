package com.spiralg.chatrestful.common.exception;

public class TcpChatException extends Exception {
    private String message;

    public TcpChatException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
