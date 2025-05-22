package storage;

import model.Member;
import model.MembershipType;
import model.SwimDiscipline;
import java.util.ArrayList;
import java.util.List;

public class TeamManager {
    private static List<Team> allTeams;

    public TeamManager() {
        allTeams = new ArrayList<>();

        allTeams.add(new Team("Per Madsen", SwimDiscipline.BACKSTROKE, List.of(MembershipType.PENSIONER)));
        allTeams.add(new Team("Laura Bøgeskov", SwimDiscipline.FREESTYLE, List.of( MembershipType.PENSIONER)));
        allTeams.add(new Team("Henrik Eriksen", SwimDiscipline.BREASTSTROKE, List.of( MembershipType.PENSIONER)));
        allTeams.add(new Team("Marie Lauritsen", SwimDiscipline.BUTTERFLY, List.of( MembershipType.PENSIONER)));
        allTeams.add(new Team("Pia Lundgren", SwimDiscipline.BACKSTROKE, List.of( MembershipType.JUNIOR, MembershipType.SENIOR)));
        allTeams.add(new Team("julia Bayers", SwimDiscipline.FREESTYLE, List.of(MembershipType.JUNIOR, MembershipType.SENIOR)));
        allTeams.add(new Team("Palle Kvist", SwimDiscipline.BREASTSTROKE, List.of(MembershipType.JUNIOR,MembershipType.SENIOR)));
        allTeams.add(new Team("Filip Jacobsen", SwimDiscipline.BUTTERFLY, List.of(MembershipType.JUNIOR, MembershipType.SENIOR)));
    }

    public void addMembertoTeam(Member member){
        for (Team team : allTeams) {
            if (team.fitsMember(member)){
                team.addMember(member);
                return;
            }
        }
    }
    public List<Team> getAllTeams() {
        return allTeams;
    }
    public void printAllTeams() {
        System.out.println("=========== Hold oversigt ===========");
        for (Team team : allTeams) {
            System.out.println("----------------");

            System.out.println("T   ræner:    " + team.getTrainer());

            System.out.println("Disciplin:    " + team.getDiscipline());

            System.out.print("Medlemstyper:    ");
            List<MembershipType> types = team.getMembershipTypes();
            if(types.isEmpty()) {
                System.out.println("Ingen");
            } else {
                String memberTypes = String.join(", ", types.stream().map(MembershipType::name).toArray(String[]::new));
                System.out.println(memberTypes);
            }

                System.out.println();

            List<Member> teamMembers = team.getMembers();
            if (teamMembers.isEmpty()) {
                System.out.println("Ingen medlemmer på dette hold");
            } else {
                System.out.println("Medlemmer:");
                for (Member member : teamMembers) {
                    System.out.println("   - " + member.getName());
                }
            }
        }
        System.out.println("-------------------------");
    }
}
