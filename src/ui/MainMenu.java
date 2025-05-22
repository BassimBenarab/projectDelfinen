package ui;

import data.Handler.Datahandler;
import model.CompetitionResult;
import model.TrainingResult;
import storage.MemberRepository;
import storage.PaymentRepository;
import storage.ResultRepository;
import storage.TeamManager;
import utils.InputHelper;
import utils.PaymentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);
    private InputHelper input =new InputHelper(scanner);

    private TeamManager Manager = new TeamManager();
    private MemberRepository memberRepo = new MemberRepository(Manager, input);
    private PaymentUtils payments = new PaymentUtils();
    private PaymentRepository totalBalance = new PaymentRepository(memberRepo);
    private Datahandler data = new Datahandler();
    private List<TrainingResult> results = new ArrayList<>();
    private List<CompetitionResult> results2 = new ArrayList<>();
    private ResultRepository swimResult = new ResultRepository(memberRepo, results, results2, input);

        public void runProgram() {
        boolean running = true;
        while (running) {
            System.out.println("\n--Hovedmenu--");
            System.out.println("1. Medlemmer");
            System.out.println("2. Betalinger");
            System.out.println("3. Hold");
            System.out.println("4. Afslut");

            int choice = input.getIntInput("Vælg mellem:", 1, 4);

                switch (choice) {
                    case 1 -> {
                        boolean back = false;
                        while (!back) {
                            System.out.println("\n--Medlemmer --");
                            System.out.println("1. Tilføj medlem");
                            System.out.println("2. Se alle medlemmer");
                            System.out.println("3. Se inaktive medlemmer (passiv)");
                            System.out.println("4. Tilbage");


                            int subChoice = input.getIntInput("Vælg mellem:", 1, 4);

                            switch (subChoice) {
                                case 1 -> memberRepo.addMember();
                                case 2 -> memberRepo.printAllMembers();
                                case 3 -> memberRepo.printInactiveMembers();
                                case 4 -> back = true;
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

                            int paymentChoice = input.getIntInput("Vælg mellem:", 1,3);

                            switch (paymentChoice) {
                                case 1 -> totalBalance.printAllPayments();
                                case 2 -> totalBalance.printNonPayers();
                                case 3 -> back2 = true;
                                default -> System.out.println("Ugyldigt valg");

                            }
                        }
                    }
                    case 3 -> {
                        boolean back2 = false;
                        while (!back2) {
                            System.out.println("\n-- Hold");
                            System.out.println("1. Se alle hold");
                            System.out.println("2. Tilføj træningstid");
                            System.out.println("3. Se alles træningstider");
                            System.out.println("4. Opdater stævne information");
                            System.out.println("5. Se stævner");
                            System.out.println("6. Tilbage");

                            int teamChoice = input.getIntInput("Vælg mellem:", 1, 7);

                            switch (teamChoice) {
                                case 1 -> Manager.printAllTeams();
                                case 2 -> swimResult.addTrainingResult();
                                case 3 -> swimResult.printAllMembersTrainingTimes();
                                case 4 -> swimResult.addCompetitionResult();
                                case 5 -> swimResult.printAllCompetitionResults();
                                case 6 -> back2 = true;
                                default -> System.out.println("Ugyldigt valg");
                            }
                        }
                    }
                    case 4 -> {
                        System.out.println("Programmet afslutter. Vi ses...");
                        running = false;
                    }
                    default -> System.out.println("Ugyldigt valg");
                }
            }
        }
    }