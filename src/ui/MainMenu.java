package ui;

import model.Member;
import storage.MemberRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Member> members = new ArrayList<>();

    public void addMember (String name, LocalDate birthDate,boolean isActive) {
        members.add(new Member(name, birthDate, isActive));
    }
    public ArrayList<Member> getAllMembers () {
        System.out.println(members);
        return members;
    }

        public void runProgram() {
        boolean running = true;
        while (running) {
            System.out.println("velkommen til klub 'Delfin' ");
            System.out.println("1. opret nyt medlem");
            System.out.println("2. se alle medlemmer");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {
                       case 1 -> addMember("", LocalDate.of(2004,10,21), true);
                       case 2 -> getAllMembers();
                    }
                }
            }
        }
    //public void addMember (String name, LocalDate birthDate,boolean isActive){
            //members.add(new Member(name, birthDate, isActive));
    }
