
import model.MembershipType;
import org.junit.jupiter.api.Test;
import logic.MembershipCalculator;
import java.time.LocalDate;
import model.SwimDiscipline;
import model.Member;
import static org.junit.jupiter.api.Assertions.*;

public class MemberTest {
    @Test
    void testMemberisactive() {
        Member newmember = new Member("Filip Jacobsen", LocalDate.of(2004,10,1), true, SwimDiscipline.FREESTYLE);
        assertTrue(newmember.isActiveMember()); // her tester vi om medlemmet returnere aktivt
    }
    @Test
    void test_member_is_not_active() {
        Member newmember = new Member("Filip Jacobsen", LocalDate.of(2004,10,1), false, SwimDiscipline.FREESTYLE);
        assertFalse(newmember.isActiveMember()); // her tester vi at hvis member er passiv, skal den retunere false på at være aktiv ( booleans)
    }
    @Test
    void testMembershipTypeSenior() {
        Member newmember = new Member("Issa Baldeh", LocalDate.of(2000,10,5), true, SwimDiscipline.BREASTSTROKE);
        assertEquals(MembershipType.SENIOR, newmember.getMembershipType());
    }
    @Test
    void testMembershipTypeJunior() {
        Member newmember = new Member("Issa Baldeh", LocalDate.of(2010,10,5), true, SwimDiscipline.BREASTSTROKE);
        assertEquals(MembershipType.JUNIOR, newmember.getMembershipType());
    }
    @Test
    void testMembershipTypePensioner() {
        Member newmember = new Member("Issa Baldeh", LocalDate.of(1950,10,5), true, SwimDiscipline.BREASTSTROKE);
        assertEquals(MembershipType.PENSIONER, newmember.getMembershipType()); // testing that the correct membership type is given to a new member by using the age calculator method and logic provided in our project
    }
    @Test
    void testMembershipFeeJunior_Senior_Pensioner() {
        Member newmember = new Member("Rasmus", LocalDate.of(2010,5,7),true,SwimDiscipline.BACKSTROKE);
        MembershipCalculator calculator = new MembershipCalculator();
        double fee = calculator.calculateMembershipFee(newmember);
        assertEquals(1000,fee ); // testing for junior membership price

        newmember.setBirthDate(LocalDate.of(2000,5,7));
        double fee2 = calculator.calculateMembershipFee(newmember);
        assertEquals(1600, fee2); // testing for Senior membership price

        newmember.setBirthDate(LocalDate.of(1950,5,7));
        double fee3 = calculator.calculateMembershipFee(newmember);
        assertEquals(1200,fee3); // testing for Pensioner membership price

        newmember.setActiveMember(false);
        double fee4 = calculator.calculateMembershipFee(newmember);
        assertEquals(500, fee4); // testing for membership price if you are passive (regardless of junior, senior or pensiner)
    }
    @Test
    void testSwimDisciplineForMembers() {
        Member newmember = new Member("Kasper Nielsen",LocalDate.of(2010,3,18),true, SwimDiscipline.FREESTYLE);
        assertEquals(SwimDiscipline.FREESTYLE, newmember.getDiscipline());

        newmember.setDiscipline(SwimDiscipline.BACKSTROKE);
        assertEquals(SwimDiscipline.BACKSTROKE, newmember.getDiscipline());

        newmember.setDiscipline(SwimDiscipline.BREASTSTROKE);
        assertEquals(SwimDiscipline.BREASTSTROKE, newmember.getDiscipline());

        newmember.setDiscipline(SwimDiscipline.BUTTERFLY);
        assertEquals(SwimDiscipline.BUTTERFLY, newmember.getDiscipline());
    }
}
