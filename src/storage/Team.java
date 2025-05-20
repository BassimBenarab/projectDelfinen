package storage;

import model.Member;
import model.MembershipType;
import model.SwimDiscipline;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String trainer;
    private SwimDiscipline discipline;
    private MembershipType membershipType;
    private List<Member> members;

    public Team(String trainer, SwimDiscipline discipline, MembershipType membershipType) {
        this.trainer = trainer;
        this.discipline = discipline;
        this.membershipType = membershipType;
        this.members = new ArrayList<>();
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public SwimDiscipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(SwimDiscipline discipline) {
        this.discipline = discipline;
    }

    public MembershipType getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    public List<Member> getMembers() {
        return members;
    }

    public boolean fitsMember(Member member) {
        return member.getDiscipline() == this.discipline && member.getMembershipType() == this.membershipType && member.isActiveMember();
    }
}
