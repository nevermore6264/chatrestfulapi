package com.spiralg.chatrestful.service.impl;

import com.spiralg.chatrestful.model.Friend;
import com.spiralg.chatrestful.repository.FriendRepository;
import com.spiralg.chatrestful.repository.impl.FriendRepositoryImpl;
import com.spiralg.chatrestful.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendRepository friendRepository;

    @Override
    public boolean create(Friend friend) {
        System.out.println(FriendRepositoryImpl.class+" den day" +friend.toString());
        return friendRepository.create( friend );
    }
}
