package com.bougsid.business;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.bougsid.ScoreRepository;
import com.bougsid.entities.Option;
import com.bougsid.entities.Question;
import com.bougsid.entities.QuestionLevel;
import com.bougsid.entities.Quiz;
import com.bougsid.entities.Score;
import com.bougsid.entities.Subject;
import com.bougsid.entities.User;

@Service
public class ScoreBusiness {

	@Autowired
	private ApplicationContext context;
	@Autowired
	private ScoreRepository scoreRepository;
	@Autowired
	private QuizBusiness quizBusiness;

	public Score getScoreOfUserOnQuiz(String user, String quiz) {
		return this.scoreRepository.findByUserAndQuiz(user, quiz);
	}

	public Score correctQuizAndSaveResultForUser(Quiz quiz, User user) {
		this.quizBusiness.saveQuizForUser(quiz, user);
		return this.correctQuiz(quiz, user);
	}

	private Score correctQuiz(Quiz quiz, User user) {
		Score score = context.getBean(Score.class);
		score.setQuiz(quiz.getId());
		score.setUser(user.getId());
		Long correctQuestions = 0L;
		for (Question question : quiz.getQuestions()) {
			boolean questionCorrect = questionCorrect(question);
			if (questionCorrect) {
				correctQuestions++;
			}
			this.updateScoreByLevel(score, question.getLevel(), questionCorrect);
			this.updateScoreBySubject(score, question.getSubject(), questionCorrect);
		}
		this.setScore(score, correctQuestions, (long) quiz.getQuestions().size());
		this.updateScoreBySubjectWithPercent(score, quiz);
		this.scoreRepository.save(score);
		return score;
	}

	private void updateScoreBySubjectWithPercent(Score score, Quiz quiz) {
		Map<String, Long> subjects = quiz.getQuestions().stream().map(q -> q.getSubject().getName())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		subjects.forEach((subject, questionsBySubject) -> {
			Map<String, Float> scoreBySubject = score.getScoreBySubject();
			scoreBySubject.put(subject, this.getPercent(scoreBySubject.get(subject), questionsBySubject));
		});
	}

	private void updateScoreByLevel(Score score, QuestionLevel level, boolean questionCorrect) {
		Integer correctQuestions = score.getScoreByLevel().getOrDefault(level, 0);
		if (questionCorrect)
			correctQuestions++;
		score.getScoreByLevel().put(level, correctQuestions);
	}

	private void updateScoreBySubject(Score score, Subject subject, boolean questionCorrect) {
		Float correctQuestions = score.getScoreBySubject().getOrDefault(subject, Float.valueOf(0));
		if (questionCorrect)
			correctQuestions++;
		score.getScoreBySubject().put(subject.getName(), correctQuestions);
	}

	private void setScore(Score score, Long correctQuestions, Long allQuestions) {
		score.setScore(this.getPercent(Float.valueOf(correctQuestions), allQuestions));
	}

	private float getPercent(Float correctQuestions, Long allQuestions) {
		return (correctQuestions * 100) / allQuestions;
	}

	private boolean questionCorrect(Question question) {
		boolean correct = true;
		for (Option option : question.getOptions()) {
			if (optionNotCorrect(option)) {
				correct = false;
				break;
			}
		}
		return correct;
	}

	private boolean optionNotCorrect(Option option) {
		return (option.isAnswer() != option.isCorrect());
	}

}
