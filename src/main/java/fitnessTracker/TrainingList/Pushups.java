package fitnessTracker.TrainingList;
import fitnessTracker.ITraining;
import java.io.Serializable;

public class Pushups implements Serializable, ITraining {
    private static final String name = "Отжимания";
    private static final long calories = 1L;

    @Override
    public long getCalories() {
        return calories;
    }
    public String getName() {
        return name;
    }
}
