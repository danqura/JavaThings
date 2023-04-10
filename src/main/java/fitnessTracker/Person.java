package fitness;
import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable {
    private final String name;
    private final ArrayList<Training> trainingList;

    public Person(String name){
        this.name = name;
        this.trainingList = new ArrayList<>();
    }

    public void addTraining(Training training){this.trainingList.add(training);}

    public void seeTraining(){
        System.out.printf("%-20s%-10s%-10s%n", "Training name" , "Duration" , "kCal");
        for (Training training:this.trainingList) {
            System.out.printf("%-20s%-10s%-10s%n",training.getTraining().getName(),training.getTime(),training.getKcal());
        }
    }

    public ArrayList<Training> getTrainingList(){return this.trainingList;}

    public long getKcal(){
        long kcalSum = 0;

        for (Training training:this.trainingList) {
            kcalSum += training.getTraining().getK()*training.getDurationTraining();
        }
        return kcalSum;
    }

    public String getName() {return name;}
}
