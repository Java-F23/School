import javax.swing.*;
import java.util.ArrayList;
public class GUI {
    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        CommonData.Level level = CommonData.Level.BEGINNER;
        ArrayList<CommonData.WorkingDays> days = new ArrayList<>();
        days.add(CommonData.WorkingDays.M);
        days.add(CommonData.WorkingDays.W);

        //Example for the available courses
        CommonData.addCourseToTable("Introduction to Programming", "Computer Science", "Dr. Smith", "Basics of programming", CommonData.Department.SCIENCE, days, CommonData.Time.At_08_30, CommonData.Level.BEGINNER);
        CommonData.addCourseToTable("Database Management", "Information Systems", "Prof. Johnson", "Database design and management", CommonData.Department.SCIENCE, days, CommonData.Time.At_11_30, CommonData.Level.INTERMEDIATE);
        CommonData.addCourseToTable("Advanced Calculus", "Mathematics", "Prof. Brown", "Advanced calculus concepts", CommonData.Department.MATH, days, CommonData.Time.At_13_30, CommonData.Level.ADVANCED);

    }
}