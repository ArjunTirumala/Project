package com.hire10x.team.Models;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TeamModelResponse {

    private String teamId;
    private String message;

}
