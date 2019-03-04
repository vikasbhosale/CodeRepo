package com.concerto.demo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.concerto.demo.model.Player;

@Repository
public interface PlayerRepository /* extends CrudRepository<Player, Long> */ {

	List<Player> findByTeamId(long teamId);
	
	public void savePlayer(Player player);
}
