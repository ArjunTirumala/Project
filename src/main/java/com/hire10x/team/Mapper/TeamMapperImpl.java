package com.hire10x.team.Mapper;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelRequest;
import org.springframework.stereotype.Component;

@Component
public class TeamMapperImpl implements TeamMapper {
    @Override
    public Team requestModelToEntity(TeamModelRequest teamModelRequest) {
        Team team  = new Team();
        team.setTeamId(teamModelRequest.getTeamId());
        team.setName(teamModelRequest.getName());
        team.setUserIds(teamModelRequest.getUserIds());
        team.setDescription(teamModelRequest.getDescription());
        team.setCreatedAt(teamModelRequest.getCreatedAt());
        team.setModifiedAt(teamModelRequest.getUpdatedAt());
        return team;
    }

    @Override
    public TeamModel entityToModel(Team team) {
        TeamModel teamModel = new TeamModel();
        teamModel.setTeamId(team.getTeamId());
        teamModel.setName(team.getName());
        teamModel.setDescription(team.getDescription());
        teamModel.setCreatedAt(team.getCreatedAt());
        teamModel.setUpdatedAt(team.getModifiedAt());
        return teamModel;
    }
}
