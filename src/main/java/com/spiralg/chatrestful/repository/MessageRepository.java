package com.spiralg.chatrestful.repository;

import com.spiralg.chatrestful.model.Message;
import com.spiralg.chatrestful.model.api.form.MessageSearchForm;

import java.util.List;

public interface MessageRepository {
    boolean create(Message message);
    Message update(Message message);
    void delete(Integer id);
    List<Message> search(MessageSearchForm searchForm);
}
