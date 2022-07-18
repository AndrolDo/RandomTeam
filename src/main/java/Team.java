import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Member> members = new ArrayList<>();
    private Double core = 0d;

    public Team() {
    }

    public void addMember(Member member){
        members.add(member);
        core += member.getCore();
    }


    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Double getCore() {
        return core;
    }

    public void setCore(Double core) {
        this.core = core;
    }

    @Override
    public String toString() {
        String text  = "\n\n";
        for (int i=0; i< members.size(); i++){
            text = text + String.format("\n%d. %s : %.1f",i+1, members.get(i).getName(),members.get(i).getCore());
        }
        text = text + "\nTotal core: " + core;
        return text;
    }

    public Double getAverage(){
        return core / members.size();
    }

}
