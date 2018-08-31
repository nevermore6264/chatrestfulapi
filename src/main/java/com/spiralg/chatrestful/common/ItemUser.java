package com.spiralg.chatrestful.common;

import com.spiralg.chatrestful.model.User;

import java.util.List;

public class ItemUser {
    private int total;
    private List<User> items;

    public ItemUser(int total, List<User> items) {
        this.total = total;
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public List<User> getItems() {
        return items;
    }


}
