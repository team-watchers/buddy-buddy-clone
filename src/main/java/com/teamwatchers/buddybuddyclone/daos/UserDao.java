package com.teamwatchers.buddybuddyclone.daos;

import com.teamwatchers.buddybuddyclone.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {}
