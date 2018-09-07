package com.spiralg.chatrestful.repository.impl;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.api.form.FriendSearchForm;
import com.spiralg.chatrestful.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("friendRepository")
public class FriendRepositoryImpl implements FriendRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean create(Friend friend) {
        try {
            String sql = "INSERT INTO friends(user_id, user_friend_id, status) VALUES(:userId, :userFriendId, :status)";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("userId", friend.getUserId());
            params.addValue("userFriendId", friend.getUserFriend());
            params.addValue("status", friend.getStatus());
            jdbcTemplate.update(sql, params);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Friend> search(FriendSearchForm searchForm) {
        return null;
    }
}
