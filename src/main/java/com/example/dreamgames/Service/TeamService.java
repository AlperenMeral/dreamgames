package com.example.dreamgames.Service;

import com.example.dreamgames.Exception.NotEnoughCoinsException;
import com.example.dreamgames.Model.Team;
import com.example.dreamgames.Exception.TeamFullException;
import com.example.dreamgames.Model.User;
import com.example.dreamgames.Repository.TeamRepository;
import com.example.dreamgames.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;

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

    public Team joinTeam(long teamId, User user) throws TeamFullException {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            if (team.isFull()) {
                throw new TeamFullException("Team is Full");
            }
            if (team.addUser(user)) {
                return teamRepository.save(team);
            }
        }
        return null;
    }

    public List<Team> getTeamsWithEmptySlots() {
        List<Team> teams = teamRepository.findByCapacityGreaterThanAndMembersIsNull(0);
        Collections.shuffle(teams);
        return teams.subList(0, Math.min(teams.size(), 10));
    }
}
