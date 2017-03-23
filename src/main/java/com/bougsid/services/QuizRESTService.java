package com.bougsid.services;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bougsid.business.QuizBusiness;
import com.bougsid.dao.QuizRepository;
import com.bougsid.entities.Quiz;
import com.bougsid.entities.User;
import com.bougsid.util.LoggedUserUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by bougsid.ayoub on 2/24/2017.
 */
@RestController
@RequestMapping("/api/quiz")
public class QuizRESTService {
	@Autowired
	private LoggedUserUtil loggedUserUtil;

	@Autowired
	private QuizRepository repository;
	@Autowired
	private QuizBusiness metier;

	@GetMapping("/{id}")
	public ResponseEntity<Quiz> getQuizOfUser(final HttpServletRequest request,
			@PathVariable(name = "id") String quizId) {
		Quiz quiz = this.metier.getQuizOfUser(this.loggedUserUtil.getUser(request), quizId);
		if (quiz != null) {
			return new ResponseEntity<>(quiz, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public Quiz addQuiz(@RequestBody Quiz quiz) {
		return this.repository.save(quiz);
	}

	@GetMapping("/{page}/{size}")
	public Page<Quiz> getAll(@PathVariable(name = "page") int page, @PathVariable(name = "size") int size) {
		return this.repository.findAll(new PageRequest(page, size));
	}

	@GetMapping("/user-quizzes")
	public Page<Quiz> getQuizzesOfUser(final HttpServletRequest request, @RequestParam(name = "page") int page,
			@RequestParam(name = "size") int size) {
		return this.metier.getQuizzesOfUser(this.loggedUserUtil.getUser(request), new PageRequest(page, size));
	}

	@PostMapping("/affect-quiz")
	public List<User> affectQuizToUsers(@RequestBody JsonNode node) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Quiz quiz = mapper.treeToValue(node.get(1), Quiz.class);
		String[] userArray = mapper.treeToValue(node.get(0), String[].class);
		List<String> usersID = Arrays.asList(userArray);
		System.out.println(quiz);
		System.out.println(Arrays.toString(userArray));
		return this.metier.affectQuizToUsers(quiz, usersID);
	}


}
