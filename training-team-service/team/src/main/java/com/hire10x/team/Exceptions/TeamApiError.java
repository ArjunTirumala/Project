package com.hire10x.team.Exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class TeamApiError {
    public Integer errorID;

    public HttpStatus errorCode;

    public String errorMessage;
}
