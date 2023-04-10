package fitness.TrainingList;
import fitness.trainingInterface;
import java.io.Serializable;

public class Swimming implements trainingInterface, Serializable {
    private static final String name = "Swimming";
    private static final long Kcal = 3L;

    @Override
    public long getK() {
        return Kcal;
    }
    public String getName() {return name;}
}
