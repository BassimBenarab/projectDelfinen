package data.Handler;

import model.Member;
import model.MembershipType;
import model.Payment;
import model.SwimDiscipline;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class Datahandler {
    private List <Member> members;
    private List <Payment> payments;

    public Datahandler(){
        members = loadMembers();
        payments = loadPayments();
    }
    public <T> void saveListToFile(List<T> list, String filePath, Function<T, String> toLine) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            for (T item : list) {
                out.println(toLine.apply(item));
            }
        } catch (IOException e) {
            System.out.println("Fejl ved skrivning til fil: " + filePath + ";" + e.getMessage());


        }
    }

    public <T> List <T> loadListFromFile(String filePath, Function<String, T> fromLine) {
        List<T> list = new ArrayList<>();
        Path path = Paths.get(filePath);
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                list.add(fromLine.apply(line));
            }
        } catch (IOException e) {
            System.out.println("Fejl ved læsning til fil: " + filePath + ";" + e.getMessage());
        }
        return list;
    }

    public List<Member> loadMembers(){
        return loadListFromFile("src/data/members.txt", line -> {
            String[] parts = line.split(";");
            String name = parts [0].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");  // or your actual format
            LocalDate birthDate = LocalDate.parse(parts[1].trim(), formatter);
            MembershipType type = MembershipType.valueOf(parts[2].trim());
            boolean isActive = Boolean.parseBoolean(parts[3].trim());
            SwimDiscipline discipline = parts[4].trim().equalsIgnoreCase("null") ? null : SwimDiscipline.valueOf(parts[4]);
            return new Member(name,birthDate,isActive,discipline);
        });
    }

    public void saveMembers(List<Member> members) {
        saveListToFile(members, "src/data/members.txt", m ->
                m.getName() + ";" + m.getBirthDate() + ";" + m.getMembershipType() + ";" + m.isActiveMember() + ";" + m.getDiscipline()
        );
    }


    // ---------- Payments ------------
    public List<Payment> loadPayments() {
        return loadListFromFile("src/data/payments.txt", line -> {
            String[] parts = line.split(";");

            if (parts.length < 3) {
                System.out.println("Ugyldig betalingslinje: " + line);
                return null;
            }
            try {
            String memberName = parts[0];
            double amount = Double.parseDouble(parts[1]);
            LocalDate date = LocalDate.parse(parts[2]);
            return new Payment(memberName, amount, date);
        } catch (Exception e){
            System.out.println("Fejl ved parsing af betalingslinje" + line);
            return null;
        }
        }).stream().filter(p -> p != null).toList();
    }

    public void savePayments(List<Payment> payments) {
        saveListToFile(payments, "src/data/payments.txt", p ->
                p.getMemberName() + ";" + p.getAmount() + ";" + p.getDate()
        );
    }
}