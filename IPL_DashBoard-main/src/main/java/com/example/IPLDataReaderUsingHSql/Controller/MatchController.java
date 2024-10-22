package com.example.IPLDataReaderUsingHSql.Controller;

import com.example.IPLDataReaderUsingHSql.Modules.IPLMatch;
import com.example.IPLDataReaderUsingHSql.Modules.TeamComparision;
import com.example.IPLDataReaderUsingHSql.Modules.TeamDetail;
import com.example.IPLDataReaderUsingHSql.Repository.BatsmanRecordRepository;
import com.example.IPLDataReaderUsingHSql.Repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/all2")
@CrossOrigin
public class MatchController {
    @Autowired
    public MatchRepository matchRepository;
    @Autowired
    public BatsmanRecordRepository batsmanRecordRepository;


    @GetMapping("/Match/{id}")
    public Optional<IPLMatch> getMatchById(@PathVariable("id") int id)
    {
        return matchRepository.findById(id);
    }


    @GetMapping("/MatchesOf/{year}")
    public List<IPLMatch> getWinnersForYear(@PathVariable("year") String year) {
        //MatchRepository matchRepository1 = null;

        return matchRepository.findByYear(year);
    }
    @GetMapping("/Matches/{year}/{team}")
    public List<IPLMatch>getMatchOfATeamYearWise(@PathVariable("year") String year,@PathVariable("team") String team)
    {
        return  matchRepository.findByYearAndTeam(year,team);
    }
    @GetMapping("Match/{year}/{team1}/{team2}")
    public List<IPLMatch>getMatchOfTeam(@PathVariable("team1") String team1,@PathVariable("team2") String team2,@PathVariable("year") String year)
    {
        return matchRepository.findByTeam1VsTeam2OfYear(year,team1,team2);
    }


    @GetMapping("Data/{team}")
    public TeamDetail Teamdata(@PathVariable("team") String team) {
        String NoResult="NA";
        TeamDetail teamDetail = new TeamDetail();
        List<IPLMatch> l = matchRepository.findTeamDetail(team);
        int cntMatchWinn = 0;
        int cntNoResult=0;
        int cntTossWin=0;
        List<IPLMatch>listOfLast4IPLMatch =new ArrayList<>();

        for (int i = 0; i < l.size(); i++) {
            IPLMatch m = l.get(i);
            //System.out.println(m.getResult().toString());
            if (m.getResult().equals(team)) {
                cntMatchWinn++;
            }
            if(m.getResult().equals(NoResult))
            {
                cntNoResult++;
            }
            if (m.getTossWinner().equals(team)) {
                cntTossWin++;
            }

        }
        int i=0;
        while (listOfLast4IPLMatch.size()!=4)
        {   IPLMatch m = l.get(i);
            if(m.getTeam1().equals(team)|| m.getTeam2().equals(team))
                listOfLast4IPLMatch.add(m);
            i++;
        }
        teamDetail.setIPLMatchList(listOfLast4IPLMatch);
        teamDetail.setTossWon(cntTossWin);
        teamDetail.setNoResult(cntNoResult);
        teamDetail.setMatchPlayed(l.size());
        teamDetail.setTeamName(team);

        int totalMatch = l.size();
        int totalLoss = (totalMatch - cntMatchWinn)-cntNoResult;
        teamDetail.setTotalLoss(totalLoss);
        teamDetail.setTotalWins(cntMatchWinn);

        return teamDetail;
    }


    @GetMapping("/last4MatchesOf/{year}")
    public List<IPLMatch>LastFourMatchesOfSeason(@PathVariable("year") String year){
        List<IPLMatch> iplMatches=new ArrayList<>();
        List<IPLMatch>l=matchRepository.findByYear(year);
        for(int i=0;i<l.size();i++)
        {
            IPLMatch m=new IPLMatch();
            m=l.get(i);
            if(m.getMatchNumber().length()>2)
            {
                iplMatches.add(m);
            }
        }
        return iplMatches;

    }



    @GetMapping("/Compare/{team1}/{team2}")
    public TeamComparision compare2Teams(@PathVariable("team1") String team1,@PathVariable("team2") String team2)
    {
        List<IPLMatch> l=matchRepository.findByTeam1VsTeam2(team1,team2);


        int MatchPlayed=l.size();
        int MatchWonByTeam1=0;
        int MatchWonByTeam2=0;
        int TossWonByTeam1=0;
        int TossWonByTeam2=0;
        int NoResult=0;
        for(int i=0;i<l.size();i++)
        {
            IPLMatch m=l.get(i);
            if(m.getResult().equals(team1))
                MatchWonByTeam1++;
            else if (m.getResult().equals(team2)) {
                MatchWonByTeam2++;
            }
            else
                NoResult++;


            if(m.getTossWinner().equals(team1))
                TossWonByTeam1++;
            else if (m.getTossWinner().equals(team2)) {
                TossWonByTeam2++;
            }




        }
        TeamComparision TeamCmp=new TeamComparision(MatchPlayed,MatchWonByTeam1,MatchWonByTeam2,NoResult,TossWonByTeam1,TossWonByTeam2);
        return TeamCmp;
    }



}


