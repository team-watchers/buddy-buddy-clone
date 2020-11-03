package com.teamwatchers.buddybuddyclone.daos;

import com.teamwatchers.buddybuddyclone.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao extends CrudRepository<Message, Long> {
}
