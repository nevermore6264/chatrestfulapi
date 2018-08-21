package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

//    public boolean save(Users users);
//
//    public Users findById(int id);
//
//    Users findByUsername(String userName);

    List<Users> findAllByName(String name);

}
