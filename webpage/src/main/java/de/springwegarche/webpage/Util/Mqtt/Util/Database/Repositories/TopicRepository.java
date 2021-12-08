package de.springwegarche.webpage.Util.Mqtt.Util.Database.Repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>{
    Topic findById(long id);
    /*
        User findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update User u set u.token = ?1 where u.username = ?2")
    int setUserToken(String token, String username);

    @Modifying
    @Transactional
    @Query("update User u set u.password = ?1 where u.username = ?2")
    int setUserPassword(String password, String username);
    */ 
}
