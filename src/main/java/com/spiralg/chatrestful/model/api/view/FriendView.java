package com.spiralg.chatrestful.model.api.view;

public class FriendView {
    private int id;
    private int userId;
    private  int userFriend;
    private byte status;

    public FriendView() {
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
}
