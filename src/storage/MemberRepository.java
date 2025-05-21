package storage;

import data.Handler.Datahandler;
import model.Member;
import model.MembershipType;
import model.SwimDiscipline;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MemberRepository {
    private List<Member> members = new ArrayList<>();
    private final Datahandler dataHandler = new Datahandler();
    private TeamManager teamManager;
    private PaymentRepository payment;

    public MemberRepository(TeamManager teamManager) {
        this.teamManager = teamManager;
        this.payment = new PaymentRepository(this);
        this.members = dataHandler.loadMembers();
        for (Member m : members) {
            teamManager.addMembertoTeam(m);
        }
    }


  /*  public MemberRepository(){
        this.members = loadMembers();

   */
    public void addMember() {
        Scanner input = new Scanner(System.in);

        boolean addMore = true;
        while (addMore) {

            System.out.println("--------------------------");
            System.out.println("Navn: ");
            String name = input.nextLine();

            LocalDate birthDate = null;
            while (birthDate == null) {
                try {
                    System.out.println("Fødselsår: ");
                    int year = input.nextInt();
                    System.out.println("Måned: ");
                    int month = input.nextInt();
                    System.out.println("Dato: ");
                    int day = input.nextInt();
                    input.nextLine();

                    birthDate = LocalDate.of(year, month, day);

                    if (birthDate.isAfter(LocalDate.now())) {
                        System.out.println("Født i fremtiden er ikke muligt ");
                        birthDate = null;
                    }
                } catch (Exception e) {
                    System.out.println("Ugyldig dato, prøv venligst igen");
                    input.nextLine();
                }
            }

            System.out.println("Er medlem aktiv - Ja/Nej?: ");
            boolean isActive;
            SwimDiscipline swimDiscipline = null;
            while (true) {
                String input2 = input.nextLine().trim().toLowerCase();

                if (input2.equals("ja")) {
                    isActive = true;

                    System.out.println("Vælg en svømmedisciplin");
                    System.out.println("\n 1. Freestyle " + "\n 2. Backstroke" + "\n 3. Breaststroke" + "\n 4. Butterfly");

                    while (swimDiscipline == null && isActive) {
                        try {
                            int disciplineChoice = input.nextInt();
                            input.nextLine();

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
                            input.nextLine();
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
            String input2 = input.nextLine().trim().toLowerCase();

            if (!input2.equals("ja")) {
                addMore = false;
            }
        }
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
        System.out.println("---- inaktive medlemmer -----");
        boolean found = false;
        for (Member m : members) {
            if (!m.isActiveMember()) {
                System.out.println(m);
                found = true;
            }
        }
        if (!found) {
            System.out.println("ingen aktive medlemmer fundet");
        }
        System.out.println("------------------------");
    }
    private String findTrainerForMember(Member member) {
        for (Team team : teamManager.getAllTeams()) {
            if (team.fitsMember(member)) {
                return team.getTrainer();
            }
        }
        return "ukendt træner";
    }
}

