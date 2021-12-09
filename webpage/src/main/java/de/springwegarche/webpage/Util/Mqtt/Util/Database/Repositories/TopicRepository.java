package de.springwegarche.webpage.Util.Mqtt.Util.Database.Repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import de.springwegarche.webpage.Util.Mqtt.Util.Database.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long>{
    Topic findById(long id);

    // parent == null
    @Query("select t from Topic t where parent_id = 0 AND topic_name = ?1")
    List<Topic> findFirstTopicWith(String topicName);

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
