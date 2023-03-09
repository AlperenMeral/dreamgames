package com.example.dreamgames.Service;

import com.example.dreamgames.Exception.NotEnoughCoinsException;
import com.example.dreamgames.Model.JoinTeamRequest;
import com.example.dreamgames.Model.Team;
import com.example.dreamgames.Exception.TeamFullException;
import com.example.dreamgames.Model.User;
import com.example.dreamgames.Repository.TeamRepository;
import com.example.dreamgames.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final UserRepository userRepository;

    public TeamService(TeamRepository teamRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.userRepository = userRepository;
    }

    public Team createTeam(String teamName, User user) throws NotEnoughCoinsException {
        if (user.getCoins() < 1000) {
            throw new NotEnoughCoinsException("Not Enough Coin");
        }

        Team team = new Team(teamName, 20);
        team.addUser(user);

        user.setCoins(user.getCoins() - 1000);
        userRepository.save(user);

        return teamRepository.save(team);
    }

    public Team joinTeam(long teamId, JoinTeamRequest request) throws TeamFullException {
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
