package fitnessTracker.TrainingList;
import fitnessTracker.trainingInterface;
import java.io.Serializable;

public class Running implements trainingInterface, Serializable {
    private static final String name = "Running";
    private static final long Kcal = 2L;

    @Override
    public long getK() {
        return Kcal;
    }
    public String getName() {return name;}
}
