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



}


