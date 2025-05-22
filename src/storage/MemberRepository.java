package storage;

import data.Handler.Datahandler;
import model.Member;
import model.SwimDiscipline;
import utils.InputHelper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MemberRepository {
    private List<Member> members = new ArrayList<>();
    private final Datahandler dataHandler = new Datahandler();
    private TeamManager teamManager;
    private PaymentRepository payment;
    private InputHelper input;

    public MemberRepository(TeamManager teamManager, InputHelper input) {
        this.teamManager = teamManager;
        this.payment = new PaymentRepository(this);
        this.members = dataHandler.loadMembers();
        this.input = input;
        for (Member m : members) {
            teamManager.addMembertoTeam(m);
        }
    }

    public void addMember() {

        boolean addMore = true;
        while (addMore) {

            System.out.println("--------------------------");
            String name = input.getString("Navn: ");

            LocalDate birthDate = null;
            while (birthDate == null) {
                try {
                    System.out.println("Fødselsår: ");
                    int year = input.getIntInput("Vælg år", 1000, 2030);
                    System.out.println("Måned: ");
                    int month = input.getIntInput("Vælg måned", 1,12);
                    System.out.println("Dato: ");
                    int day = input.getIntInput("Vælg dag", 1,31);

                    birthDate = LocalDate.of(year, month, day);

                    if (birthDate.isAfter(LocalDate.now())) {
                        System.out.println("Født i fremtiden er ikke muligt ");
                        birthDate = null;
                    }
                } catch (Exception e) {
                    System.out.println("Ugyldig dato, prøv venligst igen");
                }
            }

            System.out.println("Er medlem aktiv - Ja/Nej?: ");
            boolean isActive;
            SwimDiscipline swimDiscipline = null;
            while (true) {
                String input2 = input.getString("").trim().toLowerCase();

                if (input2.equals("ja")) {
                    isActive = true;

                    System.out.println("Vælg en svømmedisciplin");
                    System.out.println("\n 1. Freestyle " + "\n 2. Backstroke" + "\n 3. Breaststroke" + "\n 4. Butterfly");

                    while (swimDiscipline == null && isActive) {
                        try {
                            int disciplineChoice = input.getIntInput("Vælg svømmedisciplin",1,4);

                            switch (disciplineChoice) {
                                case 1:
                                    swimDiscipline = SwimDiscipline.FREESTYLE;
                                    break;
                                case 2:
                                    swimDiscipline = SwimDiscipline.BACKSTROKE;
                                    break;
                                case 3:
                                    swimDiscipline = SwimDiscipline.BREASTSTROKE;
                                    break;
                                case 4:
                                    swimDiscipline = SwimDiscipline.BUTTERFLY;
                                    break;
                                default:
                                    System.out.println("Ugyldigt valg");
                            }
                        } catch (Exception e) {
                            System.out.println("Ugyldigt input, vælg igen");
                        }
                    }
                    break;
                } else if (input2.equals("nej")) {
                    isActive = false;
                    break;
                } else {
                    System.out.println("Ugyldigt svar");
                }
            }
            System.out.println("---------------------------");
            System.out.println("Medlem oprettet");



            Member newMember = new Member(name, birthDate, isActive, swimDiscipline);
            members.add(newMember);
            dataHandler.saveMembers(members);
            teamManager.addMembertoTeam(newMember);
            System.out.println(newMember.getName() + " tilføjet til hold med træner " + findTrainerForMember(newMember));
            payment.generateAndSavePayments();
            System.out.println(members.get(members.size() - 1));
            System.out.println("--------------------------");
            System.out.println("Ville du oprette endnu et medlem? Ja/Nej");
            String input2 = input.getString("").trim().toLowerCase();

            if (!input2.equals("ja")) {
                addMore = false;
            }
        }
    }
    public Member getMemberByName(String name) {
        for (Member member : members) {
            if (member.getName().equalsIgnoreCase(name)) {
                return member;
            }
        }
        return null;
    }

    public List<Member> getAllMembers() {
        return members;
    }

    public void printAllMembers() {
        System.out.println("--------------------------");
        for (Member m : members) {
            System.out.println(m);
        }
    }
    public void printInactiveMembers() {
        System.out.println("---- Inaktive medlemmer -----");
        boolean found = false;
        for (Member m : members) {
            if (!m.isActiveMember()) {
                System.out.println(m);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Ingen aktive medlemmer fundet");
        }
        System.out.println("------------------------");
    }
    private String findTrainerForMember(Member member) {
        for (Team team : teamManager.getAllTeams()) {
            if (team.fitsMember(member)) {
                return team.getTrainer();
            }
        }
        return "Ukendt træner";
    }
}

