package com.spiralg.chatrestful.repository.impl;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.api.form.FriendSearchForm;
import com.spiralg.chatrestful.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("friendRepository")
public class FriendRepositoryImpl implements FriendRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean create(Friend friend) {
        String sql = "INSERT INTO friends(user_id, user_friend_id) VALUES(:userId, :userFriendId)";

        return false;
    }

    @Override
    public List<Friend> search(FriendSearchForm searchForm) {
        return null;
    }
}
