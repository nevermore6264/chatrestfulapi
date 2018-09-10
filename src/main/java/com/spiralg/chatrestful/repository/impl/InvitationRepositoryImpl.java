package com.spiralg.chatrestful.repository.impl;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.api.form.AcceptForm;
import com.spiralg.chatrestful.model.api.form.InvitationForm;
import com.spiralg.chatrestful.repository.InvitationRepository;
import com.spiralg.chatrestful.rowmapper.InvitationRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("invitationRepository")
public class InvitationRepositoryImpl implements InvitationRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<Friend> getAllInvitations(InvitationForm invitationForm) {
        String sql = "SELECT user_id , user_name FROM friends INNER  JOIN users on friends.user_id = users.id WHERE user_friend_id =:userFriend and friends.status = 0";
        MapSqlParameterSource params = new MapSqlParameterSource( );
        params.addValue( "userFriend",invitationForm.getUserFriend() );
        return jdbcTemplate.query( sql,params, new InvitationRowMapper() );
    }

    @Override
    public int acceptInvitations(AcceptForm acceptForm) {
        String sql = "UPDATE friends SET status = 1 WHERE user_id = :user_id AND user_friend_id = :user_friend_id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "user_id", acceptForm.getUserId() );
        params.addValue( "user_friend_id", acceptForm.getUserFriend() );
        return jdbcTemplate.update( sql, params );
    }
}
