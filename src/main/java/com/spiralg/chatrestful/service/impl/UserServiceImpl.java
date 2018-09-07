package com.spiralg.chatrestful.service.impl;

import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.model.api.form.UserSearchForm;
import com.spiralg.chatrestful.repository.UserRepository;
import com.spiralg.chatrestful.service.UserService;
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
    private UserRepository userRepository;

    @Override
    public List<User> search(UserSearchForm searchForm){
        return userRepository.search( searchForm );
    }

    @Override
    public boolean create(User user){
        return userRepository.create( user );
    }

    @Override
    public List<User> getAll(UserSearchForm searchForm){
        return userRepository.getAll( searchForm );
    }

    @Override
    public int findIdByUsername(String userName) {
        return userRepository.findIdByUsername( userName );
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

}
