package com.spiralg.chatrestful.model.api.view;

import java.util.List;

public class ApiListResponse<T> {
    private Integer total;
    private List<T> items;

    public ApiListResponse() {
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
