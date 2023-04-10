package fitnessTracker.TrainingList;
import fitnessTracker.trainingInterface;
import java.io.Serializable;

public class SkippingRope implements trainingInterface, Serializable {
    private static final String name = "SkippingRope";
    private static final long Kcal = 1L;

    @Override
    public long getK() {
        return Kcal;
    }
    public String getName() {return name;}
}