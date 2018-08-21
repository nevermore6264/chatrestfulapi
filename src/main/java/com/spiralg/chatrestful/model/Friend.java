package com.spiralg.chatrestful.model;

public class Friend {

    private int id;
    private int userId;
    private  int userFriend;
    private byte status;
    public Friend(){
    }

    public Friend(int userId, int userFriend, byte status) {
        this.userId = userId;
        this.userFriend = userFriend;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(int userFriend) {
        this.userFriend = userFriend;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", userId=" + userId +
                ", userFriend=" + userFriend +
                ", status=" + status +
                '}';
    }
}
