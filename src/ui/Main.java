package ui;

import storage.MemberRepository;
import storage.TeamManager;
import utils.InputHelper;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TeamManager manager = new TeamManager();
        MemberRepository memberRepo = new MemberRepository(manager, new InputHelper(new Scanner(System.in)));


        System.out.println("Velkommen til svømmeklub 'Delfin' ");
        MainMenu menu = new MainMenu();
        menu.runProgram();
    }
}