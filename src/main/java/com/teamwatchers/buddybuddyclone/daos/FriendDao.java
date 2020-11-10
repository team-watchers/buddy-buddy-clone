package com.teamwatchers.buddybuddyclone.daos;

import com.teamwatchers.buddybuddyclone.models.Friend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendDao extends CrudRepository<Friend, Long> {
    @Query("select f from Friend f where f.user.id=:userId and f.friend.id=:friendId")
    Friend findByUserAndFriend(@Param("userId") Long userId, @Param("friendId")Long friendId);
}
