package com.example.IPLDataReaderUsingHSql.Repository;

import com.example.IPLDataReaderUsingHSql.Modules.IPLMatch;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface MatchRepository extends CrudRepository<IPLMatch, Integer> {

    @Query("SELECT m FROM IPLMatch m WHERE m.Year = :Year")
    List<IPLMatch> findByYear(@Param("Year") String year);

    @Query("SELECT m FROM IPLMatch m WHERE m.Year = :Year AND (m.Team1 = :Team1 OR m.Team2=:Team1)")
    List<IPLMatch> findByYearAndTeam(
            @Param("Year") String year,
            @Param("Team1") String team1);

    @Query("SELECT m FROM IPLMatch m WHERE m.Year = :Year AND ((m.Team1 = :Team1 AND m.Team2 = :Team2) OR (m.Team1 = :Team2 AND m.Team2 = :Team1))")
    List<IPLMatch> findByTeam1VsTeam2OfYear(
            @Param("Year") String year,
            @Param("Team1") String team1,
            @Param("Team2") String team2);

    @Query("SELECT m FROM IPLMatch m WHERE m.Team1 = :Team OR m.Team2 = :Team")
    List<IPLMatch> findTeamDetail(@Param("Team") String team);

    @Query("SELECT m FROM IPLMatch m WHERE (m.Team1 = :Team1 AND m.Team2 = :Team2) OR (m.Team1 = :Team2 AND m.Team2 = :Team1)")
    List<IPLMatch> findByTeam1VsTeam2(
            @Param("Team1") String team1,
            @Param("Team2") String team2);

}
