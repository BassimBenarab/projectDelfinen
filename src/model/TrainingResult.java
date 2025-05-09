package model;
import java.util.Date;


public class TrainingResult {
    //Attributter:
    private double time;
    private Date date;
    private SwimDiscipline discipline;

    //Constructor:
    public TrainingResult(double time, Date date, SwimDiscipline discipline){
        this.time = time;
        this.date = date;
        this.discipline = discipline;
    }


    //Getter og setter:


    public double getTime() {
        return this.time;
    }

    public void setTime (double time) {
        this.time = time;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate (Date date) {
        this.date = date;
    }

    public SwimDiscipline getDiscipline() {
        return this.discipline;
    }

    public void setDiscipline (SwimDiscipline discipline) {
        this.discipline = discipline;
    }


}
