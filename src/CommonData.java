import java.util.ArrayList;
import java.util.EnumSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;


public class CommonData {
    public static DefaultTableModel courseTableModel;
    private static ArrayList<String> instructorsList = new ArrayList<>();
    private JTable courseTable;
    public enum WorkingDays {
       U, M, T, W, R;
    }

    public enum Time {
       At_08_30, At_09_30,  At_10_30, At_11_30, At_12_30, At_13_30,  At_14_30, At_15_30;
    }

    public enum Department {
        SCIENCE, MATH, LITERATURE;
    }

    public enum Level {
        BEGINNER, INTERMEDIATE, ADVANCED;
    }

    // Initialize the table model
    static {
        CommonData.Level level = CommonData.Level.BEGINNER;
        ArrayList<CommonData.WorkingDays> days = new ArrayList<>();
        days.add(CommonData.WorkingDays.M);
        days.add(CommonData.WorkingDays.W);
        courseTableModel = new DefaultTableModel();
        courseTableModel.addColumn("Title");
        courseTableModel.addColumn("Subject");
        courseTableModel.addColumn("Instructor");
        courseTableModel.addColumn("Content");
        courseTableModel.addColumn("Department");
        courseTableModel.addColumn("Days");
        courseTableModel.addColumn("Time");
        courseTableModel.addColumn("Level");
        courseTableModel.addRow(new Object[]{"Title", "Subject", "Instructor", "Content", "Department", "Days", "Time", "Level"});
    }


    // Method to add a course to the table
    public static void addCourseToTable( String title, String subject, String instructor, String content, Department department, ArrayList <WorkingDays> days, Time time, Level level)
    {
        courseTableModel.addRow(new Object[]{title, subject, instructor, content, department, days, time, level});
    }
    public static Course2 searchCourseByCriteria(String searchTerm, String searchOption) {
        Course2 foundCourse = new Course2(null,null,null,null,null,null,null,null); // Initialize foundCourse to null
        boolean courseFound = false;
        for (int row = 1; row < courseTableModel.getRowCount(); row++) {
            try {
                String cellValue = courseTableModel.getValueAt(row, getColumnIndex(searchOption)).toString();
                if (cellValue.equalsIgnoreCase(searchTerm)) {
                    foundCourse = getCourseDetails(row);

                    courseFound = true; // Set the flag to exit the loop
                }
            } catch (Exception ex) {
                // Handle any unexpected exceptions during the search
                ex.printStackTrace();
            }
        }
        return foundCourse; //Course or null
    }

    public static Course2 getCourseDetails(int rowIndex) {

        String courseTitle = courseTableModel.getValueAt(rowIndex, 0).toString();
        String courseSubject = courseTableModel.getValueAt(rowIndex, 1).toString();
        String courseInstructor = courseTableModel.getValueAt(rowIndex, 2).toString();
        String courseContent = courseTableModel.getValueAt(rowIndex, 3).toString();
        String courseDepartment = courseTableModel.getValueAt(rowIndex, 4).toString();
        String daysStr = courseTableModel.getValueAt(rowIndex, 5).toString();
        String timeStr = courseTableModel.getValueAt(rowIndex, 6).toString();
        String levelStr = courseTableModel.getValueAt(rowIndex, 7).toString();

        // Convert department,days, time, and level strings to their respective enum types
        Department department = Department.valueOf(courseDepartment);
        ArrayList<WorkingDays> days = getWorkingDaysList(daysStr);
        Time time = Time.valueOf(timeStr);
        Level level = Level.valueOf(levelStr);

        return new Course2(courseTitle, courseSubject, department,days,time,level,courseInstructor, courseContent );
        }


    public static int getColumnIndex(String selectedOption){
        switch (selectedOption) {
            case "Title":
                return 0;
            case "Subject":
                return 1;
            case "Instructor":
                return 2;
            case "Department":
                return 4;
            default:
                return -1;
        }
    }
    public static ArrayList<WorkingDays> getWorkingDaysList(String daysStr) {
        ArrayList<WorkingDays> daysList = new ArrayList<>();
        String[] dayTokens = daysStr.replaceAll("[\\[\\]\\s]", "").split(",");
        for (String day : dayTokens) {
           try {
            WorkingDays dayEnum = WorkingDays.valueOf(day.toUpperCase()); // Convert to uppercase
                daysList.add(dayEnum);
           } catch (IllegalArgumentException ex) {
                // Handle invalid day string, or log an error
            }
        }
        return daysList;
    }

    public static ArrayList<Course2> getAllCoursesFromTableModel(DefaultTableModel courseTableModel) {

        ArrayList<Course2> allCourses = new ArrayList<>();

        for (int row = 0; row < courseTableModel.getRowCount(); row++) {
            String title = courseTableModel.getValueAt(row, getColumnIndex("Title")).toString();
            String subject = courseTableModel.getValueAt(row, getColumnIndex("Subject")).toString();
            String instructor = courseTableModel.getValueAt(row, getColumnIndex("Instructor")).toString();
            String departmentStr = courseTableModel.getValueAt(row, getColumnIndex("Department")).toString();
            String daysStr = courseTableModel.getValueAt(row, getColumnIndex("Days")).toString();
            String timeStr = courseTableModel.getValueAt(row, getColumnIndex("Time")).toString();
            String levelStr = courseTableModel.getValueAt(row, getColumnIndex("Level")).toString();
            String content = courseTableModel.getValueAt(row, getColumnIndex("Content")).toString();

            // Convert department,days, time, and level strings to their respective enum types
            Department department = Department.valueOf(departmentStr);
            ArrayList<WorkingDays> days = getWorkingDaysList(daysStr);
            Time time = Time.valueOf(timeStr);
            Level level = Level.valueOf(levelStr);
            // Create a new Course2 instance and add it to the list
            Course2 course = new Course2(title, subject, department, days, time, level, instructor, content);
            allCourses.add(course);
        }

        return allCourses;
    }

    public static ArrayList<String> getAllCourseTitlesFromTableModel(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        ArrayList<String> courseTitles = new ArrayList<>();

        for (int row = 1; row < model.getRowCount(); row++) {
            String title = model.getValueAt(row, getColumnIndex("Title")).toString();
            courseTitles.add(title);
        }

        return courseTitles;
    }
    public static ArrayList<Course2> getCoursesByDepartment(CommonData.Department department) {
        ArrayList<Course2> coursesByDepartment = new ArrayList<>();

        for (int row = 1; row < courseTableModel.getRowCount(); row++) {
            Course2 course = getCourseDetails(row);
            if (course.getDepartment() == department) {
                coursesByDepartment.add(course);
            }
        }

        return coursesByDepartment;
    }
    public  static ArrayList<String> getInstructorsList() {
        return instructorsList;
    }

    public static void setInstructorsList(ArrayList<String> instructorslist) {
        instructorsList = instructorslist;
    }
/*    public static double calculateStudentGPA(String studentName, Course2 course) {
        double gpa = (StudentWelcomeFrame.getTotalGradePoints()/StudentWelcomeFrame.getTotalCredits());

        return gpa;
    }
    */
public static double calculateStudentGPA(String studentName, Course2 course) {
    BigDecimal totalGradePoints = StudentWelcomeFrame.getTotalGradePoints();
    BigDecimal totalCredits = StudentWelcomeFrame.getTotalCredits();

    // Check if the values are null
    if (totalGradePoints == null || totalCredits == null) {
        return 3.5;  //for test
    }

    if (totalCredits.compareTo(BigDecimal.ZERO) == 0) {
        return 0.0;
    }

    BigDecimal gpa = totalGradePoints.divide(totalCredits, 2, BigDecimal.ROUND_HALF_UP);

    return gpa.doubleValue();
}
}


