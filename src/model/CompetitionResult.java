package model;

import java.util.Date;

public class CompetitionResult extends TrainingResult{
    private String competitionName ;
    private int placement;

    //Constructor:
    public CompetitionResult(double time, Date date, SwimDiscipline discipline, String competitionName, int placement){
        super(time,date,discipline);
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
