package StudentQuizGrades;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public interface UserIO {



    String getStudent(String prompt);

    //OUTPUTS
    Set viewStudents();
    void addStudent(String student);
    void removeStudent(String student);
    ArrayList viewStudentScore(String student);
    Integer viewStudentAvg(String student);
    Integer viewClassroomAvg();
    Map<Integer, ArrayList<String>> viewClassroomHigh();
    void viewClassroomLow();


}