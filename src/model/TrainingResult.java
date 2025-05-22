package model;
import java.time.LocalDate;


public class TrainingResult {
    //Attributter:
    private Member member;
    private double time;
    private LocalDate date;
    private SwimDiscipline discipline;

    //Constructor:
    public TrainingResult(Member member, SwimDiscipline discipline, double time, LocalDate date){
        this.time = time;
        this.date = date;
        this.discipline = discipline;
        this.member = member;
    }
    public Member getMember() {
        return member;
    }
    public void setMember (Member member) {
        this.member = member;
    }


    //Getter og setter:

    public double getTime() {
        return this.time;
    }

    public void setTime (double time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate (LocalDate date) {
        this.date = date;
    }
    public SwimDiscipline getDiscipline() {
        return this.discipline;
    }
    public void setDiscipline (SwimDiscipline discipline) {
        this.discipline = discipline;
    }
}
