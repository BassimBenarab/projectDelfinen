package utils;

import model.Member;
import model.MembershipType;

import static model.MembershipType.JUNIOR;

public class PaymentUtils {
    private Member member;
    public static MembershipType determineMembershipType(int age) {
        if (age < 18) {
            return JUNIOR;
        } else if (age <= 60) {
            return MembershipType.SENIOR;
        } else {
            return MembershipType.PENSIONER;
        }
    }
}
