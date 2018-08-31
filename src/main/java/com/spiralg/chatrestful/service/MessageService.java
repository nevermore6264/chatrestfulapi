package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.Message;
import com.spiralg.chatrestful.model.api.form.MessageForm;
import com.spiralg.chatrestful.model.api.form.MessageSearchForm;

import java.util.List;

public interface MessageService {

//    List<Message> getAll(String sender, String receiver, String date);
//
//    List<Message> getAllByDay(String create_date);

    List<Message> search (MessageSearchForm searchForm);

    boolean create (Message message);
}
