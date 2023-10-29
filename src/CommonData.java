import java.util.ArrayList;
import java.util.EnumSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;


public class CommonData {
    public static DefaultTableModel courseTableModel;
    public enum WorkingDays {
       SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY;
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
        days.add(CommonData.WorkingDays.MONDAY);
        days.add(CommonData.WorkingDays.WEDNESDAY);
        courseTableModel = new DefaultTableModel();
        courseTableModel.addColumn("Title");
        courseTableModel.addColumn("Subject");
        courseTableModel.addColumn("Instructor");
        courseTableModel.addColumn("Content");
        courseTableModel.addColumn("Department");
        courseTableModel.addColumn("Days");
        courseTableModel.addColumn("Time");
        courseTableModel.addColumn("Level");
    }


    // Method to add a course to the table
    public static void addCourseToTable( String title, String subject, String instructor, String content, Department department, ArrayList <WorkingDays> days, Time time, Level level)
    {
        courseTableModel.addRow(new Object[]{title, subject, instructor, content, department, days, time, level});
    }

    // Method to get available courses
    public static ArrayList<Course2> getAvailableCourses() {
        ArrayList<Course2> courses = new ArrayList<>();

        for (int row = 0; row < courseTableModel.getRowCount(); row++) {
            String title = courseTableModel.getValueAt(row, 0).toString();
            String subject = courseTableModel.getValueAt(row, 1).toString();
            String instructor = courseTableModel.getValueAt(row, 2).toString();
            String content = courseTableModel.getValueAt(row, 3).toString();
            Department department = (Department) courseTableModel.getValueAt(row, 4);
            ArrayList<WorkingDays> days = (ArrayList<WorkingDays>) courseTableModel.getValueAt(row, 5);
            Time time = (Time) courseTableModel.getValueAt(row, 6);
            Level level = (Level) courseTableModel.getValueAt(row, 7);

            Course2 course = new Course2(title, subject,department, days,time, level,instructor, content);
            courses.add(course);

            }

        return courses;
    }
}


