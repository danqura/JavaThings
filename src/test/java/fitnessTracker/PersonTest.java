package fitnessTracker;


import fitnessTracker.TrainingList.Running;
import fitnessTracker.TrainingList.Swimming;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    @DisplayName("����������")
    void savingTest(){
        Person dummy = new Person("�����");
        assertEquals("�����", dummy.getName());
    }
    @Test
    @DisplayName("����������� ���")
        void namingTest()
    {
        Person dummy = new Person("�����");
        assertEquals("�����", dummy.getName());
        assertNotEquals("����", dummy.getName());
    }

    @Test
    @DisplayName("������������ ���������� �������")
    void listTest(){

        Person dummy = new Person("����");

        Training training_1_1 = new Training(new Running());
        training_1_1.endTraining(3L);
        dummy.addTraining(training_1_1);
        Training training_1_2 = new Training(new Running());
        training_1_2.endTraining(2L);
        dummy.addTraining(training_1_2);
        Training training_1_3 = new Training(new Swimming());
        training_1_3.endTraining(6L);
        dummy.addTraining(training_1_3);


        ArrayList<Training> testList = new ArrayList<>();

        testList.add(training_1_1);
        testList.add(training_1_2);
        testList.add(training_1_3);
        assertEquals(dummy.getTrainingList(), testList);



        dummy.addTraining(training_1_3);
        assertNotEquals(dummy.getTrainingList(), testList);


    }
    @Test
    @DisplayName("������������ �������� �������")
    void caloriesTest()
    {
        Person dummy = new Person("������");
        Training training_1_3 = new Training(new Swimming());
        training_1_3.endTraining(6L);
        dummy.addTraining(training_1_3);
        dummy.addTraining(training_1_3);

        assertEquals(dummy.getTotalCalories(), (6L * new Swimming().getCalories() + 6L * new Swimming().getCalories()));
        assertNotEquals(dummy.getTotalCalories(), (6L * new Swimming().getCalories() + 2L * new Swimming().getCalories()));
    }

}
