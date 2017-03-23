package com.bougsid.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by bougsid.ayoub on 2/27/2017.
 */
@Entity
public class Role {
    @Id

    private String id;
    private String name;

    @ManyToMany
    private List<User> users;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void removeUser(User user){
        this.users.remove(user);
    }
}
