package com.bougsid.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bougsid.business.ScoreBusiness;
import com.bougsid.entities.Quiz;
import com.bougsid.entities.Score;
import com.bougsid.util.LoggedUserUtil;

@RestController
@RequestMapping("/api/score")
public class ScoreRESTService {
	@Autowired
	private ScoreBusiness scoreBusiness;
	@Autowired
	private LoggedUserUtil loggedUserUtil;

	@GetMapping("/{userId}/{quizId}")
	public Score getScore(@PathVariable(name = "userId") String user,@PathVariable(name = "quizId") String quiz) {
		Score score = this.scoreBusiness.getScoreOfUserOnQuiz(user, quiz);
		return score;
	}

	@PostMapping
	public float correctQuiz(final HttpServletRequest request, @RequestBody Quiz quiz) {
		return this.scoreBusiness.correctQuizAndSaveResultForUser(quiz, this.loggedUserUtil.getUser(request))
				.getScore();
	}
}
