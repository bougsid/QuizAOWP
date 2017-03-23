package com.bougsid.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bougsid.business.UserBusiness;
import com.bougsid.dao.UserRepository;
import com.bougsid.entities.User;
import com.bougsid.entities.UserCategory;

/**
 * Created by bougsid.ayoub on 2/24/2017.
 */
@RestController
@RequestMapping("/api/user")

public class UserRESTService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private UserBusiness userMetier;
	// @Autowired
	// private QuizUserAssociationRepository quizUserAssociationRepository;

	@GetMapping
	public List<User> getAll() {
		return this.repository.findAll();
	}

	@PutMapping
	public List<User> saveAll(@RequestBody List<User> users) {
		return this.repository.save(users);
	}

	@GetMapping("/scoreboard/{id}")
	List<User> getUsersOfQuiz(@PathVariable(name = "id") String quizId) {
		return this.userMetier.getUsersOfQuiz(quizId);
	}

	@PostMapping("/category")
	public UserCategory saveUserCategory(@RequestBody UserCategory userCategory) {
		return this.userMetier.saveUserCategory(userCategory);
	}

	@PutMapping("/category")
	public UserCategory updateUserCategory(@RequestBody UserCategory userCategory) {
		return this.userMetier.saveUserCategory(userCategory);
	}
	
	@GetMapping("/category")
	public Page<UserCategory> getUserCategoryPage(int page, int size) {
		return this.userMetier.getUserCategoryPage(page, size);
	}
}
