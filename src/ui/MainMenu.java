package ui;

import model.Member;
import storage.MemberRepository;
import utils.PaymentUtils;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);
    //private ArrayList<Member> members = new ArrayList<>();
    private MemberRepository memberRepo = new MemberRepository();
    private PaymentUtils payments = new PaymentUtils();

        public void runProgram() {
        boolean running = true;
        while (running) {
            System.out.println("\n--Hovedmenu--");
            System.out.println("1. Medlemmer");
            System.out.println("2. Betalinger");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                        case 1 -> {
                            boolean back = false;
                            while (!back) {
                                System.out.println("\n--Medlemmer --");
                                System.out.println("1. Tilføj medlem");
                                System.out.println("2. Se alle medlemmer");
                                System.out.println("3. Tilbage");


                                int subChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch (subChoice) {
                                    case 1 -> memberRepo.addMember();
                                    case 2 -> memberRepo.getAllMembers();
                                    case 3 -> back = true;
                                    default -> System.out.println("Ugyldigt valg");
                                }
                                
                            }
                        }
                        case 2 -> {
                            boolean back2 = false;
                            while (!back2) {
                                System.out.println("\n--Betalinger --");
                                System.out.println("1. Samlet kontingent");
                                System.out.println("2. Medlemmer i restance");
                                System.out.println("3. Tilbage");
                            }
                        }


                        default -> System.out.println("Ugyldigt valg");
                        }
                    }


                }
            }
        }
