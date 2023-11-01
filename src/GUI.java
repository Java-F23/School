import javax.swing.*;
import java.util.ArrayList;
public class GUI {
    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        ArrayList<CommonData.WorkingDays> days = new ArrayList<>();
        ArrayList<String> instructorlist = new ArrayList<>();
        days.add(CommonData.WorkingDays.M);
        days.add(CommonData.WorkingDays.W);

        //Example for the available courses
        Course2 course1 = new Course2("Introduction to Programming", "Computer Science", CommonData.Department.SCIENCE,days,CommonData.Time.At_08_30,CommonData.Level.BEGINNER,"Dr. Smith", "Basics of programming");
        Course2 course2 = new Course2("Database Management", "Information Systems", CommonData.Department.SCIENCE,days,CommonData.Time.At_11_30,CommonData.Level.INTERMEDIATE,"Dr. Johnson", "Database design and management");
        Course2 course3 = new Course2("Advanced Calculus", "Mathematics", CommonData.Department.MATH,days,CommonData.Time.At_13_30,CommonData.Level.ADVANCED,"Dr. Brown", "Advanced calculus concepts");
        //Example for instructor list
        instructorlist.add("Dr. Smith");
        instructorlist.add("Dr. Johnson");
        instructorlist.add("Dr. Brown");

        CommonData.addCourseToTable(course1.getTitle(),course1.getSubject(),course1.getInstructor(), course1.getContent(), course1.getDepartment(),course1.getDays(),course1.getTime(),course1.getLevel());
        CommonData.addCourseToTable(course2.getTitle(),course2.getSubject(),course2.getInstructor(), course2.getContent(), course2.getDepartment(),course2.getDays(),course2.getTime(),course2.getLevel());
        CommonData.addCourseToTable(course1.getTitle(),course3.getSubject(),course3.getInstructor(), course3.getContent(), course3.getDepartment(),course3.getDays(),course3.getTime(),course3.getLevel());
        CommonData.setInstructorsList(instructorlist);

        //To test the gui as student skipping the login frame
          /*  StudentWelcomeFrame user1 = new StudentWelcomeFrame("Ahmed");
            // Create an ArrayList of Course2 to represent the initial enrolled courses
            ArrayList<Course2> initialEnrolledCourses = new ArrayList<>();

            // Add some courses to the initialEnrolledCourses ArrayList
            initialEnrolledCourses.add(course1);
            initialEnrolledCourses.add(course3);
            //Get course grades
            course1.addStudentGrade("Ahmed","A");
            course3.addStudentGrade("Ahmed","A-");
            user1.setEnrolledCourses(initialEnrolledCourses);
          */
    }
}