//package com.hire10x.team.TestService;
//
//import com.hire10x.team.Entity.Team;
//import com.hire10x.team.Exceptions.TeamDuplicateException;
//import com.hire10x.team.Mapper.TeamMapper;
//import com.hire10x.team.Models.TeamModel;
//import com.hire10x.team.Models.TeamModelResponse;
//import com.hire10x.team.Repository.TeamRepository;
//import com.hire10x.team.Service.TeamServiceImp;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//public class TeamServiceImpTest {
//
//    @Mock
//    private TeamRepository teamRepository;
//
//    @Mock
//    private TeamMapper teamMapper;
//
//    @InjectMocks
//    private TeamServiceImp teamService;
//
//    @Test
//    public void testCreateTeam_UniqueTeamName_Success() {
//        TeamModel teamModel = new TeamModel("New Team", "Description", new ArrayList<>());
//        Team team = new Team("New Team", "Description", new Date(), new ArrayList<>());
//
//        when(teamRepository.findByName("New Team")).thenReturn(Optional.empty());
//        when(teamMapper.modelToEntity(teamModel)).thenReturn(team);
//        when(teamRepository.save(team)).thenReturn(team);
//
//        TeamModelResponse response = teamService.createTeam(teamModel);
//
//        assertNotNull(response);
//        assertEquals("Team created successfully", response.getMessage());
//    }
//
//    @Test
//    public void testCreateTeam_DuplicateTeamName_ExceptionThrown() {
//        TeamModel teamModel = new TeamModel("Existing Team", "Description", new ArrayList<>());
//        Team existingTeam = new Team("Existing Team", "Description", new Date(), new ArrayList<>());
//
//        when(teamRepository.findByName("Existing Team")).thenReturn(Optional.of(existingTeam));
//
//        assertThrows(TeamDuplicateException.class, () -> teamService.createTeam(teamModel));
//    }
//
//    @Test
//    public void testGetTeam_ExistingTeam_Success() {
//        String teamName = "Existing Team";
//        Team existingTeam = new Team("Existing Team", "Description", new Date(), new ArrayList<>());
//
//        when(teamRepository.findByName("Existing Team")).thenReturn(Optional.of(existingTeam));
//
//        ResponseEntity<?> responseEntity = teamService.getTeam(teamName);
//
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    }
//
//    // Add more test cases for getTeam method, updateTeam method, etc.
//
//}