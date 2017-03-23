package com.bougsid.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.bougsid.entities.Subject;


@RepositoryRestResource
public interface SubjectRepository extends MongoRepository<Subject, String>{

}
