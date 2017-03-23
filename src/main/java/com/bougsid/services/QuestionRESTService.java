package com.bougsid.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bougsid.dao.QuestionRepository;
import com.bougsid.dao.SubjectRepository;
import com.bougsid.entities.Question;
import com.bougsid.entities.Subject;

/**
 * Created by bougsid.ayoub on 2/24/2017.
 */
@RestController
@RequestMapping("/api/question")

public class QuestionRESTService {
    @Autowired
    private QuestionRepository repository;
	@Autowired
	private SubjectRepository subjectRepository;
    @PostMapping
    public Question addQuestion(@RequestBody Question question) {
        return this.repository.save(question);
    }
    
	@GetMapping("/subjects")
	private List<Subject> getSubjects() {
		return this.subjectRepository.findAll();
	}
}
