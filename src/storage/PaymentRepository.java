package storage;

import logic.MembershipCalculator;
import model.Member;
import utils.PaymentUtils;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private MemberRepository memberRepository;
    MembershipCalculator calculator = new MembershipCalculator();

    public PaymentRepository( MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void calculatePayments() {
        List<Member> members = memberRepository.getAllMembers();
        double balance = 0;
        for (Member m : members) {
            double Payment = calculator.calculateMembershipFee(m);
            balance += Payment;

            //System.out.println("total balance = " + balance);
        }
        System.out.println("Samlede kontingentindtægter = " + balance);
    }
}
