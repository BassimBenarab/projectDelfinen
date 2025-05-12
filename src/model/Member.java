package model;

import utils.DateUtils;
import utils.PaymentUtils;

import java.time.LocalDate;


public class Member {
    //Attributter:
    private String name;
    private LocalDate birthDate;
    private boolean activeMember;
    private MembershipType membershipType;

    // Constructor:
    public Member(String name, LocalDate birthDate, boolean activeMember) {
        this.name = name;
        this.birthDate = birthDate;
        this.activeMember = activeMember;
        int age = DateUtils.calculateAge(birthDate);
        this.membershipType = PaymentUtils.determineMembershipType(age);
    }

    //Getter og setter:
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isActiveMember() {
        return this.activeMember;
    }

    public void setActiveMember(boolean activeMember) {
        this.activeMember = activeMember;
    }

    public MembershipType getMembershipType() {
        return this.membershipType;
    }

    public void setMembershipType(MembershipType membershipType) {
        this.membershipType = membershipType;
    }

    //To string metoden gør udskrivningen pænere:
    public String toString() {
        return "Name: " + name + " Birthday " + birthDate + " Membership Type: " + membershipType + " Active membership = " + activeMember;

    }


    }

