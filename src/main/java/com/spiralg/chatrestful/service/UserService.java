package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.User;

import java.util.List;

public interface UserService {

    boolean save(User user);

    List<User> findAllByName(String name);

    User findByName(String userName);
}
