package fitnessTracker.TrainingList;
import fitnessTracker.ITraining;
import java.io.Serializable;

public class Skiprope implements ITraining, Serializable {
    private static final String name = "Скакалка";
    private static final long calories = 4L;

    @Override
    public long getCalories() {
        return calories;
    }
    public String getName() {
        return name;
    }
}