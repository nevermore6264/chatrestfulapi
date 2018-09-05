package com.spiralg.chatrestful.repository.impl;

import com.spiralg.chatrestful.model.Token;
import com.spiralg.chatrestful.repository.TokenRepository;
import com.spiralg.chatrestful.rowmapper.TokenRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepositoryImpl implements TokenRepository {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public Token getByUserId(int userId) {
        try {
            String sql = "SELECT tokens.id,`key`,expire_date FROM tokens WHERE user_id = :userId and expire_date > now();";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("userId", userId);
            return jdbcTemplate.queryForObject(sql, params, new TokenRowMapper());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
