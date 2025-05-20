package data.Handler;

public class Datahandler {
    private List<Member> members = new ArrayList<>();
    private final String FILE_PATH = "src/data/members.txt";

    public Datahandler() {
        this.members = loadMembers();
    }

    //Metode til at gemme medlemmer til fil
    public void saveMembers(List<Member> members) throws IOException {
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Member m : members) {
                out.println(m.getName() + " - " + m.getBirthDate() + " - " + m.getMembershipType() + " - " + m.isActiveMember() + " - " + m.getDiscipline());
                System.out.println("Medlemmer er blevet gemt");
            }

        } catch (IOException e) {
            System.out.println("file is empty" + e.getMessage());
        }
    }

    public List<Member> loadMembers() {
        List<Member> members = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        try {
            List<String> lines = Files.readAllLines(path);

            for (String line : lines) {
                String[] parts = line.split(" - ");
                if (parts.length >= 5) {
                    String name = parts[0];
                    LocalDate birthDate = LocalDate.parse(parts[1]);
                    MembershipType type = MembershipType.valueOf(parts[2]);
                    boolean isActive = Boolean.parseBoolean(parts[3]);
                    SwimDiscipline discipline = null;
                    if (!parts[4].equalsIgnoreCase("null")) {
                        discipline = SwimDiscipline.valueOf(parts[4]);
                    }

                    members.add(new Member(name, birthDate, isActive, discipline));


                }
            }
        } catch (IOException e) {
            System.out.println("file error" + e.getMessage());
        }
        return members;
    }
}


/*
public class DataHandler {
    private List<Member> members;
    private static final String fileName = "src/data/members.csv";

    public static void SaveTofilefilipVersion(List<Member> members, String fileName) throws IOException {
        try(PrintWriter out2 = new PrintWriter(new FileWriter(fileName, true))) {

        for(int i = 0; i < members.size(); i++) {
            Member m = members.get(i);
            String memberString =    m.getName() + ";" +
                                     m.getBirthDate() + ";" +
                                     m.getMembershipType() + ";" +
                                     m.isActiveMember() + ";" +
                                     m.getDiscipline();
            out2.println(memberString);
        }

    } catch (IOException e) {
        System.out.println("error writing to file" + e.getMessage());
    }
        }


}
*/