package fitnessTracker;
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
        System.out.printf("%-20s%-15s%-15s%n", "Вид тренировки" , "Длительность" , "Калорий сожжено");
        for (Training training:this.trainingList) {
            System.out.printf("%-20s%-15s%-15s%n",training.getTraining().getName(),training.getTrainingDuration(),training.getCalories());
        }
    }

    public ArrayList<Training> getTrainingList(){return this.trainingList;}

    public long getTotalCalories(){
        long calSum = 0;

        for (Training training:this.trainingList) {
            calSum += training.getTraining().getCalories()*training.getTrainingDuration();
        }
        return calSum;
    }

    public String getName() {return name;}
}
