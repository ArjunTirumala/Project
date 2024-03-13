package com.hire10x.team.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "teams")
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

    @Temporal(TemporalType.DATE)
    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.DATE)
    @LastModifiedDate
    @Column (name = "modified_at")
    private Date modifiedAt;

}
