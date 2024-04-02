package com.hire10x.team.Service;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Exceptions.TeamDuplicateException;
import com.hire10x.team.Mapper.TeamMapper;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelRequest;
import com.hire10x.team.Models.TeamModelResponse;
import com.hire10x.team.Repository.TeamRepository;
import com.hire10x.team.utils.CommonUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public TeamModelResponse createTeam(TeamModelRequest teamModelRequest) {
        CommonUtil.validateNotNull(teamModelRequest.getName(), "Team name cannot be null");
        Team team = this.teamMapper.requestModelToEntity(teamModelRequest);
        Optional<Team> existingTeam = this.teamRepo.findByName(team.getName());
        CommonUtil.validateNonEmptyObject(existingTeam, "Team name already in use");
        team.setCreatedAt(new Date());
        Team savedTeam = null;
        try {
            savedTeam = this.teamRepo.save(team);
        } catch (Exception e) {
            throw new DataAccessResourceFailureException("An error occurred while saving the team details");
        }
        CommonUtil.logInfo(Level.INFO, "Team created: " + savedTeam.getName());
        response.setTeamId(savedTeam.getTeamId() != null ? String.valueOf(savedTeam.getTeamId()) : null);
        response.setMessage("Team created successfully");
        return response;
    }

    @Override
    public TeamModel getTeam(String teamName) {
        Optional<Team> teamOptional = teamRepo.findByName(teamName);
        CommonUtil.validateEmptyObjects(teamOptional, "No team details present for given teamName");
        Team team = teamOptional.get();
        TeamModel teamModel = teamMapper.entityToModel(team);
        return teamModel;

    }

    @Override
    public List<TeamModel> searchTeam(String teamName) {
        List<Team> teamList = teamRepo.searchByName(teamName);
        CommonUtil.validateEmptyList(teamList, "No team details present for given teamName");
        List<TeamModel> teamModelList = new ArrayList<>();
        for (Team team : teamList) {
            TeamModel teamModel = teamMapper.entityToModel(team);
            teamModelList.add(teamModel);
        }
        return teamModelList;

    }

    @Override
    public TeamModel updateTeam(TeamModelRequest teamModelRequest, Long teamId) {
        Optional<Team> teamOptional = teamRepo.findById(teamId);
        CommonUtil.validateEmptyObjects(teamOptional, "No team details present for given teamId: " + teamId);
        Team existingTeam = teamOptional.get();
        if (teamModelRequest.getName() != null) {
            existingTeam.setName(teamModelRequest.getName());
        }
            if (teamModelRequest.getDescription() != null) {
            existingTeam.setDescription(teamModelRequest.getDescription());
        }
            if (teamModelRequest.getUserIds() != null) {
            List<String> existingUserIds = existingTeam.getUserIds();
            List<String> newUserIds = teamModelRequest.getUserIds();
            CommonUtil.checkAndConvertNullToEmptyList(existingUserIds);
            for (String userId : newUserIds) {
                if (!existingUserIds.contains(userId)) {
                    existingUserIds.add(userId);
                }
            }

            existingTeam.setUserIds(existingUserIds);
        }

        existingTeam.setModifiedAt(new Date());
        Team team = null;
        try {
            team = teamRepo.save(existingTeam);
        } catch (Exception e) {
            throw new DataAccessResourceFailureException("An error occurred while updating the team details");
        }
        TeamModel teamModel = teamMapper.entityToModel(team);
        CommonUtil.logInfo(Level.INFO, "Team with ID " + teamId + " updated successfully");
        return teamModel;
    }
}