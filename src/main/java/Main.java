import org.apache.xmlbeans.impl.jam.mutable.MMember;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Member> listSource;
    private static  List<Integer> listIndex;

    private static List<Team> listTeamOut;

    public static void main(String[] args) {

        int loop = 999999;
        String file = "./test.xlsx";

        listTeamOut = new ArrayList<>();

        try {
            listSource = Excel.readExcel(file);
            listSource = listSource.stream().sorted((Member m1,Member m2) -> m2.getCore().compareTo(m1.getCore())).collect(Collectors.toList());
            listSource .forEach(System.out::println);
            System.err.println("Total member: " + listSource.size());

           for (int i=0; i<loop; i++){
               splitTeam();
           }

            listTeamOut.forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void splitTeam(){
        List<Team> listTeam = new ArrayList<>();
        listTeam.add(new Team());
        listTeam.add(new Team());
        listTeam.add(new Team());
        listTeam.add(new Team());

        List<Member> listTemp = new ArrayList<>();

        for (int i=0; i<=listSource.size(); i++){
            if ((i!=0 && i%4==0) ||  i==listSource.size()){
                listIndex = new ArrayList<>();
                for (Integer j=0; j<listTemp.size(); j++){
                    listIndex.add(j);
                }
                for (Team team:listTeam){
                    try {
                        Member m = listTemp.get(randomIndex());
                        team.addMember(m);
//                        System.err.println(m.toString());
                    }catch (Exception e){
//                        e.printStackTrace();
                    }
                }

               if (i<listSource.size()){
                   listTemp = new ArrayList<>();
                   listTemp.add(listSource.get(i));
               }

            }else{
                listTemp.add(listSource.get(i));
            }
        }

        if ((getDeviant(listTeam)<getDeviant(listTeamOut)) || (getDeviant(listTeam)==getDeviant(listTeamOut))&&getDeviantAverage(listTeam)<getDeviantAverage(listTeamOut)){

                System.err.println("== Chênh lệch tổng điểm giữa các đội: " + getDeviant(listTeam) + " ==Chênh lệch điểm trung bình giữa các đội: " + getDeviantAverage(listTeam));
                listTeamOut = listTeam;

        }

    }

    public static  int randomIndex(){
        Random rand = new Random();
        int index = rand.nextInt(listIndex.size());
        int value = listIndex.get(index);
        listIndex.remove(index);
        return value;
    }

    public static Double getDeviantAverage(List<Team> list){
        if (list.size()==0)
            return 100d;
        double min = list.stream().max((t1, t2) -> t2.getAverage().compareTo(t1.getAverage())).get().getAverage();
        double max = list.stream().max((t1, t2) -> t1.getAverage().compareTo(t2.getAverage())).get().getAverage();

        return max-min;
    }
    public static Double getDeviant(List<Team> list){
        if (list.size()==0)
            return 100d;
        double min = list.stream().max((t1, t2) -> t2.getCore().compareTo(t1.getCore())).get().getCore();
        double max = list.stream().max((t1, t2) -> t1.getCore().compareTo(t2.getCore())).get().getCore();

        return max-min;
    }



}
