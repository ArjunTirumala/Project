package com.hire10x.team.TestService;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Exceptions.TeamDuplicateException;
import com.hire10x.team.Mapper.TeamMapper;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelRequest;
import com.hire10x.team.Models.TeamModelResponse;
import com.hire10x.team.Repository.TeamRepository;
import com.hire10x.team.Service.TeamServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    @InjectMocks
    private TeamServiceImpl teamService;

    @Mock
    private TeamModelResponse teamModelResponse;

    @Test
    void createTeam_ValidRequest_TeamCreatedSuccessfully() {

        TeamModelRequest request = new TeamModelRequest();
        request.setName("TestTeam");
        Team teamEntity = new Team();
        teamEntity.setName("TestTeam");
        when(teamMapper.requestModelToEntity(request)).thenReturn(teamEntity);
        when(teamRepository.findByName("TestTeam")).thenReturn(Optional.empty());
        when(teamRepository.save(any(Team.class))).thenReturn(teamEntity);
        TeamModelResponse response = teamService.createTeam(request);
        assertNotNull(response);
    }

    @Test
    void createTeam_NullName_ThrowsNullPointerException() {
        TeamModelRequest request = new TeamModelRequest();
        assertThrows(NullPointerException.class, () -> teamService.createTeam(request));
    }

    @Test
    void createTeam_DuplicateName_ThrowsTeamDuplicateException() {
        TeamModelRequest request = new TeamModelRequest();
        request.setName("ExistingTeam");
        Team teamEntity = new Team();
        teamEntity.setName("ExistingTeam");
        when(teamMapper.requestModelToEntity(request)).thenReturn(teamEntity);
        when(teamRepository.findByName("ExistingTeam")).thenReturn(Optional.of(teamEntity));
        assertThrows(TeamDuplicateException.class, () -> teamService.createTeam(request));
    }

    @Test
    void getTeam_shouldReturnTeamModel() {
        String teamName = "ExampleTeam";
        Team team = new Team();
        when(teamRepository.findByName(teamName)).thenReturn(Optional.of(team));
        when(teamMapper.entityToModel(team)).thenReturn(new TeamModel());
        TeamModel result = teamService.getTeam(teamName);
        verify(teamRepository, times(1)).findByName(teamName);
        verify(teamMapper, times(1)).entityToModel(team);
        assertNotNull(result);
    }

    @Test
    void getTeam_NonExistentTeamName_ThrowsEntityNotFoundException() {
        String teamName = "NonExistentTeam";
        when(teamRepository.findByName(teamName)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> teamService.getTeam(teamName));
    }

    @Test
    void searchTeam_shouldReturnTeamModelList() {

        String teamName = "ExampleTeam";
        List<Team> teamList = Collections.singletonList(new Team());
        when(teamRepository.searchByName(teamName)).thenReturn(teamList);
        when(teamMapper.entityToModel(any())).thenReturn(new TeamModel());
        List<TeamModel> result = teamService.searchTeam(teamName);
        verify(teamRepository, times(1)).searchByName(teamName);
        verify(teamMapper, times(1)).entityToModel(any());
        assertNotNull(result);
    }

    @Test
    void searchTeam_NoMatchingTeams_ThrowsEntityNotFoundException() {
        String teamName = "NonExistentTeam";
        when(teamRepository.searchByName(teamName)).thenReturn(Collections.emptyList());
        assertThrows(EntityNotFoundException.class, () -> teamService.searchTeam(teamName));
    }

    @Test
    void updateTeam_shouldReturnTeamUpdate() {
        Long teamId = 1L;
        TeamModelRequest teamModelRequest = new TeamModelRequest();
        Team team = new Team();
        TeamModel teamModel = new TeamModel();
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
        when(teamRepository.save(team)).thenReturn(team);
        when(teamMapper.entityToModel(team)).thenReturn(teamModel);
        TeamModel result = teamService.updateTeam(teamModelRequest, teamId);
        verify(teamRepository, times(1)).findById(teamId);
        verify(teamRepository, times(1)).save(team);
        assertNotNull(result);
    }

    @Test
    void updateTeam_InvalidTeamId_ThrowsEntityNotFoundException() {
        Long teamId = 999L;
        TeamModelRequest teamModelRequest = new TeamModelRequest();
        when(teamRepository.findById(teamId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> teamService.updateTeam(teamModelRequest, teamId));
    }
}
