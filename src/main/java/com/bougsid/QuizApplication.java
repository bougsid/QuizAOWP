package com.bougsid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.bougsid.dao.QuizRepository;
import com.bougsid.dao.SubjectRepository;
import com.bougsid.dao.UserCategoryRepository;
import com.bougsid.dao.UserRepository;
import com.bougsid.entities.Option;
import com.bougsid.entities.Question;
import com.bougsid.entities.QuestionLevel;
import com.bougsid.entities.Quiz;
import com.bougsid.entities.Role;
import com.bougsid.entities.Subject;
import com.bougsid.entities.User;
import com.bougsid.entities.UserCategory;
import com.bougsid.jwt.JwtFilter;

@SpringBootApplication

public class QuizApplication implements CommandLineRunner {
	@Autowired
	private QuizRepository repository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserCategoryRepository userCategoryRepository;
	@Autowired
	private SubjectRepository subjectRepository;
	// @Autowired
	// private QuizUserAssociationRepository quizUserAssociationRepository;

	public static void main(String[] args) {
		SpringApplication.run(QuizApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}

	@Override
	public void run(String... strings) throws Exception {

		Subject subject = new Subject();
		subject.setName("JAVA");
		Subject subject2 = new Subject();
		subject2.setName("Spring");
		Subject subject3 = new Subject();
		subject3.setName("Angular");
		this.subjectRepository.save(subject);
		this.subjectRepository.save(subject2);
		this.subjectRepository.save(subject3);

		User user = new User();
		user.setUsername("bougsid");
		user.setPassword("bougsid");
		List<Role> roles = new ArrayList<>();
		roles.add(new Role("user"));
		roles.add(new Role("admin"));
		user.setRoles(roles);
		userRepository.save(user);
		User user2 = new User();
		user2.setUsername("ayoub");
		user2.setPassword("ayoub");
		List<Role> r = new ArrayList<>();
		r.add(new Role("user"));
		user2.setRoles(r);
		userRepository.save(user2);

		UserCategory userCategory = new UserCategory();
		userCategory.addUser(user.getId());
		userCategory.setName("Category 1");
		userCategoryRepository.save(userCategory);

		List<Quiz> quizzes = new ArrayList<>();

		for (int i = 0; i < 2; i++) {
			List<Question> questions = new ArrayList<>();
			Quiz quiz = new Quiz("Quiz" + i, questions, (float) 3600);
			quizzes.add(quiz);
			for (int j = 0; j < 2; j++) {
				List<Option> options = new ArrayList<>();
				Question question = new Question("Question " + 1, options);
				if (j % 2 == 0)
				{
					question.setSubject(subject);
					question.setLevel(QuestionLevel.ADVANCED);
				}
				else{
					question.setLevel(QuestionLevel.BASIC);
					question.setSubject(subject3);
				}
				for (int k = 0; k < 2; k++) {
					options.add(new Option("Option" + k, (k % 2) == 0, question));
				}
				questions.add(question);
			}
		}
		repository.save(quizzes);
	}
}
