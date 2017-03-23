package com.bougsid.entities;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Scope("prototype")
@Component
public class Score {
	@Id
	private String id;
	private String quiz;
	private String user;
	private float score;
	private Map<QuestionLevel, Integer> scoreByLevel;
	private Map<String, Float> scoreBySubject;
	
	public Score() {
		super();
		this.scoreByLevel = new HashMap<>();
		this.scoreBySubject = new HashMap<>();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuiz() {
		return quiz;
	}
	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public Map<QuestionLevel, Integer> getScoreByLevel() {
		return scoreByLevel;
	}
	public void setScoreByLevel(Map<QuestionLevel, Integer> scoreByLevel) {
		this.scoreByLevel = scoreByLevel;
	}
	public Map<String, Float> getScoreBySubject() {
		return scoreBySubject;
	}
	public void setScoreBySubject(Map<String, Float> scoreBySubject) {
		this.scoreBySubject = scoreBySubject;
	}

}
