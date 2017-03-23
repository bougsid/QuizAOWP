package com.bougsid.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bougsid.entities.User;

/**
 * Created by bougsid.ayoub on 2/23/2017.
 */
@RepositoryRestResource
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
    
    List<User> findByIdIn(List<String> ids);

}
