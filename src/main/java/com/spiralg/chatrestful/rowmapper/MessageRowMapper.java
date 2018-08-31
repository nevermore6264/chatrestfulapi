package com.spiralg.chatrestful.rowmapper;

import com.spiralg.chatrestful.model.Message;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageRowMapper implements RowMapper<Message> {

    @Override
    public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
        Message message = new Message();
        message.setId(rs.getInt("id"));
        message.setMessage(rs.getString("content"));
        message.setSender(rs.getString("sender"));
        message.setReceiver(rs.getString("receiver"));
        message.setTime(rs.getString("create_date"));
        System.out.println(rs.getString("receiver"));
        return message;
    }
}
