package storage;

import data.Handler.Datahandler;
import logic.MembershipCalculator;
import model.Member;
import model.Payment;
import utils.PaymentUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private MemberRepository memberRepository;
    private final MembershipCalculator calculator = new MembershipCalculator();
    private final Datahandler data = new Datahandler();

    public PaymentRepository( MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void generateAndSavePayments (){
        List<Member> members = memberRepository.getAllMembers();
        List<Payment> payments = new ArrayList<>();

        for (Member m : members) {
            double amount = calculator.calculateMembershipFee(m);
            Payment payment = new Payment(m.getName(), amount, LocalDate.now());
            payments.add(payment);
        }

        data.savePayments(payments);
        System.out.println("Betalinger er gemt");
    }

    public void printAllPayments(){
        List<Payment> payments = data.loadPayments();
        double total = 0;
        if (payments.isEmpty()) {
            System.out.println("Ingen betalinger fundet");
        } else {
            System.out.println(" ------ Betalinger -------");
            for (Payment p : payments) {
                System.out.println(p.getMemberName() + " | " + p.getAmount() + " kr. " + p.getDate());
                total += p.getAmount();
            }
            System.out.println("------------------");
            System.out.println("samlet betaling: " + total + "kr.");
        }
    }
}
