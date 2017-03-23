package com.bougsid.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by bougsid.ayoub on 2/23/2017.
 */

@Entity
@Scope("prototype")

public class Option {
    @Id
    private String id;
    private String content;
    private boolean correct;
    private boolean answer;

    public Option() {
    }

    public Option(String content, boolean correct, Question question) {
        this.content = content;
        this.correct = correct;
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

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
