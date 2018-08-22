package com.spiralg.chatrestful.model;

public class User {

    private int id;

    private String user_name;

    private byte status;

    public User(){
    }

    public User(int id, String user_name, byte status) {
        this.id = id;
        this.user_name = user_name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
                ", user_name='" + user_name + '\'' +
                ", status=" + status +
                '}';
    }
}
