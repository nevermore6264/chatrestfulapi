package com.spiralg.chatrestful.repository.impl;

import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.model.api.form.UserSearchForm;
import com.spiralg.chatrestful.repository.UserRepository;
import com.spiralg.chatrestful.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO users(user_name) VALUES(:username)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue( "username", user.getUserName() );
        int result = jdbcTemplate.update( sql,params );
        return result == 1;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<User> search(UserSearchForm searchForm) {
        String sql = "SELECT user_name, id FROM users WHERE user_name LIKE :username";
        MapSqlParameterSource params = new MapSqlParameterSource(  );
        params.addValue( "username", "%"+searchForm.getUserName()+"%");

        return jdbcTemplate.query( sql, params, new UserRowMapper());
    }
}
