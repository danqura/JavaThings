package fitnessTracker.TrainingList;
import fitnessTracker.ITraining;
import java.io.Serializable;

public class Situps implements ITraining, Serializable {
    private static final String name = "Приседания";
    private static final long calories = 3L;

    @Override
    public long getCalories() {
        return calories;
    }
    public String getName() {
        return name;
    }
}