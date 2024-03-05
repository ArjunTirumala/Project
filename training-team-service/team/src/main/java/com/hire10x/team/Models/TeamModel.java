package com.hire10x.team.Models;

import lombok.Data;

import java.util.List;
@Data
public class TeamModel {

    private Long teamId;
    private String name;
    private List<String> userIds;
    private String description;

}
