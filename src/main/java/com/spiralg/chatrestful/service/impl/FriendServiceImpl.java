package com.spiralg.chatrestful.service.impl;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.repository.FriendRepository;
import com.spiralg.chatrestful.repository.impl.FriendRepositoryImpl;
import com.spiralg.chatrestful.service.FriendService;
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

    @Override
    public boolean create(Friend friend) {
        return friendRepository.create( friend );
    }

    @Override
    public List<User> getAllFriend(String userName) {
        try {
            String sql = "SELECT friends.id, a.user_name" +
                    "  FROM friends JOIN users a ON friends.user_friend_id = a.id"  +
                    "  JOIN users b ON friends.user_id = b.id"  +
                    "  WHERE b.user_name = :user_name and status=1";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("user_name",  userName);
            List<User> users = jdbcTemplate.query(sql, params, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User u = new User();
                    u.setUserName(rs.getString("user_name"));
                    u.setId(rs.getInt("id"));
                    return u;
                }
            });
            return users;
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
