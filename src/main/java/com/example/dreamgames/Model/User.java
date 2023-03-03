package com.example.dreamgames.Model;

import jakarta.persistence.*;

@Entity
@Table
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private int level = 1;
    @Column(nullable = false)
    private int coins = 5000;
    @ManyToOne
    private Team team;

    public User() {}

    public User(int level, int coins) {
        this.level = level;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}