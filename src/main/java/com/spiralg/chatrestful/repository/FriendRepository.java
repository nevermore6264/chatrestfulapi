package com.spiralg.chatrestful.repository;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.api.form.FriendSearchForm;

import java.util.List;

public interface FriendRepository {
    boolean create(Friend friend);
    List<Friend> search(FriendSearchForm searchForm);
}
