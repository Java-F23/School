import javax.swing.JOptionPane;

public class AdministratorControl {
    private static AdministratorModel adminModel;
    private AdministratorView adminView;

    public AdministratorControl() {
        this.adminModel = new AdministratorModel();
//        this.adminView = new AdministratorView;
        this.adminView = adminView;
    }
    public static void addNewCourse() {
        // Taking data from user
        String courseTitle = JOptionPane.showInputDialog("Enter course title:");
        String courseSubject = JOptionPane.showInputDialog("Enter course subject:");
        String department = JOptionPane.showInputDialog("Enter department:");
        String instructorName = JOptionPane.showInputDialog("Enter instructor name:");
        String content = JOptionPane.showInputDialog("Enter course content:");
        int level = Integer.parseInt(JOptionPane.showInputDialog("Enter course level:"));
        String scheduleName = JOptionPane.showInputDialog("Enter course day:");
        int scheduleTime = Integer.parseInt(JOptionPane.showInputDialog("Enter course time:"));
        Schedule newcourseSchedule = new Schedule(scheduleName,scheduleTime);

        InstructorModel courseInstructor = new InstructorModel(instructorName);

        // Create and return a CourseModel object with the collected details
        CourseModel newCourse= new CourseModel(courseTitle, courseSubject, department, courseInstructor, content, level, newcourseSchedule);
        adminModel.addNewCourse(newCourse);
    }
    public static void enrollStudent() {
      try{
          // Taking data from user
        String studentName = JOptionPane.showInputDialog("Enter student name:");
        String courseTitle = JOptionPane.showInputDialog("Enter course title:");

        // Get the student and course objects
          StudentModel student = new StudentModel(studentName);
        CourseModel course = adminModel.getCourseByTitle(courseTitle);
        System.out.println(adminModel.getCourses());
        if (!adminModel.getCourses().contains(course)) {
            System.out.println("in AdminControl enroll student");
            throw new ExceptionHandling.CourseNotFoundException("Course not found.");
        }
        if(adminModel == null)
          {
              adminModel = new AdministratorModel();
          }
          // Enroll the student in the course
          adminModel.enrollStudentInCourse(student, course);

          // Display success message
          JOptionPane.showMessageDialog(null, "Student '" + studentName + "' enrolled in course '" + courseTitle + "' successfully!");

      } catch(ExceptionHandling.CourseNotFoundException e) {
          System.out.println("in AdminControl handel enroll student");
          ExceptionHandling.handleCourseNotFoundException(e.getMessage());
      }

    }
}
