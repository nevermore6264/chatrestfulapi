package com.spiralg.chatrestful.rowmapper;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.Token;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenRowMapper implements RowMapper<Token> {
    @Override
    public Token mapRow(ResultSet rs, int rowNum) throws SQLException {
        Token token = new Token();
        token.setId( rs.getInt( "id" ) );
        token.setKey( rs.getString( "key" ) );
        token.setExpectDate( rs.getString( "expire_date" ) );
        return token;
    }
}
