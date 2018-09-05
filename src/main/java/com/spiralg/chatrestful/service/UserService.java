package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.model.api.form.UserSearchForm;

import java.util.List;

public interface UserService {

    List<User> search(UserSearchForm searchForm);

    boolean create(User user);

    List<User> getAll(UserSearchForm searchForm);

    int findIdByUsername(String userName);
}
