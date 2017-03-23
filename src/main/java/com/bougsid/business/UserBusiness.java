package com.bougsid.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bougsid.dao.QuizRepository;
import com.bougsid.dao.UserCategoryRepository;
import com.bougsid.dao.UserRepository;
import com.bougsid.entities.Quiz;
import com.bougsid.entities.User;
import com.bougsid.entities.UserCategory;

/**
 * Created by bougsid.ayoub on 3/6/2017.
 */
@Service
public class UserBusiness {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private UserCategoryRepository userCategoryRepository;

	public List<User> getUsersOfQuiz(String quizId) {
		Quiz quiz = this.quizRepository.findOne(quizId);
		List<User> users = this.userRepository.findByIdIn(quiz.getUsers());
		users.forEach(user -> {
			Quiz userQuiz = user.getQuizzes().stream().filter(q -> q.getId().equals(quizId)).findFirst().get();
			user.getQuizzes().clear();
			user.addQuiz(userQuiz);
		});
		return users;
	}

	public Page<UserCategory> getUserCategoryPage(int page, int size) {
		return this.userCategoryRepository.findAll(new PageRequest(page, size));
	}

	public UserCategory saveUserCategory(UserCategory userCategory) {
		return this.userCategoryRepository.save(userCategory);
	}
	// public List<User> affectQuizToUsers(Quiz quiz, List<User> users) {
	// users.forEach(user -> {
	// this.affectQuizToUser(quiz, user);
	// });
	// this.affectQuizToUser(quiz,users)
	// this.userRepository.save(users);
	// return users;
	// }
	//
	// private void affectQuizToUser(Quiz quiz, User user) {
	// if(user.getQuizzes()==null)
	// user.setQuizzes(new ArrayList<>());
	// if(user.getQuizzes().contains(quiz)){
	//
	// }
	// }
}
