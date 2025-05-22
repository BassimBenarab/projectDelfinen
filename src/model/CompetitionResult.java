package model;

import java.time.LocalDate;
import java.util.Date;

public class CompetitionResult extends TrainingResult{
    private String competitionName ;
    private int placement;

    //Constructor:
    public CompetitionResult(Member member, SwimDiscipline discipline, double time, LocalDate date, String competitionName, int placement){
        super(member, discipline,time,date);
        this.competitionName = competitionName;
        this.placement = placement;
    }

    public String getCompetitionName(){
        return this.competitionName;
    }
    public void setCompetitionName(String competitionName){
        this.competitionName = competitionName;
    }

    public int getPlacement(){
        return this.placement;
    }

    public void setPlacement(int placement){
        this.placement = placement;
    }



}
