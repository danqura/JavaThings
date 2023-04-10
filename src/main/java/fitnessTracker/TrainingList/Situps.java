package fitness.TrainingList;
import fitness.trainingInterface;
import java.io.Serializable;

public class Situps implements trainingInterface, Serializable {
    private static final String name = "Situps";
    private static final long Kcal = 1L;

    @Override
    public long getK() {
        return Kcal;
    }
    public String getName() {return name;}
}