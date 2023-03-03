package com.example.dreamgames.Repository;

import com.example.dreamgames.Model.Team;
import com.example.dreamgames.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByUsersSizeLessThan(int maxSize);
    Optional<Team> findById(Long id);
    Team save(Team team);
    List<Team> findByCapacityGreaterThanAndMembersIsNull(int capacity);

}
