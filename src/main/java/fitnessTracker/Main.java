package fitness;

//        Приложение позволяет указать вид тренировки: отжимание, скакалка, приседания.
//        Доступны команды: начать тренировку (запускается таймер тренировки), закончить тренировку (таймер останавливается).
//        За отработанное время высчитывается количество потраченных калорий по формуле K*t=cal, где K - количество калорий в час,
//        затрачиваемое на определенный вид тренировки, t - время, засеченное трекером. "
//        От запуска к запуску программы данные должны сохраняться и общее количество калорий - суммироваться.
//        Персистенция данных приложения с помощью ObjectOutputStream +5 баллов или JAXB +10 баллов
//        Поддержка нескольких профилей пользователей приложением + 5 баллов"

//"Задачи с оценкой 20+ баллов доступны для зачётной\курсовой работы.
//
//        По ним необходим отчет. Требования к содержимому отчёта:
//        0. цель работы, постановка задач работы,
//        1. описание архитектуры приложения (UML class diagram, component diagram, sequence diagram),
//        2. скриншоты основных экранов приложения,
//        3. оценка покрытия кода модульными тестами,
//        4. перечисление использованных API и библиотек,
//        5. вывод по проделанной работе."


//Глубоко расписывать не нужно
import com.google.common.base.Stopwatch;

import fitness.TrainingList.*;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class Main {

    static String SAVEPATH = "src/main/java/fitness/save/";
    public static void main(String[] args) throws InterruptedException {
        //Сделать класс трекира

        Scanner in = new Scanner(System.in);
        int i = 0;
        Person person = null;
        Training training = null;
        Stopwatch stopwatch;

        while (true) {
            while ((i != 1  && i != 2)) {
                System.out.println("1 - To create new person\n2 - To load person from file\n0 - To close");
                i = in.nextInt();
                switch (i) {
                    case 1:
                        System.out.println("Write profile name:");
                        String name = in.next();
                        if (profileNameIsFree(name)) {
                            person = new Person(name);
                            System.out.println("Profile is " + person.getName() + " has been created");
                        } else {
                            System.out.println("That profile name's taken");
                            i = 0;
                        }
                        break;
                    case 2:
                        if (canLoad()) {
                            System.out.println("Select profile:");
                            person = load();
                        } else {
                            System.out.println("Save file not found");
                        }
                        break;
                    case 0:
                        return;
                }
            }
            while (i != 9 && person != null) {
                System.out.println("1 - To start the training\n2 - To info about training\n3 - To save\n9 - To exit\n0 - To close ");
                i = in.nextInt();
                switch (i) {
                    case 0:
                        return;
                    case 9:
                        person = null;
                        break;
                    case 1:
                        System.out.println("Select the trainig");
                        System.out.println("1 - Runing\n2 - Swimming\n3 - Situps\n4 - SkippingRope\n - Pushups");
                        i = 0;
                        while (i != 1 && i != 2 && i != 3 && i != 4 && i != 5)  { //можно использовать enum
                            i = in.nextInt();
                            switch (i) {
                                case 1 -> training = new Training(new Runing());
                                case 2 -> training = new Training(new Swimming());
                                case 3 -> training = new Training(new Situps());
                                case 4 -> training = new Training(new SkippingRope());
                                case 5 -> training = new Training(new Pushups());
                                default -> System.out.println("Writhe the right number");
                            }
                        }
                        System.out.println("Press Enter to start the training");
                        in.next();
                        stopwatch = Stopwatch.createStarted();
                        System.out.println("Press Enter to end the training");
                        in.next();
                        stopwatch.stop();
                        training.endTrainig(stopwatch.elapsed(TimeUnit.SECONDS));
                        person.addTraining(training);
                        break;
                    case 2:
                        System.out.println("Select the info");
                        System.out.println("1 - Training info\n2 - kCal info");
                        i = 0;
                        while (i != 1 && i != 2){
                            i = in.nextInt();
                            switch (i) {
                                case 1 -> person.seeTraining();
                                case 2 -> System.out.println("Total kCal for all time: " + person.getKcal());
                                default -> System.out.println("Writhe the right number");
                            }
                        }
                        break;
                    case 3:
                        save(person);
                        System.out.println("Writhe the right number");
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
            System.out.println("Write to number");
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
        System.out.println("Profile "+p.getName()+" uploaded");
        return p;
    }

    static void save(Person person) {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVEPATH+person.getName()))){
            oos.writeObject(person);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    static boolean profileNameIsFree(String name){
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
