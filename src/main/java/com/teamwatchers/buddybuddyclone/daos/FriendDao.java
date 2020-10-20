package com.teamwatchers.buddybuddyclone.daos;

import com.teamwatchers.buddybuddyclone.models.Friend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendDao extends CrudRepository<Friend, Long> {}
