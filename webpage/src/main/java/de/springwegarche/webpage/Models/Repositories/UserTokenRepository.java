package de.springwegarche.webpage.Models.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import de.springwegarche.webpage.Util.Security.UserToken.UserToken;

public interface UserTokenRepository extends JpaRepository<UserToken, Long>{
    UserToken findById(long id);
}
