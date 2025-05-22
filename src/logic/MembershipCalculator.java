package logic;

import model.Member;
import utils.DateUtils;

public class MembershipCalculator {
    public double calculateMembershipFee(Member member) {

        int age = DateUtils.calculateAge(member.getBirthDate());
        if(!member.isActiveMember()) {
            return 500.00;
        } else if (age <=17 ) {
            return 1000.00;
        } else if (age <=60) {
            return 1600.00;
        } else {
            return 1600.00 * 0.75;
        }
    }
}

