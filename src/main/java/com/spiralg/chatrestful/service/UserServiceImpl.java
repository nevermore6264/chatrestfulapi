package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean save(User user) {
        try {
            String sql = "INSERT INTO users(user_name) VALUES(:username)";
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue( "username", user.getUserName() );
            jdbcTemplate.update( sql, params );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> findAllByName(String name) {
        name.toLowerCase();
        try {
            String sql = "SELECT user_name, id, status FROM users WHERE user_name LIKE :username";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue( "username", "%" + name + "%" );
            List<User> users = jdbcTemplate.query( sql, parameters, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setUserName( rs.getString( "user_name" ) );
                    user.setId( rs.getInt( "id" ) );
                    user.setStatus( rs.getByte( "status" ) );
                    return user;
                }
            } );
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public User findByName(String userName) {
        try {
            String sql = "SELECT user_name FROM users WHERE user_name = :userName";
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue( "username", userName );
            User name = jdbcTemplate.queryForObject( sql, parameters, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setUserName( rs.getString( "user_name" ) );
                    return user;
                }
            } );
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return new User();
        }
    }
}
