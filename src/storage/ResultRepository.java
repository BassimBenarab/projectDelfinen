package storage;

import data.Handler.Datahandler;
import model.CompetitionResult;
import model.Member;
import model.SwimDiscipline;
import model.TrainingResult;
import utils.InputHelper;
import java.time.LocalDate;
import java.util.List;

public class ResultRepository {
    private MemberRepository memberRepo;
    private List<TrainingResult> trainingResultList;
    private List<CompetitionResult> competitionResultList;
    private final Datahandler data = new Datahandler();
    private InputHelper input;

    public ResultRepository (MemberRepository memberRepo, List<TrainingResult> trainingResultList, List<CompetitionResult> competitionResultList, InputHelper input) {
        this.memberRepo = memberRepo;
        this.trainingResultList = trainingResultList;
        this.competitionResultList = competitionResultList;
        this.input = input;
    }

    public void addTrainingResult() {
        Member member = null;

        while (member == null) {
            String name = input.getString("Skriv dit navn: ");
            member = memberRepo.getMemberByName(name);
            if (member == null) {
                System.out.println("Medlem ikke fundet,prøv igen");
            }
        }

            double time = input.getDoubleInput("Skriv din tid i sekunder: ");
            SwimDiscipline discipline = member.getDiscipline();

            TrainingResult result = new TrainingResult(member, discipline, time,LocalDate.now());
            trainingResultList.add(result);
            System.out.println("Ny træningsresultat tilføjet");
    }
    public void addCompetitionResult() {
        Member member = null;

        while (member == null) {
            String name = input.getString("Skriv dit navn: ");
            member = memberRepo.getMemberByName(name);
            if (member == null) {
                System.out.println("Medlem ikke fundet, prøv igen");
            }
        }
        String location = input.getString("Skriv navnet på konkurrencen du deltog i: ");
        double time = input.getDoubleInput("Skriv din tid: ");
        int placement = input.getIntInput("Hvilken placering fik du i stævnet? ", 1, 100);

        SwimDiscipline discipline = member.getDiscipline();

        CompetitionResult result = new CompetitionResult(member, discipline, time, LocalDate.now(),location, placement );
        competitionResultList.add(result);
        System.out.println("Konkurrence information tilføjet");
    }

    public void printAllMembersTrainingTimes() {
        List<Member> members = memberRepo.getAllMembers();

        for (Member member : members) {
            System.out.println(member.getName() + "'s trænings resultater:");

            boolean foundResult = false;
            for (TrainingResult result : trainingResultList) {
                if (result.getMember().equals(member)) {
                    foundResult = true;
                    System.out.println("    Disciplin: " + result.getDiscipline() + ", tid: " + result.getTime() + " sekunder, dato:    " + result.getDate());
                }
            }

            if (!foundResult) {
                System.out.println("Ingen træningsresultat sat endnu");
            }
            System.out.println();
        }
    }
    public void printAllCompetitionResults() {
        List<Member> members = memberRepo.getAllMembers();

        for (Member member : members) {
            System.out.println("Konkurrence information for: " + member.getName());

            boolean foundCompetition = false;
            for ( CompetitionResult competition : competitionResultList) {
                if (competition.getMember().equals(member)) {
                    foundCompetition = true;
                    System.out.println("    Disciplin: " + competition.getDiscipline() + ". Tid: " + competition.getTime() + ". Konkurrence: " + competition.getCompetitionName() + ". Dato: " + competition.getDate() + ". Placering = " + competition.getPlacement() );
                }
            }
            if (!foundCompetition) {
                System.out.println("Ingen stævne information fundet");
            }
            System.out.println();
        }
    }
}