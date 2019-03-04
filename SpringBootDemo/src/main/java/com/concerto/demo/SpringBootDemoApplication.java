package com.concerto.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.concerto.demo.service.CricketService;

@SpringBootApplication
@ComponentScan("com.concerto")
public class SpringBootDemoApplication implements CommandLineRunner{
    @Autowired
    CricketService cricketService;
    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
    @Override
    public void run(String... arg0) throws Exception {
    	cricketService.addBarcelonaPlayer("Xavi Hernandez", "Midfielder", 6);
		/*
		 * List<String> players = cricketService.getAllTeamPlayers(1); for(String player
		 * : players) { System.out.println("Introducing Barca player => " + player); }
		 */
    }

}
