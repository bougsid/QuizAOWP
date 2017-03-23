package com.bougsid.dao;

import com.bougsid.entities.Option;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
/**
 * Created by bougsid.ayoub on 2/23/2017.
 */
@RepositoryRestResource
public interface AnswerRepository extends MongoRepository<Option, String> {
}
