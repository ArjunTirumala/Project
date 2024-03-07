package com.hire10x.team.Service;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Exceptions.TeamDuplicateException;
import com.hire10x.team.Mapper.TeamMapper;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelResponse;
import com.hire10x.team.Models.TeamUpdate;
import com.hire10x.team.Repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TeamServiceImpl implements TeamService {

    private static final Logger logger = Logger.getLogger(TeamServiceImpl.class.getName());

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
            Team savedTeam = null;
            try {
                savedTeam = this.teamRepo.save(team);
            } catch (Exception e) {
                throw new DataAccessResourceFailureException("An error occurred while saving the team details");
            }

                logger.log(Level.INFO, "Team created: " + savedTeam.getName());
                response.setTeamId(savedTeam.getTeamId() != null ? savedTeam.getTeamId().toString() : "");
                response.setMessage("Team created successfully");
                return response;
        }
    }

    @Override
    public TeamModel getTeam(String teamName) {
        Optional<Team> teamOptional = teamRepo.findByName(teamName);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            TeamModel teamModel = teamMapper.entityToModel(team);
            return teamModel;
        } else {
            throw new EntityNotFoundException("No team details present for given teamName");
        }
    }

    @Override
    public Team updateTeam(TeamModel teamModel, Long teamId) {
        Optional<Team> teamOptional = teamRepo.findById(teamId);
        if (teamOptional.isEmpty()) {
            logger.info("No Team found with ID: " + teamId);
            throw new EntityNotFoundException("No team details present for given teamId");
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
        Team updatedTeam = null;
        try {
            updatedTeam = teamRepo.save(existingTeam);
        } catch (Exception e) {
            throw new DataAccessResourceFailureException("An error occurred while updating the team details");
        }
        logger.info("Team with ID " + teamId + " updated successfully");
        return updatedTeam;
    }
}