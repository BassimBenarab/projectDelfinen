package storage;

import model.Member;
import model.MembershipType;
import model.SwimDiscipline;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String trainer;
    private SwimDiscipline discipline;
    private List<MembershipType> membershipTypes;
    private List<Member> members;

    public Team(String trainer, SwimDiscipline discipline, List<MembershipType> membershipTypes) {
        this.trainer = trainer;
        this.discipline = discipline;
        this.membershipTypes = membershipTypes != null ? membershipTypes : new ArrayList<>();
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

    public List<MembershipType> getMembershipTypes() {
        return membershipTypes;
    }

    public void setMembershipType(List<MembershipType> membershipType) {
        this.membershipTypes = membershipTypes;
    }

    public List<Member> getMembers() {
        return members;
    }

    public boolean fitsMember(Member member) {
        return member.getDiscipline() == this.discipline && getMembershipTypes().contains(member.getMembershipType()) && member.isActiveMember();
    }
}
