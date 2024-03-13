package com.hire10x.team.TestService;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Mapper.TeamMapper;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Models.TeamModelRequest;
import com.hire10x.team.Models.TeamModelResponse;
import com.hire10x.team.Models.TeamUpdate;
import com.hire10x.team.Repository.TeamRepository;
import com.hire10x.team.Service.TeamServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    @InjectMocks
    private TeamServiceImpl teamService;

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
    void getTeam_shouldReturnTeamModel() {
        // Arrange
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
    void updateTeam_shouldReturnTeamUpdate() {
        Long teamId = 1L;
        TeamModelRequest teamModelRequest = new TeamModelRequest();
        Team team = new Team();
        when(teamRepository.findById(teamId)).thenReturn(Optional.of(team));
        when(teamRepository.save(team)).thenReturn(team);

        TeamUpdate result = teamService.updateTeam(teamModelRequest, teamId);

        verify(teamRepository, times(1)).findById(teamId);
        verify(teamRepository, times(1)).save(team);
        assertNotNull(result);
    }
}
