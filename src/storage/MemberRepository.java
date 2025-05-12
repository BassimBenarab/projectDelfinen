package storage;

import model.Member;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MemberRepository {
    List<Member> members = new ArrayList<>();

    public void addMember() {
        Scanner input = new Scanner(System.in);

        boolean addMore = true;
        while (addMore) {
            System.out.println("--------------------------");
            System.out.println("Navn: ");
            String name = input.nextLine();

            System.out.println("Fødselsår: ");
            int year = input.nextInt();
            System.out.println("Måned: ");
            int month = input.nextInt();
            System.out.println("Dato: ");
            int day = input.nextInt();
            input.nextLine();

            LocalDate birthDate = LocalDate.of(year, month, day);

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

    public void getAllMembers() {
        System.out.println("--------------------------");
        for (Member m : members) {
            System.out.println(m);
        }
    }
}
