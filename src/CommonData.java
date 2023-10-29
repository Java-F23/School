import java.util.ArrayList;
import java.util.EnumSet;
import javax.swing.table.DefaultTableModel;

public class CommonData {
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

    // Method to add a course to the table
    public static void addCourseToTable(DefaultTableModel courseTableModel, String title, String subject,
                                        String instructor, String content, Department department, ArrayList <WorkingDays> days,
                                        Time time, Level level) {
        courseTableModel.addRow(new Object[]{title, subject, instructor, content, department, days, time, level});
    }
}
