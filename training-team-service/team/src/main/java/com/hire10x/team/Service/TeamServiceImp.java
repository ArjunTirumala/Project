package com.hire10x.team.Service;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Exceptions.TeamDuplicateException;
import com.hire10x.team.Mapper.TeamMapper;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelResponse;
import com.hire10x.team.Models.TeamUpdate;
import com.hire10x.team.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TeamServiceImp implements TeamService {

    private static final Logger logger = Logger.getLogger(TeamServiceImp.class.getName());

    @Autowired
    TeamRepository teamRepo;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    TeamModelResponse response;

    @Autowired
    TeamUpdate teamUpdate;

    public TeamModelResponse createTeam(TeamModel teamModel) {
        Team team = this.teamMapper.modelToEntity(teamModel);
        Optional<Team> existingTeam = this.teamRepo.findByName(team.getName());

        if (existingTeam.isPresent()) {
            logger.log(Level.WARNING, "Team name already in use: " + team.getName());
            throw new TeamDuplicateException("Team name already in use");
            } else {
                team.setCreatedAt(new Date());
                Team savedTeam = this.teamRepo.save(team);
                logger.log(Level.INFO, "Team created: " + savedTeam.getName());
                response.setTeamId(savedTeam.getTeamId() != null ? savedTeam.getTeamId().toString() : "");
                response.setMessage("Team created successfully");
                return response;
        }
    }

    @Override
    public ResponseEntity<?> getTeam(String teamName) {
        Optional<Team> teamOptional = teamRepo.findByName(teamName);

        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            TeamModel teamModel = teamMapper.entityToModel(team);
            return new ResponseEntity<>(teamModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No team found with team name: " + teamName, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> updateTeam(TeamModel teamModel, Long teamId) {
        Optional<Team> teamOptional = teamRepo.findById(teamId);

        if (teamOptional.isEmpty()) {

            logger.info("No Team found with ID: " + teamId);
            return new ResponseEntity<>("No team found with the specified ID", HttpStatus.NOT_FOUND);
        }

        Team existingTeam = teamOptional.get();

        if (teamModel.getName() != null) {
            existingTeam.setName(teamModel.getName());
        }
            if (teamModel.getDescription() != null) {
            existingTeam.setDescription(teamModel.getDescription());
        }
            if (teamModel.getUserIds() != null) {
            List<String> existingUserIds = existingTeam.getUserIds();
            List<String> newUserIds = teamModel.getUserIds();

            for (String userId : newUserIds) {
                if (!existingUserIds.contains(userId)) {
                    existingUserIds.add(userId);
                }
            }

            existingTeam.setUserIds(existingUserIds);
        }

        existingTeam.setModifiedAt(new Date());

        Team updatedTeam = teamRepo.save(existingTeam);

        logger.info("Team with ID " + teamId + " updated successfully");

        return new ResponseEntity<>(updatedTeam, HttpStatus.OK);
    }
}