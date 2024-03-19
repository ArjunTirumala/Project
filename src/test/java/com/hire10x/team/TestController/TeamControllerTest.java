package com.hire10x.team.TestController;

import com.hire10x.team.Controller.TeamController;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelRequest;
import com.hire10x.team.Models.TeamModelResponse;
import com.hire10x.team.Service.TeamService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    @Test
    void createTeam_shouldReturnCreatedResponse() {
        TeamModelRequest teamModelRequest = new TeamModelRequest();
        TeamModelResponse teamModelResponse = new TeamModelResponse();
        when(teamService.createTeam(teamModelRequest)).thenReturn(teamModelResponse);
        ResponseEntity<TeamModelResponse> responseEntity = teamController.createTeam(teamModelRequest);
        verify(teamService, times(1)).createTeam(teamModelRequest);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void getTeam_shouldReturnTeamModel() {
        String teamName = "ExampleTeam";
        TeamModel teamModel = new TeamModel();
        when(teamService.getTeam(teamName)).thenReturn(teamModel);
        ResponseEntity<TeamModel> responseEntity = teamController.getTeam(teamName);
        verify(teamService, times(1)).getTeam(teamName);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void searchTeam_shouldReturnTeamModelList() {
        String teamName = "ExampleTeam";
        List<TeamModel> teamModelList = Collections.singletonList(new TeamModel());
        when(teamService.searchTeam(teamName)).thenReturn(teamModelList);
        ResponseEntity<List<TeamModel>> responseEntity = teamController.searchTeam(teamName);
        verify(teamService, times(1)).searchTeam(teamName);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void updateTeam_shouldReturnTeamUpdate() {
        Long teamId = 1L;
        TeamModelRequest teamModelRequest = new TeamModelRequest();
        TeamModel teamModel = new TeamModel();
        when(teamService.updateTeam(teamModelRequest, teamId)).thenReturn(teamModel);
        ResponseEntity<TeamModel> responseEntity = teamController.updateTeam(teamModelRequest, teamId);
        verify(teamService, times(1)).updateTeam(teamModelRequest, teamId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
