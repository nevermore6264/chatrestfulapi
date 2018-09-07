package com.spiralg.chatrestful.model.api.view;

public class InvitationView {
    private int id;
    private String name;

    public InvitationView() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InvitationView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
