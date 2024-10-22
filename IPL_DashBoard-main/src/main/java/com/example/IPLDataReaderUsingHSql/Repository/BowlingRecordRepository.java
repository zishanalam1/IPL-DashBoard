package com.example.IPLDataReaderUsingHSql.Repository;

import com.example.IPLDataReaderUsingHSql.Modules.IPLBOWLINGDATA;
import com.example.IPLDataReaderUsingHSql.Modules.IPLBatsmanRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BowlingRecordRepository extends CrudRepository<IPLBOWLINGDATA,Integer> {

    @Query("SELECT m FROM IPLBOWLINGDATA m WHERE m.Match_id = :matchid")
    List<IPLBOWLINGDATA> getRecordByMatchId(@Param("matchid") String Matchid);
    @Query("SELECT m FROM IPLBOWLINGDATA m WHERE m.BowlerName = :BowlerName")
    List<IPLBOWLINGDATA> getBowlingDetailByName(@Param("BowlerName") String Name);
}
