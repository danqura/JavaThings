package fitnessTracker;

// Фитнес-трекер
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
                System.out.println("1 - Создать новый профиль\n2 - Загрузить профиль\n0 - Прекращение работы");
                menuPointer = in.nextInt();
                switch (menuPointer) {
                    case 1:
                        System.out.println("Введите имя профиля:");
                        String name = in.next();
                        if (checkIfNameIsFree(name)) {
                            person = new Person(name);
                            System.out.println("Профиль " + person.getName() + " был создан");
                        } else {
                            System.out.println("Имя профиля уже занято.");
                            menuPointer = 0;
                        }
                        break;
                    case 2:
                        if (canLoad()) {
                            System.out.println("Выберите профиль:");
                            person = load();
                        } else {
                            System.out.println("Профили не найдены.");
                            menuPointer = 0;
                        }
                        break;
                    case 0:
                        return;
                }
            }
            while (menuPointer != 9 && person != null) {
                System.out.println("1 - Начать тренировку\n2 - Данные о тренировках\n3 - Сохранить\n9 - Выйти из профиля\n0 - Прекращение работы");
                menuPointer = in.nextInt();
                switch (menuPointer) {
                    case 0:
                        return;
                    case 1:
                        System.out.println("Выберите тренировку");
                        System.out.println("1 - Бег\n2 - Плавание\n3 - Приседания\n4 - Скакалка\n5 - Отжимания");
                        menuPointer = 0;
                        while (menuPointer != 1 && menuPointer != 2 && menuPointer != 3 && menuPointer != 4 && menuPointer != 5)  { //можно использовать enum
                            menuPointer = in.nextInt();
                            switch (menuPointer) {
                                case 1 -> training = new Training(new Running());
                                case 2 -> training = new Training(new Swimming());
                                case 3 -> training = new Training(new Situps());
                                case 4 -> training = new Training(new Skiprope());
                                case 5 -> training = new Training(new Pushups());
                                default -> System.out.println("Выберите адекватную тренировку.");
                            }
                        }
                        System.out.println("Подтвердите начало тренировки");
                        in.next();
                        timer = Stopwatch.createStarted();
                        System.out.println("Подтвердите конец тренировки");
                        in.next();
                        timer.stop();
                        training.endTraining(timer.elapsed(TimeUnit.SECONDS));
                        person.addTraining(training);
                        //savePerson(person); -- При желании можно сохранять прямо после окончания тренировки
                        break;
                    case 2:
                        person.seeTraining();
                        System.out.println("Итого калорий: " + person.getTotalCalories());
                        break;
                    case 3:
                        savePerson(person);
                        System.out.println("Выберите адекватный вариант");
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
        for (int i = 0; i< Objects.requireNonNull(folder.listFiles()).length; i++) //что это?
         {
            System.out.println((i+1) + " - " + Objects.requireNonNull(folder.listFiles())[i].getName());
        }
        while (position<0||position>folder.listFiles().length) {
            System.out.println("Выберите номер пользователя");
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
        System.out.println("Профиль "+p.getName()+" загружен.");
        return p;
    }

    static void savePerson(Person person) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVEPATH+person.getName()))){
            oos.writeObject(person);
            System.out.println("Профиль "+person.getName()+" сохранен.");
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
