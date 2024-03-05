package com.hire10x.team.Service;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Exceptions.TeamDuplicateException;
import com.hire10x.team.Mapper.TeamMapper;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public TeamServiceImp() {
    }

    public Team createTeam(TeamModel teamModel) {
        Team team = this.teamMapper.modelToEntity(teamModel);
        Optional<Team> existingTeam = this.teamRepo.findByName(team.getName());
        if (existingTeam.isPresent()) {
            logger.log(Level.WARNING, "Team name already in use: " + team.getName());
            throw new TeamDuplicateException("Team name already in use");
        } else {
            Team savedTeam = this.teamRepo.save(team);
            logger.log(Level.INFO, "Team created: " + savedTeam.getName());
            return savedTeam;
        }
    }

    @Override
    public TeamModel getTeam(String teamName) {
        Optional<Team> team = this.teamRepo.findByName(teamName);
        if (team.isPresent()) {
            TeamModel teamModel = teamMapper.entityToModel(team.get());
            return teamModel;
        } else {
            return null;
        }

    }
}