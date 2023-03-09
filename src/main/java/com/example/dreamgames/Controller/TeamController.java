package com.example.dreamgames.Controller;

import com.example.dreamgames.Model.JoinTeamRequest;
import com.example.dreamgames.Model.Team;
import com.example.dreamgames.Model.User;
import com.example.dreamgames.Repository.TeamRepository;
import com.example.dreamgames.Service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @PostMapping("/teams")
    public Team createTeam(String teamName, User user) {
        return teamService.createTeam(teamName,user);
    }

    @PostMapping("/{id}/join")
    public Team joinTeam(@PathVariable("id") Long teamId, @RequestBody JoinTeamRequest request) {
        return teamService.joinTeam(teamId, request);
    }

    @GetMapping("/random")
    public List<Team> getRandomTeamsWithEmptySlot() {
        return teamService.getRandomTeamsWithEmptySlot();
    }
}
