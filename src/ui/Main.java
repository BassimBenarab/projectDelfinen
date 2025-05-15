package ui;
import storage.MemberRepository;
import storage.PaymentRepository;

public class Main {
    public static void main(String[] args) {
    MemberRepository memberRepo = new MemberRepository();
    PaymentRepository payRepo = new PaymentRepository(memberRepo);

        System.out.println("velkommen til svømmeklub 'Delfin' ");
        MainMenu menu = new MainMenu();
        menu.runProgram();
    }
}