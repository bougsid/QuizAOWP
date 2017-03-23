package com.bougsid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by bougsid.ayoub on 2/23/2017.
 */
@Entity
@Scope("prototype")

public class Question {
    @Id

    private String id;
    private String content;
    private Subject subject;
    private float duration;
    @Enumerated(EnumType.STRING)
    private QuestionLevel level;
    private List<Option> options;

    public Question() {
    }

    public Question(String content, List<Option> options) {
        this.content = content;
        this.options = options;
    }

    @JsonIgnore
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public QuestionLevel getLevel() {
		return level;
	}

	public void setLevel(QuestionLevel level) {
		this.level = level;
	}
    
}
