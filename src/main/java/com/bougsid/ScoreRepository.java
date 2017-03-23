package com.bougsid;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bougsid.entities.Score;

@RepositoryRestResource
public interface ScoreRepository extends MongoRepository<Score, String> {

	Score findByUserAndQuiz(String user, String quiz);
}
