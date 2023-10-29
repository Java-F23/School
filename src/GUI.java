import javax.swing.*;
import java.util.ArrayList;
public class GUI {
    public static void main(String[] args) {
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
        CommonData.Level level = CommonData.Level.BEGINNER;
        ArrayList<CommonData.WorkingDays> days = new ArrayList<>();
        days.add(CommonData.WorkingDays.MONDAY);
        days.add(CommonData.WorkingDays.WEDNESDAY);
        CommonData.addCourseToTable("Course Title2","Course Subject","Instructor Name","Course Content",CommonData.Department.MATH,days,CommonData.Time.At_08_30,level);
        CommonData.addCourseToTable("Course Title33","Course Subject","Instructor Name","Course Content",CommonData.Department.MATH,days,CommonData.Time.At_08_30,level);

        CommonData.addCourseToTable("Course Title","Course Subject","Instructor Name","Course Content",CommonData.Department.MATH,days,CommonData.Time.At_08_30,level);
    }
}