package com.spiralg.chatrestful.model.api.form;

public class AcceptForm {

    private int userFriend;
    private int userId;

    public AcceptForm() {
    }

    public int getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(int userFriend) {
        this.userFriend = userFriend;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
