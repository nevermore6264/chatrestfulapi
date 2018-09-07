package com.spiralg.chatrestful.rowmapper;

import com.spiralg.chatrestful.model.api.view.InvitationView;
import com.spiralg.chatrestful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvitationRowMapper implements RowMapper {

    @Autowired
    UserService userService;

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        InvitationView invitationView = new InvitationView();
        invitationView.setId( rs.getInt( "user_id" ) );
        invitationView.setName(rs.getString( "user_name" ));
        return invitationView;
    }
}
