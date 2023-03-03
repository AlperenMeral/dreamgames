package com.example.dreamgames.Model;

import com.example.dreamgames.Exception.TeamFullException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "team")
    private List<User> users = new ArrayList<>();

    private int capacity = 20;

    public Team() {}

    public Team(String name) {
        this.name = name;
    }

    public Team(String teamName, int i) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean addUser(User user) {
        if (users.size() >= capacity) {
            throw new TeamFullException("Team is full, cannot add more users.");
        }
        users.add(user);
        return true;
    }
    public boolean isFull() {
        return users.size() >= capacity;
    }
}
