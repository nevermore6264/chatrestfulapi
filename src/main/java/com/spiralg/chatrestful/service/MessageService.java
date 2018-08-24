package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAll(String sender, String receiver, String date);

    List<Message> getAllByDay(String date);

//    List<Message> getAllById(int id);
}
