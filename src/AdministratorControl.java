import javax.swing.JOptionPane;
import java.lang.reflect.Array;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;



public class AdministratorControl {
    private static AdministratorModel adminModel;
    private AdministratorView adminView;
    private static StudentModel studentModel;


    public AdministratorControl() {
        this.adminModel = new AdministratorModel();
//        this.adminView = new AdministratorView;
       // this.adminView = adminView;
        this.studentModel = new StudentModel();
    }
    public static void addNewCourse() {
        // Taking data from user
        String courseTitle = JOptionPane.showInputDialog("Enter course title:");
        String courseSubject = JOptionPane.showInputDialog("Enter course subject:");
        String department = JOptionPane.showInputDialog("Enter department:");
        String instructorName = JOptionPane.showInputDialog("Enter instructor name:");
        String content = JOptionPane.showInputDialog("Enter course content:");
        int level = Integer.parseInt(JOptionPane.showInputDialog("Enter course level:"));
        String scheduleDay = JOptionPane.showInputDialog("Enter course day:");
        int time =Integer.parseInt( JOptionPane.showInputDialog("Enter course time:"));
        Schedule newcourseSchedule = new Schedule(scheduleDay,time);

        InstructorModel courseInstructor = new InstructorModel(instructorName);

        // Create and return a CourseModel object with the collected details
        CourseModel newCourse= new CourseModel(courseTitle, courseSubject, department, courseInstructor, content, level, newcourseSchedule);
        adminModel.addNewCourse(newCourse);
        AdministratorView.isCoursesByDepartmentOpen=false;

    }
    public static void editExistingCourse() {
        try {
            if (adminModel == null) {
                adminModel = new AdministratorModel();
            }
            // Taking data from the user
            String oldCourseTitle = JOptionPane.showInputDialog("Enter the title of the course to be edited:");
            CourseModel oldCourse = adminModel.getCourseByTitle(oldCourseTitle);


            // Check if the old course exists
            if (oldCourse == null) {
                throw new ExceptionHandling.CourseNotFoundException("Old course not found.");
            }

            String newCourseTitle = JOptionPane.showInputDialog("Enter new course title:");
            String newCourseSubject = JOptionPane.showInputDialog("Enter new course subject:");
            String newDepartment = JOptionPane.showInputDialog("Enter new department:");
            String newInstructorName = JOptionPane.showInputDialog("Enter new instructor name:");
            String newContent = JOptionPane.showInputDialog("Enter new course content:");
            int newLevel = Integer.parseInt(JOptionPane.showInputDialog("Enter new course level:"));
            String newScheduleDay = JOptionPane.showInputDialog("Enter new course day:");
            int newTime = Integer.parseInt(JOptionPane.showInputDialog("Enter new course time:"));
            Schedule newCourseSchedule = new Schedule(newScheduleDay, newTime);

            InstructorModel newCourseInstructor = new InstructorModel(newInstructorName);

            // Create and return a new CourseModel object with the collected details
            CourseModel newCourse = new CourseModel(newCourseTitle, newCourseSubject, newDepartment,
                    newCourseInstructor, newContent, newLevel, newCourseSchedule);

            // Edit the existing course
            adminModel.editExistingCourse(oldCourseTitle, newCourse);
            AdministratorView.isCoursesByDepartmentOpen=false;

        } catch (ExceptionHandling.CourseNotFoundException e) {
            ExceptionHandling.handleCourseNotFoundException(e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input for course level or time. Please enter valid numbers.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void enrollStudent() {
        try {
            // Taking data from the user
            String studentName = JOptionPane.showInputDialog("Enter student name:");
            String courseTitle = JOptionPane.showInputDialog("Enter course title:");

            // Check if studentModel and adminModel are null
            if (adminModel == null) {
                adminModel = new AdministratorModel();
            }
            if (studentModel == null) {
                studentModel = new StudentModel();
                studentModel.setName(studentName);
            }

            // Get the student and course objects
            //StudentModel student = studentModel.getStudentByName(studentName);
            CourseModel course = adminModel.getCourseByTitle(courseTitle);

            if (!adminModel.getCourses().contains(course)) {
                throw new ExceptionHandling.CourseNotFoundException("Course not found.");
            }

            // Enroll the student in the course
            adminModel.enrollStudentInCourse(studentModel, course);
            AdministratorView.isCoursesByDepartmentOpen=false;


        } catch (ExceptionHandling.CourseNotFoundException e) {
            System.out.println("in AdminControl handle enroll student");
            ExceptionHandling.handleCourseNotFoundException(e.getMessage());
        }
    }

    public static void removeCourse() {
        try {
            if (adminModel == null) {
                adminModel = new AdministratorModel();
            }
            // Taking data from the user
            String courseTitleToRemove = JOptionPane.showInputDialog("Enter the title of the course to be removed:");
            CourseModel courseToRemove = adminModel.getCourseByTitle(courseTitleToRemove);

            // Check if the course exists
            if (courseToRemove == null) {
                throw new ExceptionHandling.CourseNotFoundException("Course not found.");
            }

            // Validate data for the course (optional, depending on your requirements)
            ExceptionHandling.validateCourse(courseToRemove);

            // Remove the course if validation passes
            adminModel.removeCourse(courseToRemove);

            // Display success message
            JOptionPane.showMessageDialog(null, "Course '" + courseTitleToRemove + "' removed successfully.");
            AdministratorView.isCoursesByDepartmentOpen=false;

        } catch (ExceptionHandling.CourseNotFoundException e) {
            ExceptionHandling.handleCourseNotFoundException(e.getMessage());
        } catch (ExceptionHandling.InvalidDataException e) {
            ExceptionHandling.handleInvalidDataException(e.getMessage());
        }
    }








}

