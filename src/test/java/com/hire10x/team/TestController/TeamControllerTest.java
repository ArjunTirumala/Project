//package com.hire10x.team.TestController;
//
//import com.hire10x.team.Controller.TeamController;
//import com.hire10x.team.Models.TeamModel;
//import com.hire10x.team.Models.TeamModelResponse;
//import com.hire10x.team.Service.TeamService;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//
//public class TeamControllerTest {
//
//    @Test
//    public void testTeamController() {
//        // Mock objects
//        TeamService teamService = mock(TeamService.class);
//        TeamController teamController = new TeamController();
//        teamController.setTeamService(teamService);
//
//        // Test case 1: Creating a new team
//        TeamModel teamModel = new TeamModel();
//        when(teamService.createTeam(any(TeamModel.class))).thenReturn(new TeamModelResponse());
//        ResponseEntity createResponse = teamController.createTeam(teamModel);
//        assertEquals(HttpStatus.CREATED, createResponse.getStatusCode());
//
//        // Test case 2: Getting a team by name
//        when(teamService.getTeam(anyString())).thenReturn(ResponseEntity.ok().build());
//        ResponseEntity getResponse = teamController.getTeam("TestTeam");
//        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
//
//        // Test case 3: Updating a team
//        when(teamService.updateTeam(any(TeamModel.class), anyLong())).thenReturn(ResponseEntity.ok().build());
//        ResponseEntity updateResponse = teamController.updateTeam(teamModel, 1L);
//        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
//    }
//}