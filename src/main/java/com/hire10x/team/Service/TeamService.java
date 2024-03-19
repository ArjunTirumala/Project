package com.hire10x.team.Service;


import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelRequest;
import com.hire10x.team.Models.TeamModelResponse;

import java.util.List;


public interface TeamService {

    TeamModelResponse createTeam(TeamModelRequest teamModelRequest);

    TeamModel getTeam(String teamName);

    List<TeamModel> searchTeam(String teamName);

    TeamModel updateTeam(TeamModelRequest teamModelRequest, Long teamId);
}
