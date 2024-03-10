 package com.hire10x.team.Controller;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelResponse;
import com.hire10x.team.Models.TeamUpdate;
import com.hire10x.team.Service.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class TeamController {
    @Autowired
    private TeamService teamService;

    private static final Logger logger = Logger.getLogger(TeamController.class.getName());

    @PostMapping({"/teams"})
    public ResponseEntity createTeam(@Valid @RequestBody TeamModel teamModel) {
        TeamModelResponse teamModelResponse = this.teamService.createTeam(teamModel);
        logger.info("New team created with ID: " + teamModelResponse.getTeamId());
        return new ResponseEntity<>(teamModelResponse, HttpStatus.CREATED);
    }
    @GetMapping({"/search"})
    public ResponseEntity getTeam(@Valid @RequestParam String teamName){
        TeamModel teamModel = teamService.getTeam(teamName);
        return new ResponseEntity<>(teamModel, HttpStatus.OK);
    }
    @PutMapping({"/update/{teamId}"})
    public ResponseEntity updateTeam(@RequestBody TeamModel teamModel, @PathVariable Long teamId){
        logger.info("Updating team: " + teamId);
        TeamUpdate teamUpdate =  teamService.updateTeam(teamModel,teamId);
        return new ResponseEntity<>(teamUpdate, HttpStatus.OK);
    }
}