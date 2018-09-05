package com.spiralg.chatrestful.repository;

import com.spiralg.chatrestful.model.User;
import com.spiralg.chatrestful.model.api.form.UserSearchForm;

import java.util.List;

public interface UserRepository {
    boolean create(User user);
    User update(User user);
    void delete(Integer id);
    List<User> search(UserSearchForm searchForm);
    List<User> getAll(UserSearchForm searchForm);
    int findIdByUsername(String userName);
}
