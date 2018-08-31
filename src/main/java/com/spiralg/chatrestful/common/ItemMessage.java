package com.spiralg.chatrestful.common;

import com.spiralg.chatrestful.model.Message;

import java.util.List;

public class ItemMessage {
    private int total;
    private List<Message> items;

    public ItemMessage() {
    }

    public ItemMessage(int total, List<Message> items) {

        this.total = total;
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Message> getItems() {
        return items;
    }

    public void setItems(List<Message> items) {
        this.items = items;
    }
}
