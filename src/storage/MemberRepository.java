package storage;

import model.Member;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberRepository {
    ArrayList<Member> members = new ArrayList<>();


    public void addMember(String name, LocalDate birthDate, boolean isActive) {
        members.add(new Member(name,birthDate, isActive));
    }

    public ArrayList<Member> getAllMembers (){
        return members;



    }
}
