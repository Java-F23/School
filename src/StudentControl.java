import java.util.ArrayList;

public class StudentControl {
    private Student model;
    private StudentView view;
    private AdministratorModel adminModel;
    private StudentModel studentModel;

    // Constructor
    public StudentControl(Student model, StudentView view, AdministratorModel adminModel, StudentModel studentModel) {
        this.model = model;
        this.view = view;
        this.adminModel = adminModel;
        this.studentModel = studentModel;
    }

    // Method to handle browsing current courses
    public void browseCurrentCourses() {
        // Get the list of courses from the AdministratorModel
        ArrayList<CourseModel> courses = adminModel.getCourses();

        // Display courses in the view
        StringBuilder coursesText = new StringBuilder();
        for (int i = 0; i < courses.size(); i++) {
            coursesText.append(i + 1).append(". ").append(courses.get(i).getCourseTitle()).append("\n");
        }

        // Update the view to display current courses
        view.displayCurrentCourses(coursesText.toString());
    }

    // Add more methods as needed to handle student-related actions
}
