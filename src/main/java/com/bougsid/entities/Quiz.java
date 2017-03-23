package com.bougsid.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;

/**
 * Created by bougsid.ayoub on 2/23/2017.
 */
@Entity
@Scope("prototype")
public class Quiz {
    @Id
    private String id;
    private String title;
    private List<Question> questions;
    private float duration;
    private boolean active;
    List<String> users;

    public Quiz() {
    }

    public Quiz(String title, List<Question> questions, float duration) {
        this.title = title;
        this.questions = questions;
        this.duration = duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

	public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
//
//    public float getScore() {
//        return score;
//    }
//
//    public void setScore(float score) {
//        this.score = score;
//    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
    public void addUser(String user){
    	this.users.add(user);
    }
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quiz other = (Quiz) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", questions=" + questions +
                ", duration=" + duration +
//                ", users=" + users +
                '}';
    }
}
