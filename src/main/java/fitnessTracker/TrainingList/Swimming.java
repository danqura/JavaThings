package fitnessTracker.TrainingList;
import fitnessTracker.ITraining;
import java.io.Serializable;

public class Swimming implements ITraining, Serializable {
    private static final String name = "Плавание";
    private static final long calories = 5L;

    @Override
    public long getCalories() {
        return calories;
    }
    public String getName() {
        return name;
    }
}
