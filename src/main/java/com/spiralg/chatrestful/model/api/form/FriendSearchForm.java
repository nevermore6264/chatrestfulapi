package com.spiralg.chatrestful.model.api.form;

public class FriendSearchForm {
    private String userFriend;
    private byte status;

    public FriendSearchForm(){}

    public String getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(String userFriend) {
        this.userFriend = userFriend;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
