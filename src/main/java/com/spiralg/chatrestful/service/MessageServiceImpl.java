package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Message> getAll(String sender, String receiver, String time){
        sender.toLowerCase();
        receiver.toLowerCase();
        try {
            String sql = "SELECT messages.id, content,create_date,a.user_name AS sender,b.user_name AS receiver " +
                    "FROM messages JOIN users a ON messages.sender_id = a.id " +
                    "JOIN users b ON messages.receiver_id = b.id " +
                    "WHERE a.user_name = ifnull(:sender, a.user_name) AND b.user_name = ifnull(:receiver, b.user_name)  AND create_date LIKE :time";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("sender", "%" + sender + "%");
            params.addValue("receiver", "%" + receiver + "%");
            params.addValue("time" ,"%" + time + "%");
            List<Message> messages = jdbcTemplate.query(sql, params, new RowMapper<Message>() {
               @Override
                public Message mapRow(ResultSet rs, int rowNum) throws SQLException{
                   Message message = new Message();
                   message.setId(rs.getInt("id"));
                   message.setSender(rs.getString("sender"));
                   message.setReceiver(rs.getString("receiver"));
                   message.setTime(rs.getString("time"));
                   return message;
               }
            });
            return messages;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

//    @Override
//    public List<Message> getAllById(int id){
//        List<Message> messages = new LinkedList<>();
//        try{
//            String sql = "SELECT messages.content,create_date,a.user_name AS sender,b.user_name AS receiver " +
//                    "FROM messages JOIN users a ON messages.sender_id = a.id " +
//                    "JOIN users b ON messages.receiver_id = b.id " +
//                    "WHERE a.id = :id";
//            MapSqlParameterSource params = new MapSqlParameterSource();
//            params.addValue("id", id);
//            messages = jdbcTemplate.query(sql, params, new RowMapper<Message>(){
//                @Override
//                public Message mapRow(ResultSet rs, int rowNum) throws SQLException{
//                    Message message = new Message();
//                    message.setMessage(rs.getString("message"));
//                    message.setSender(rs.getString("sender"));
//                    message.setReceiver(rs.getString("receiver"));
//                    message.setTime(rs.getString("time"));
//                    return message;
//                }
//            } );
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return messages;
//    }
}
