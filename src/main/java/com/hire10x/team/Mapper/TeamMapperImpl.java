package com.hire10x.team.Mapper;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Models.TeamModel;
import org.springframework.stereotype.Component;

@Component
public class TeamMapperImpl implements TeamMapper {
    @Override
    public Team modelToEntity(TeamModel teamModel) {
        Team team  = new Team();
        team.setTeamId(teamModel.getTeamId());
        team.setName(teamModel.getName());
        team.setDescription(teamModel.getDescription());
        team.setUserIds(teamModel.getUserIds());
        team.setCreatedAt(teamModel.getCreatedAt());
        team.setModifiedAt(teamModel.getUpdatedAt());
        return team;
    }

    @Override
    public TeamModel entityToModel(Team team) {
        TeamModel teamModel = new TeamModel();
        teamModel.setTeamId(team.getTeamId());
        teamModel.setName(team.getName());
        teamModel.setDescription(team.getDescription());
        teamModel.setUserIds(team.getUserIds());
        teamModel.setCreatedAt(team.getCreatedAt());
        teamModel.setUpdatedAt(team.getModifiedAt());
        return teamModel;
    }
}
