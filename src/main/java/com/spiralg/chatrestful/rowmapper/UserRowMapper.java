package com.spiralg.chatrestful.rowmapper;

import com.spiralg.chatrestful.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId( rs.getInt( "id" ) );
        user.setUserName( rs.getString( "user_name" ) );
        return user;
    }
}
