package com.hire10x.team.Service;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Models.TeamModel;

import java.util.List;

public interface TeamService {

    Team createTeam(TeamModel teamModel);

    TeamModel getTeam(String teamName);
}
