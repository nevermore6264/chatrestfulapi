package com.spiralg.chatrestful.model.api.form;

public class InvitationForm {
    private  int userFriend;

    public InvitationForm() {
    }

    public InvitationForm(int userFriend) {
        this.userFriend = userFriend;
    }

    public int getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(int userFriend) {
        this.userFriend = userFriend;
    }
}
