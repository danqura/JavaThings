package fitnessTracker;

import java.io.Serializable;

public class Training implements Serializable {
    private final ITraining training;
    private long durationTraining;
    public Training(ITraining training) {this.training = training;}
    public void endTraining(long durationTraining){this.durationTraining = durationTraining;}
    public long getTrainingDuration() {return durationTraining;}
    public long getCalories(){return training.getCalories()*durationTraining;}
    public ITraining getTraining() {return training;}
}
