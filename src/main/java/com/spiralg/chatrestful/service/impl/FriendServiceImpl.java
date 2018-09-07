package com.spiralg.chatrestful.service.impl;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.repository.FriendRepository;
import com.spiralg.chatrestful.repository.UserRepository;
import com.spiralg.chatrestful.repository.impl.FriendRepositoryImpl;
import com.spiralg.chatrestful.service.FriendService;
import com.spiralg.chatrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    UserService userService;

    @Override
    public boolean create(Friend friend) {
        return friendRepository.create( friend );
    }

    @Override
    public List<User> getAllFriend(String userName) {
        int userID = userService.findIdByUsername(userName);
        try {
            String sql = "SELECT user_id, user_friend_id FROM friends WHERE user_id = :user or user_friend_id = :user AND status = 1;";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("user",  userID);
            List<User> users = jdbcTemplate.query(sql, params, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    if(rs.getInt("user_id") == userID){
                        return userService.getById(rs.getInt("user_friend_id"));
                    }else{
                        return userService.getById(rs.getInt("user_id"));
                    }
                }
            });
            return users;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
