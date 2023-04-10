package fitnessTracker;

import fitnessTracker.TrainingList.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TrainingTest {

    @Test
    @DisplayName("Длительность отжиманий + заполнение")
    void pushupsTest(){

        Training pushTest = new Training(new Pushups());
        pushTest.endTraining(4L);
        assertEquals(pushTest.getCalories(), 4L * new Pushups().getCalories());
        assertNotEquals(pushTest.getTrainingDuration(), 3L);
    }

    @Test
    @DisplayName("Длительность бега + заполнение")
    void runningTest(){
        Training runTest = new Training(new Running());
        runTest.endTraining(2L);
        assertEquals(runTest.getCalories(), 4L);
    }

    @Test
    @DisplayName("Длительность приседаний + заполнение")
    void situpsTest(){
        Training sitTest = new Training(new Situps());
        sitTest.endTraining(4L);
        assertNotEquals(23, sitTest.getCalories());
    }

}