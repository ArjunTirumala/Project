package com.hire10x.team.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Table(name = "teamsData")
@Data

public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "userIds")
    private List<String> userIds;

}
