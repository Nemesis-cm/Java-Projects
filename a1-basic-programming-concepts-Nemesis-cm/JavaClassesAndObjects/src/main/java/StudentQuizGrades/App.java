package StudentQuizGrades;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        getCommand();
    }

    public static void getCommand() {
        Integer command;
        Classroom mine = new Classroom();
        Scanner sc = new Scanner(System.in);

        mine.addStudent("Tom");
        mine.addStudent("Blake");
        mine.addStudent("Tina");

        System.out.println(mine.viewClassroomHigh());
    }
}
