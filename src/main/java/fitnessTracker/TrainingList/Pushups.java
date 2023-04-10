package fitness.TrainingList;
import fitness.trainingInterface;
import java.io.Serializable;

public class Pushups implements Serializable, trainingInterface {
    private static final String name = "Pushups";
    private static final long Kcal = 1L;

    @Override
    public long getK() {
        return Kcal;
    }
    public String getName() {return name;}
}
