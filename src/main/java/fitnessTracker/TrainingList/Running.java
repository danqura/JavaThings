package fitnessTracker.TrainingList;
import fitnessTracker.ITraining;
import java.io.Serializable;

public class Running implements ITraining, Serializable {
    private static final String name = "Бег";
    private static final long calories = 2L;

    @Override
    public long getCalories() {
        return calories;
    }
    public String getName() {
        return name;
    }
}
