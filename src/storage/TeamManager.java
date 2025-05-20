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

        allTeams.add(new Team("træner A", SwimDiscipline.BACKSTROKE, MembershipType.PENSIONER));
        allTeams.add(new Team("træner B", SwimDiscipline.FREESTYLE, MembershipType.PENSIONER));
        allTeams.add(new Team("træner C", SwimDiscipline.BREASTSTROKE, MembershipType.PENSIONER));
        allTeams.add(new Team("træner D", SwimDiscipline.BUTTERFLY, MembershipType.PENSIONER));
    }

    public void addMembertoTeam(Member member){
        for (Team team : allTeams) {
            if (team.fitsMember(member)){
                team.addMember(member);
                System.out.println(member.getName() + " tilføjet til hold med træner " + team.getTrainer());
                return;
            }
        }
    }
    public void printAllTeams() {
        for (Team team : allTeams) {
            System.out.println("----------------");
            System.out.println("træner: " + team.getTrainer());
            System.out.println("Disciplin: " + team.getDiscipline());
            System.out.println("medlemstype: " + team.getMembershipType());

            List<Member> teamMembers = team.getMembers();
            if (teamMembers.isEmpty()) {
                System.out.println("ingen tildelte medlemmer på dette hold");
            } else {
                System.out.println("medlemmer:");
                for (Member member : teamMembers) {
                    System.out.println(" - " + member.getName());
                }
            }
        }
        System.out.println("-------------------------");
    }
}
