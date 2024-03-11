package com.hire10x.team.Service;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelRequest;
import com.hire10x.team.Models.TeamModelResponse;
import com.hire10x.team.Models.TeamUpdate;
import org.springframework.http.ResponseEntity;


public interface TeamService {

    TeamModelResponse createTeam(TeamModelRequest teamModelRequest);

    TeamModel getTeam(String teamName);

    TeamUpdate updateTeam(TeamModelRequest teamModelRequest, Long teamId);
}
