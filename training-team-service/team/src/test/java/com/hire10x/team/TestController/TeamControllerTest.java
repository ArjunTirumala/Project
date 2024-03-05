package com.hire10x.team.TestController;

import com.hire10x.team.Controller.TeamController;
import com.hire10x.team.Entity.Team;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelResponse;
import com.hire10x.team.Service.TeamService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @Mock
    private TeamModelResponse response; // Mock the TeamModelResponse

    @InjectMocks
    private TeamController teamController;

    @Test
    public void createTeam_WhenValidTeamModel_ShouldReturnCreatedResponse() {
        // Arrange
        TeamModel teamModel = new TeamModel();
        teamModel.setName("Test Team");

        Team createdTeam = new Team();
        createdTeam.setTeamId(123L);

        when(teamService.createTeam(any(TeamModel.class))).thenReturn(createdTeam);

        // Mock the behavior of the response object
        when(response.getTeamId()).thenReturn("123");
        when(response.getMessage()).thenReturn("Team created successfully");

        // Act
        ResponseEntity<TeamModelResponse> responseEntity = teamController.createTeam(teamModel);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        TeamModelResponse responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals("123", responseBody.getTeamId());
        assertEquals("Team created successfully", responseBody.getMessage());

        verify(teamService, times(1)).createTeam(any(TeamModel.class));
    }
}
