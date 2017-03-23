package com.bougsid.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bougsid.dao.QuizRepository;
import com.bougsid.dao.UserRepository;
import com.bougsid.entities.Quiz;
import com.bougsid.entities.User;

/**
 * Created by bougsid.ayoub on 2/24/2017.
 */
@Service
public class QuizBusiness {
	@Autowired
	private ApplicationContext context;
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private UserRepository userRepository;

	public Quiz getQuizOfUser(User user, String quizId) {
		Quiz quiz = this.getUserQuizWithID(user, quizId);
		if (this.userHasThisQuizActive(quiz))
			return quiz;
		return null;
	}

	private boolean userHasThisQuizActive(Quiz quiz) {
		return quiz.isActive();
	}

	public Page<Quiz> getQuizzesOfUser(User user, Pageable pageable) {
		List<Quiz> quizzes = user.getQuizzes();
		return this.getPageFromList(quizzes, pageable);
	}

	public List<User> affectQuizToUsers(Quiz quiz, List<String> usersId) {
		List<User> users = new ArrayList<>();
		usersId.forEach(userId -> users.add(this.affectQuizToUser(quiz, userId)));
		this.userRepository.save(users);
		this.affectUsersToQuizAndSave(quiz, users);
		return users;
	}

	private User affectQuizToUser(Quiz quiz, String userId) {
		User user = this.userRepository.findOne(userId);
		if (user.getQuizzes() == null)
			user.setQuizzes(new ArrayList<>());
		if (user.getQuizzes().contains(quiz)) {
			this.updateQuizOfUser(quiz, user);
		} else {
			user.addQuiz(quiz);
		}
		return user;
	}

	private void affectUsersToQuizAndSave(Quiz quiz, List<User> users) {
		List<String> usersIds = users.stream().map(User::getId).collect(Collectors.toList());
		if (quiz.getUsers() == null)
			quiz.setUsers(new ArrayList<>());
		usersIds.forEach(userId -> {
			if (!quiz.getUsers().contains(userId))
				quiz.addUser(userId);
		});
		this.quizRepository.save(quiz);
	}

	private void updateQuizOfUser(Quiz newQuiz, User user) {
		int index = this.getQuizIndexOnUserQuizzes(newQuiz, user);
		if (index != -1)
			user.getQuizzes().set(index, newQuiz);
	}

	private int getQuizIndexOnUserQuizzes(Quiz searchedQuiz, User user) {
		return user.getQuizzes().indexOf(searchedQuiz);
	}

	private Quiz getUserQuizWithID(User user, String quizId) {
		Optional<Quiz> optionalQuiz = user.getQuizzes().stream().filter(q -> q.getId().equals(quizId)).findFirst();
		return (optionalQuiz.isPresent()) ? optionalQuiz.get() : null;
	}

	Quiz saveQuizForUser(Quiz quiz, User user) {
		quiz.setActive(false);
		this.updateQuizOfUser(quiz, user);
		this.userRepository.save(user);
		return quiz;
	}

	private Page<Quiz> getPageFromList(List<Quiz> quizzes, Pageable pageable) {
		int page = pageable.getPageNumber();
		int size = pageable.getPageSize();
		int firstIndex = page * size;
		int lastIndex = page * size + size;
		if (quizzes == null)
			quizzes = new ArrayList<>();
		if (lastIndex > quizzes.size())
			lastIndex = quizzes.size();
		return new PageImpl<>(quizzes.subList(firstIndex, lastIndex));
	}
}
