package storage;

import model.Member;
import model.MembershipType;
import model.SwimDiscipline;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberRepository {
    private List<Member> members = new ArrayList<>();
    private final String FILE_PATH = "src/data/members.txt";

    public MemberRepository() {
        this.members = loadMembers();
    }


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
                        System.out.println("Født i fremtiden ikke muligt endnu");
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
                    System.out.println("1. Freestyle " + "\n 2. Backstroke" + "\n 3. Breaststroke" + "\n 4. Butterfly");

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


            members.add(new Member(name, birthDate, isActive, swimDiscipline));
            System.out.println(members.get(members.size() - 1));
            System.out.println("--------------------------");

            System.out.println("Ville du oprette endnu et medlem? Ja/Nej");
            String input2 = input.nextLine().trim().toLowerCase();

            if (!input2.equals("ja")) {
                addMore = false;
            }
        }
        try {
            saveMembers(members);
        } catch (IOException e) {
            System.out.println("Fejl ved gemning af medlemmer: " + e.getMessage());
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


    //Metode til at gemme medlemmer til fil
    public void saveMembers(List<Member> members) throws IOException {
        try (PrintStream out = new PrintStream(new FileOutputStream(FILE_PATH, false))) {
            for (Member m : members) {
                out.println(m.getName() + ";" + m.getBirthDate() + ";" + m.getMembershipType() + ";" + m.isActiveMember() + ";" + m.getDiscipline());
            }
            System.out.println("Medlemmer er blevet gemt");

        } catch (IOException e) {
            System.out.println("file is empty" + e.getMessage());
        }
    }

    public List<Member> loadMembers() {
        List<Member> loadedMembers = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String name = parts[0].trim();
                    LocalDate birthDate = LocalDate.parse(parts[1].trim());
                    String membershipType = parts[2].trim();
                    boolean isActive = Boolean.parseBoolean(parts[3].trim());
                    String disciplineStr =parts[4].trim();

                    SwimDiscipline discipline = null;

                    if (!disciplineStr.equals("none")) {
                        discipline = SwimDiscipline.valueOf(disciplineStr);
                    }

                    loadedMembers.add(new Member(name, birthDate, isActive, discipline));
                }
            }
        } catch (IOException e) {
            System.out.println("file error" + e.getMessage());
        } catch (Exception e) {
            System.out.println("fejl ved indlænsing" + e.getMessage());
        }
        this.members = loadedMembers;
        return loadedMembers;
    }
}

/*

            // try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
        } while ()
    }


*/


            //  if (new java.io.File(FILE_PATH).length() == 0) {
    //out.println("Name,BirthDate,Membership,Active,Disciplines");
