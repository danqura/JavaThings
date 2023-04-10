package fitnessTracker;

// ������-������
import com.google.common.base.Stopwatch;

import fitnessTracker.TrainingList.*;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {

    static String SAVEPATH = "src/main/java/fitnessTracker/save/";
    public static void main(String[] args) throws InterruptedException {

        Scanner in = new Scanner(System.in);
        int menuPointer = 0;
        Person person = null;
        Training training = null;
        Stopwatch timer;

        while (true) {
            while ((menuPointer != 1  && menuPointer != 2)) {
                System.out.println("1 - ������� ����� �������\n2 - ��������� �������\n0 - ����������� ������");
                menuPointer = in.nextInt();
                switch (menuPointer) {
                    case 1:
                        System.out.println("������� ��� �������:");
                        String name = in.next();
                        if (checkIfNameIsFree(name)) {
                            person = new Person(name);
                            System.out.println("������� " + person.getName() + " ��� ������");
                        } else {
                            System.out.println("��� ������� ��� ������.");
                            menuPointer = 0;
                        }
                        break;
                    case 2:
                        if (canLoad()) {
                            System.out.println("�������� �������:");
                            person = load();
                        } else {
                            System.out.println("������� �� �������.");
                            menuPointer = 0;
                        }
                        break;
                    case 0:
                        return;
                }
            }
            while (menuPointer != 9 && person != null) {
                System.out.println("1 - ������ ����������\n2 - ������ � �����������\n3 - ���������\n9 - ����� �� �������\n0 - ����������� ������");
                menuPointer = in.nextInt();
                switch (menuPointer) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("�������� ����������");
                        System.out.println("1 - ���\n2 - ��������\n3 - ����������\n4 - ��������\n5 - ���������");
                        menuPointer = 0;
                        while (menuPointer != 1 && menuPointer != 2 && menuPointer != 3 && menuPointer != 4 && menuPointer != 5)  { //����� ������������ enum
                            menuPointer = in.nextInt();
                            switch (menuPointer) {
                                case 1 -> training = new Training(new Running());
                                case 2 -> training = new Training(new Swimming());
                                case 3 -> training = new Training(new Situps());
                                case 4 -> training = new Training(new Skiprope());
                                case 5 -> training = new Training(new Pushups());
                                default -> System.out.println("�������� ���������� ����������.");
                            }
                        }
                        System.out.println("����������� ������ ����������");
                        in.next();
                        timer = Stopwatch.createStarted();
                        System.out.println("����������� ����� ����������");
                        in.next();
                        timer.stop();
                        training.endTraining(timer.elapsed(TimeUnit.SECONDS));
                        person.addTraining(training);
                        //savePerson(person); -- ��� ������� ����� ��������� ����� ����� ��������� ����������
                        break;
                    case 2:
                        person.seeTraining();
                        System.out.println("����� �������: " + person.getTotalCalories());
                        break;
                    case 3:
                        savePerson(person);
                        System.out.println("�������� ���������� �������");
                        break;
                    case 9:
                        person = null;
                        break;
                }
            }
        }
    }

    static Person load() {
        Person p = null;
        File folder = new File(SAVEPATH);
        Scanner in = new Scanner(System.in);
        int position = -1;
        for (int i = 0; i< Objects.requireNonNull(folder.listFiles()).length; i++) //��� ���?
         {
            System.out.println((i+1) + " - " + Objects.requireNonNull(folder.listFiles())[i].getName());
        }
        while (position<0||position>folder.listFiles().length) {
            System.out.println("�������� ����� ������������");
            position = in.nextInt();
        }
        if(position == 0){
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Objects.requireNonNull(folder.listFiles())[position-1].toString()))) {
            p = (Person) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("������� "+p.getName()+" ��������.");
        return p;
    }

    static void savePerson(Person person) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVEPATH+person.getName()))){
            oos.writeObject(person);
            System.out.println("������� "+person.getName()+" ��������.");
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    static boolean checkIfNameIsFree(String name){
        File folder = new File(SAVEPATH);
        for (int i = 0; i< Objects.requireNonNull(folder.listFiles()).length; i++){
            if(Objects.requireNonNull(folder.listFiles())[i].getName().equals(name)){
                return false;
            }
        }
        return true;
    }

    static boolean canLoad(){
        File folder = new File(SAVEPATH);
        return Objects.requireNonNull(folder.listFiles()).length > 0;
    }
}
