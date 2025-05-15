package ui;

import data.Handler.Datahandler;
import storage.MemberRepository;
import storage.PaymentRepository;

public class Main {
    public static void main(String[] args) {
    MemberRepository memberRepo = new MemberRepository();
        Datahandler datahandler = new Datahandler();
    PaymentRepository payRepo = new PaymentRepository(memberRepo);

       // memberRepo.loadMembers();
        System.out.println("velkommen til svømmeklub 'Delfin' ");
        MainMenu menu = new MainMenu();
        menu.runProgram();
    }
}