package com.hire10x.team.TestService;

import com.hire10x.team.Entity.Team;
import com.hire10x.team.Exceptions.TeamDuplicateException;
import com.hire10x.team.Mapper.TeamMapper;
import com.hire10x.team.Models.TeamModel;
import com.hire10x.team.Repository.TeamRepository;
import com.hire10x.team.Service.TeamServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TeamServiceImpTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamMapper teamMapper;

    @InjectMocks
    private TeamServiceImp teamService;

    @Test
    public void createTeam_WhenTeamDoesNotExist_ShouldReturnCreatedTeam() {
        // Arrange
        TeamModel teamModel = new TeamModel();
        teamModel.setName("New Team");

        Team team = new Team();
        team.setName("New Team");

        when(teamMapper.modelToEntity(teamModel)).thenReturn(team);
        when(teamRepository.findByName("New Team")).thenReturn(Optional.empty());
        when(teamRepository.save(team)).thenReturn(team);

        // Act
        Team createdTeam = teamService.createTeam(teamModel);

        // Assert
        assertNotNull(createdTeam);
        assertEquals("New Team", createdTeam.getName());
        verify(teamRepository, times(1)).findByName("New Team");
        verify(teamRepository, times(1)).save(team);
    }

    @Test
    public void createTeam_WhenTeamExists_ShouldThrowTeamDuplicateException() {
        // Arrange
        TeamModel teamModel = new TeamModel();
        teamModel.setName("Existing Team");

        Team existingTeam = new Team();
        existingTeam.setName("Existing Team");

        when(teamMapper.modelToEntity(teamModel)).thenReturn(existingTeam);
        when(teamRepository.findByName("Existing Team")).thenReturn(Optional.of(existingTeam));

        // Act & Assert
        assertThrows(TeamDuplicateException.class, () -> teamService.createTeam(teamModel));
        verify(teamRepository, times(1)).findByName("Existing Team");
        verify(teamRepository, never()).save(existingTeam);
    }
}
