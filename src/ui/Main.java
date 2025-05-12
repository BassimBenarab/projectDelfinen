package ui;
import storage.MemberRepository;

public class Main {
    public static void main(String[] args) {
    MemberRepository memberRepo = new MemberRepository();

        System.out.println("velkommen til klub 'Delfin' ");
        MainMenu menu = new MainMenu();
        menu.runProgram();
    }

}