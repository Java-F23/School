import java.util.ArrayList;
import java.util.EnumSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.*;
import java.awt.*;


public class CommonData {
    public static DefaultTableModel courseTableModel;
    private JTable courseTable;

    public enum WorkingDays {
       U, M, T, W, R;
    }

    public enum Time {
       At_08_30, At_09_30,  At_10_30, At_11_30, At_12_30, At_13_30,  At_14_30, At_15_30;
    }

    public enum Department {
        MATH, SCIENCE, LITERATURE, // Add more departments as needed
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
        Department department = Department.valueOf(courseDepartment); // Assuming Department is an enum
        ArrayList<WorkingDays> days = getWorkingDaysList(daysStr); // Parse days
        Time time = Time.valueOf(timeStr); // Parse time
        Level level = Level.valueOf(levelStr); // Assuming Level is an enum

        return new Course2(courseTitle, courseSubject, department,days,time,level,courseInstructor, courseContent );
        }


    private static int getColumnIndex(String selectedOption){
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
    private static ArrayList<WorkingDays> getWorkingDaysList(String daysStr) {
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

}


