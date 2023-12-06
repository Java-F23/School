import java.util.ArrayList;
import java.util.Optional;

public class StudentControl {
    private static StudentView view;
    private static AdministratorModel adminModel;
    private StudentModel studentModel;


    // Constructor
    public StudentControl( StudentView view, AdministratorModel adminModel, StudentModel studentModel) {
        this.view = view;
        this.adminModel = adminModel;
        this.studentModel = studentModel;
    }

    // Method to handle browsing current courses
    public static String  browseCurrentCourses() {
        // Get the list of courses from the AdministratorModel
        ArrayList<CourseModel> courses = CSVHandler.loadCoursesFromCsv("courses.csv");

        // Display courses in the view
        StringBuilder coursesText = new StringBuilder();
        for (int i = 0; i < courses.size(); i++) {
            coursesText.append(i + 1).append(". ").append(courses.get(i).getCourseTitle()).append("\n");
        }

        // Return the string representation of courses
        return coursesText.toString();
    }

    public static String browseCourseSchedules() {
        // Get the list of courses from the CSV file
        ArrayList<CourseModel> courses = CSVHandler.loadCoursesFromCsv("courses.csv");

        // Display course titles and schedules
        StringBuilder schedulesText = new StringBuilder();
        for (int i = 0; i < courses.size(); i++) {
            CourseModel course = courses.get(i);
            schedulesText.append(i + 1).append(". ")
                    .append(course.getCourseTitle())
                    .append(": ")
                    .append(course.getSchedule())
                    .append("\n");
        }

        // Return the string representation of course schedules
        return schedulesText.toString();
    }
    public static String browseAllCourseDetails() {
        // Get the list of courses from the CSV file
        ArrayList<CourseModel> courses = CSVHandler.loadCoursesFromCsv("courses.csv");
        // Display all course details
        StringBuilder detailsText = new StringBuilder();
        for (int i = 0; i < courses.size(); i++) {
            CourseModel course = courses.get(i);
            detailsText.append(i + 1).append(". ")
                    .append("Title: ").append(course.getCourseTitle()).append("\n")
                    .append("Subject: ").append(course.getCourseSubject()).append("\n")
                    .append("Department: ").append(course.getDepartment()).append("\n")
                    .append("Instructor: ").append(course.getInstructor().getName()).append("\n")
                    .append("Content: ").append(course.getContent()).append("\n")
                    .append("Level: ").append(course.getLevel()).append("\n")
                    .append("Schedule: ").append(course.getSchedule()).append("\n\n");
        }
        // Return the string representation of all course details
        return detailsText.toString();
    }
    public static String displayCourseDetailsByName(String courseName) {
        // Get the list of courses from the CSV file
        ArrayList<CourseModel> courses = CSVHandler.loadCoursesFromCsv("courses.csv");

        // Find the course with the specified name
        Optional<CourseModel> matchingCourse = courses.stream()
                .filter(course -> course.getCourseTitle().equalsIgnoreCase(courseName))
                .findFirst();

        // Display course details if found, otherwise, return an error message
        if (matchingCourse.isPresent()) {
            CourseModel course = matchingCourse.get();
            return "Course Details for '" + courseName + "':\n" +
                    "Title: " + course.getCourseTitle() + "\n" +
                    "Subject: " + course.getCourseSubject() + "\n" +
                    "Department: " + course.getDepartment() + "\n" +
                    "Instructor: " + course.getInstructor().getName() + "\n" +
                    "Content: " + course.getContent() + "\n" +
                    "Level: " + course.getLevel() + "\n" +
                    "Schedule: " + course.getSchedule();
        } else {
            return "Course '" + courseName + "' not found.";
        }
    }

//add method for mark course as favourite

}
