package com.example.dreamgames.Controller;

import com.example.dreamgames.Model.CreateTeamRequest;
import com.example.dreamgames.Model.JoinTeamRequest;
import com.example.dreamgames.Model.Team;
import com.example.dreamgames.Model.User;
import com.example.dreamgames.Repository.TeamRepository;
import com.example.dreamgames.Repository.UserRepository;
import com.example.dreamgames.Service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final TeamService teamService;

    public TeamController(TeamRepository teamRepository, UserRepository userRepository, TeamService teamService) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
        this.teamService = teamService;
    }



    /*@PostMapping("/create")
    public ResponseEntity<TeamResponse> createTeam(@RequestBody CreateTeamRequest request) {
        try {
            Team team = teamService.createTeam(request.getName(), request.getCreatorId());
            TeamResponse response = new TeamResponse(team.getId(), team.getName(), team.getCapacity());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (NotEnoughCoinsException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }*/

    @PostMapping
    public Team createTeam(@RequestBody CreateTeamRequest request) {
        Team team = new Team(request.getName());
        return teamRepository.save(team);
    }

    @PostMapping("/{id}/join")
    public Team joinTeam(@PathVariable("id") Long teamId, @RequestBody JoinTeamRequest request) {
        // Team team = teamService.joinTeam(teamId, request);
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (team.getUsers().size() < 20) {
            user.setTeam(team);
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Team is full");
        }

        return team;
    }

    @GetMapping("/random")
    public List<Team> getRandomTeamsWithEmptySlot() {
        List<Team> teamsWithEmptySlots = teamRepository.findByUsersSizeLessThan(20);
        if (teamsWithEmptySlots.size() <= 10) {
            return teamsWithEmptySlots;
        } else {
            return new ArrayList<>(new HashSet<>(teamsWithEmptySlots).stream()
                    .sorted((t1, t2) -> ThreadLocalRandom.current().nextInt(-1, 2))
                    .limit(10)
                    .collect(Collectors.toList()));
        }
    }
}
