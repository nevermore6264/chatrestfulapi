package com.spiralg.chatrestful.service.impl;

import com.spiralg.chatrestful.model.Message;
import com.spiralg.chatrestful.model.api.form.MessageForm;
import com.spiralg.chatrestful.model.api.form.MessageSearchForm;
import com.spiralg.chatrestful.repository.MessageRepository;
import com.spiralg.chatrestful.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> search(MessageSearchForm searchForm) {
        return messageRepository.search(searchForm);
    }

    @Override
    public boolean create(Message message) {
        return messageRepository.create(message);
    }
}
