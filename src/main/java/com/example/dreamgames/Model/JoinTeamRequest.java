package com.example.dreamgames.Model;

public class JoinTeamRequest {
    private Long teamId;

    private Long userId;

    public JoinTeamRequest() {}

    public JoinTeamRequest(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
