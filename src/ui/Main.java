package ui;
import model.Member;
import model.MembershipType;
import storage.MemberRepository;
import utils.DateUtils;
import utils.PaymentUtils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        /*Member member1 = new Member("Rasmus",LocalDate.of(2015, 5, 4), true);

        System.out.println(member1.getName());
        System.out.println(member1.getMembershipType());
        System.out.println(member1.isActiveMember());

        System.out.println(member1);
        double fee = PaymentUtils.getMembershipFee(member1);
        System.out.println("fee is = " + fee);

         */
    MemberRepository memberRepo = new MemberRepository();

    // tilføj medlem:
        memberRepo.addMember("Rasmus", LocalDate.of(1999,10,10), true);


        ArrayList<Member> members = memberRepo.getAllMembers();
        for (Member m : members){
            System.out.println(m);
        }

        MainMenu menu = new MainMenu();
        menu.runProgram();
    }

}