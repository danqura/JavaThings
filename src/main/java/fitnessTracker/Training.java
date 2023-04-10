package fitness;

import java.io.Serializable;

public class Training implements Serializable {

    private final trainingInterface training;
    private long durationTraining;

    public Training(trainingInterface training) {this.training = training;}

    public void endTrainig(long durationTraining){this.durationTraining = durationTraining;}

    public long getDurationTraining() {return durationTraining;}

    public long getKcal(){return training.getK()*durationTraining;}

    public long getTime(){return durationTraining;}

    public trainingInterface getTraining() {return training;}
}
