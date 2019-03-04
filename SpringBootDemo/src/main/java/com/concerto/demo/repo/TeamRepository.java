package com.concerto.demo.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.concerto.demo.model.Team;
@Repository
public interface TeamRepository extends /* CrudRepository<Team, Long>, */JpaRepository<Team, Long> {
    Team findByPlayers(long playerId);
}
