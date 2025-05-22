package storage;

import data.Handler.Datahandler;
import logic.MembershipCalculator;
import model.Member;
import model.Payment;
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
            System.out.println("Samlet betaling: " + total + "kr.");
        }
    }
    public void printNonPayers() {
        List<Member> members = memberRepository.getAllMembers();
        List<Payment> payments = data.loadPayments();
        List<Member> nonPayers = new ArrayList<>();

        for (Member member : members) {
            double requiredAmount = calculator.calculateMembershipFee(member);
            double paidAmount = 0.0;

            for (Payment payment : payments) {
                if (payment.getMemberName().equals(member.getName())) {
                    paidAmount += payment.getAmount();
                }
            }
            if (paidAmount < requiredAmount) {
                nonPayers.add(member);
            }
        }
        if (nonPayers.isEmpty()) {
            System.out.println("Alle medlemmer har betalt");
        } else {
            System.out.println("Medlemmer som ikke har betalt:");
            for (Member nonPayer : nonPayers) {
                double requiredAmount = calculator.calculateMembershipFee(nonPayer);
                double paidAmount = 0.0;

                for (Payment payment : payments) {
                    if (payment.getMemberName().equals(nonPayer.getName())) {
                        paidAmount += payment.getAmount();
                    }
                }
                double amountOwed = requiredAmount -paidAmount;
                System.out.println(nonPayer.getName() + " skylder " + amountOwed + "kr.");
            }
        }
    }
}
