package com.spiralg.chatrestful.model;

public class User {

    private int id;

    private String userName;

    private byte status;

    public User(){
    }

    public User(int id, String userName, byte status) {
        this.id = id;
        this.userName = userName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                '}';
    }
}
