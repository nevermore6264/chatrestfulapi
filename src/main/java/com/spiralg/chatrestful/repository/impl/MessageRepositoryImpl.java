package com.spiralg.chatrestful.repository.impl;

import com.spiralg.chatrestful.model.Message;
import com.spiralg.chatrestful.model.api.form.MessageSearchForm;
import com.spiralg.chatrestful.repository.MessageRepository;
import com.spiralg.chatrestful.rowmapper.MessageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository("messageRepository")
public class MessageRepositoryImpl implements MessageRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean create(Message message) {
        String sql = "INSERT INTO messages(message, time, reciever, sender) VALUES(:message, :time, :reciever, :sender)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("username", message.getMessage());
        params.addValue("time", message.getTime());
        params.addValue("reciever", message.getReceiver());
        params.addValue("sender", message.getSender());
        int result = jdbcTemplate.update(sql, params);
        return result == 1;
    }

    @Override
    public Message update(Message message) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Message> search(MessageSearchForm searchForm) {
        String sql = "SELECT messages.id, content,create_date,a.user_name AS sender,b.user_name AS receiver " +
                "FROM messages JOIN users a ON messages.sender_id = a.id " +
                "JOIN users b ON messages.receiver_id = b.id";

        StringBuilder whereCondition = new StringBuilder();
        MapSqlParameterSource params = new MapSqlParameterSource();

        if (!StringUtils.isEmpty(searchForm.getMessage())) {
            whereCondition.append(" content like :content");
            params.addValue("content", searchForm.getMessage());
        }

        if (!StringUtils.isEmpty(searchForm.getSender())) {
            if (whereCondition.length() != 0) {
                whereCondition.append(" AND");
            }
            whereCondition.append(" a.user_name like :sender");
            params.addValue("sender", searchForm.getSender());
        }

        if (!StringUtils.isEmpty(searchForm.getReceiver())) {
            if (whereCondition.length() != 0) {
                whereCondition.append(" AND");
            }
            whereCondition.append(" b.user_name like :receiver");
            params.addValue("receiver", searchForm.getReceiver());
        }

        sql += whereCondition.length() == 0 ? "" : " WHERE" + whereCondition.toString();

        return jdbcTemplate.query(sql, params, new MessageRowMapper());
    }
}
