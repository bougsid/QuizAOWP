package com.bougsid.dao;

import com.bougsid.entities.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by bougsid.ayoub on 2/23/2017.
 */
@RepositoryRestResource
public interface QuestionRepository extends MongoRepository<Question, String> {
}
