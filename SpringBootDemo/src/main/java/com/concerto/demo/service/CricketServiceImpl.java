package com.concerto.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.concerto.demo.model.Player;
import com.concerto.demo.model.Team;
import com.concerto.demo.repo.PlayerRepository;
import com.concerto.demo.repo.TeamRepository;

@Service
public class CricketServiceImpl implements CricketService {
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private TeamRepository teamRepository;
    public List<String> getAllTeamPlayers(long teamId) {
        List<String> result = new ArrayList<String>();
        List<Player> players = playerRepository.findByTeamId(teamId);
        for (Player player : players) {
            result.add(player.getName());
        }
        return result;
    }
    public void addBarcelonaPlayer(String name, String position, int number) {
		/* Team barcelona = teamRepository.findByPlayers(new Long(1)); */
        Player newPlayer = new Player();
        newPlayer.setName(name);
        newPlayer.setPosition(position);
        newPlayer.setNum(number);
		/* newPlayer.setTeam(barcelona); */
        playerRepository.savePlayer(newPlayer);
    }
}
