package com.hire10x.team.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
@Data
@Component
public class TeamUpdate {
    private Long teamId;
    private String name;
    @JsonIgnore
    private List<String> userIds;
    private String description;
    private Date createdAt;
    private Date modifiedAt;

}
