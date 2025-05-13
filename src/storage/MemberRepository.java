package storage;

import model.Member;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MemberRepository {
    private List<Member> members = new ArrayList<>();

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
                        System.out.println("født i fremtiden ikke muligt endnu");
                        birthDate = null;
                    }
                } catch (Exception e) {
                    System.out.println("ugyldig dato, prøv venligst igen");
                    input.nextLine();
                }
            }

            System.out.println("Er medlem aktiv - Ja/Nej?: ");
            boolean isActive;
            while (true) {
                String input2 = input.nextLine().trim().toLowerCase();

                if (input2.equals("ja")) {
                    isActive = true;
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


            members.add(new Member(name, birthDate, isActive));
            System.out.println(members.get(members.size() - 1));
            System.out.println("--------------------------");

            System.out.println("ville du oprette endnu et medlem? Ja/Nej");
            String input2 = input.nextLine().trim().toLowerCase();

            if(!input2.equals("ja")){
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
}
