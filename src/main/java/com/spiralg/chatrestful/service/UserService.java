package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.User;

import java.util.List;

public interface UserService {

    boolean save(User user);

//  public User findById(int id);

    String findByName(String userName);

    List<User> findAllByName(String name);

}
