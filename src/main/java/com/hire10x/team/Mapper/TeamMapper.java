package com.hire10x.team.Mapper;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelRequest;
import com.hire10x.team.Service.TeamServiceImpl;
import org.mapstruct.Mapper;

//@Mapper(componentModel = "Spring", uses = TeamServiceImpl.class)
public interface TeamMapper {
    Team requestModelToEntity(TeamModelRequest teamModelRequest);

    TeamModel entityToModel(Team team);

}
