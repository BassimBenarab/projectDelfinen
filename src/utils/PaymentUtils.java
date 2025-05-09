package utils;

import model.Member;
import model.MembershipType;

import java.time.LocalDate;
import java.time.Period;

import static model.MembershipType.JUNIOR;

public class PaymentUtils {
    public static MembershipType determineMembershipType(int age) {
        if (age < 18) {
            return JUNIOR;
        } else if (age <= 60) {
            return MembershipType.SENIOR;
        } else {
            return MembershipType.PENSIONER;
        }
    }

    public static double getMembershipFee(Member member) {
        int age = DateUtils.calculateAge(member.getBirthDate());
        MembershipType type = determineMembershipType(age);

        switch (type) {
            case JUNIOR:
                return 1000;
            case SENIOR:
                return 1600;
            case PENSIONER:
                return 1600 * 0.75;
            default:
                return 500;
        }

    }
}
