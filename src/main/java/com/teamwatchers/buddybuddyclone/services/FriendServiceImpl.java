package com.teamwatchers.buddybuddyclone.services;

import com.teamwatchers.buddybuddyclone.daos.FriendDao;
import com.teamwatchers.buddybuddyclone.models.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    private final FriendDao friendDao;

    public FriendServiceImpl(FriendDao friendDao) {
        this.friendDao = friendDao;
    }

    @Override
    public Friend getFriend(long userId, long friendId) {
        return friendDao.findByUserAndFriend(userId, friendId);
    }
}
