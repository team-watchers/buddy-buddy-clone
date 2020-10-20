package com.teamwatchers.buddybuddyclone.daos;

import com.teamwatchers.buddybuddyclone.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    User findFirstByUsernameEquals(String Username);
}
