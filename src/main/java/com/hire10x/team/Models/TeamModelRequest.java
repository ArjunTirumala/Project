package com.hire10x.team.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TeamModelRequest {

    private Long teamId;
    private String name;
    private List<String> userIds;
    private String description;
    private Date createdAt;
    private java.util.Date updatedAt;
}
