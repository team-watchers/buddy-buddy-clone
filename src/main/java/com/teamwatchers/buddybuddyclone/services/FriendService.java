package com.teamwatchers.buddybuddyclone.services;

import com.teamwatchers.buddybuddyclone.models.Friend;

public interface FriendService {
    Friend getFriend(long userId, long friendId);
}
