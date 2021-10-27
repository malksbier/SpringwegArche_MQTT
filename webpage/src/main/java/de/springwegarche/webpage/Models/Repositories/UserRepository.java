package de.springwegarche.webpage.Models.Repositories;

import de.springwegarche.webpage.Models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
