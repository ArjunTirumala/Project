package com.hire10x.team.Service;

import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelResponse;
import org.springframework.http.ResponseEntity;


public interface TeamService {

    TeamModelResponse createTeam(TeamModel teamModel);

    ResponseEntity<?> getTeam(String teamName);

    ResponseEntity<?> updateTeam(TeamModel teamModel, Long teamId);
}
