package com.hire10x.team.Repository;

import com.hire10x.team.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface TeamRepository extends JpaRepository <Team, Long> {
    Optional<Team> findByName(String name);

    @Query(value = "select * from teams where name like '%' || :teamName || '%'", nativeQuery = true)
    List<Team> searchByName(@Param("teamName") String teamName);
}
