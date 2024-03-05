package com.hire10x.team.Controller;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelResponse;
import com.hire10x.team.Service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
public class TeamController {
    @Autowired
    private TeamService teamService;

    @Autowired
    TeamModelResponse response;

    private static final Logger logger = Logger.getLogger(TeamController.class.getName());

    @PostMapping({"/teams"})
    public ResponseEntity createTeam(@Valid @RequestBody TeamModel teamModel) {
        Team team = this.teamService.createTeam(teamModel);
        response.setTeamId(team.getTeamId() != null ? team.getTeamId().toString() : "");
        response.setMessage("Team created successfully");

        logger.info("New team created with ID: " + response.getTeamId());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping({"/search"})
    public ResponseEntity getTeam(@Valid @RequestParam String teamName){
        TeamModel teamModel = teamService.getTeam(teamName);
        return new ResponseEntity<>(teamModel, HttpStatus.OK);

    }



}