package com.spiralg.chatrestful.service;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.model.User;

import java.util.List;

public interface FriendService {
    boolean create(Friend friend);

    List<User> getAllFriend(String userName);
}