import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;


public class AdministratorModel {
    private ArrayList<CourseModel> courses;
    private ArrayList<InstructorModel> instructors;
    private Map<String, List<CourseModel>> coursesByDepartment;
    private Map<String, List<Student>> studentEnrollment;
    private Map<String, List<Assignment>> classAssignments;
    private Map<String, List<String>> calendarEvents;

    public AdministratorModel() {
        this.courses = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.coursesByDepartment = new HashMap<>();
        this.studentEnrollment = new HashMap<>();
        this.classAssignments = new HashMap<>();
        this.calendarEvents = new HashMap<>();
    }

    // Methods to modify the state

    // Add a new course
    public void addNewCourse(CourseModel course) {
        courses.add(course);
        addToDepartmentMap(course);
    }

    // Edit existing course
    public void editExistingCourse(CourseModel oldCourse, CourseModel newCourse) {
        courses.remove(oldCourse);
        courses.add(newCourse);
        updateDepartmentMap(oldCourse, newCourse);
    }

    // Add or remove an instructor
    public void addInstructor(InstructorModel instructor) {
        instructors.add(instructor);
    }

    public void removeInstructor(InstructorModel instructor) {
        instructors.remove(instructor);
    }

    // Categorize courses by department
    private void addToDepartmentMap(CourseModel course) {
        String department = course.getDepartment();
        coursesByDepartment.computeIfAbsent(department, k -> new ArrayList<>()).add(course);
    }

    private void updateDepartmentMap(CourseModel oldCourse, CourseModel newCourse) {
        String oldDepartment = oldCourse.getDepartment();
        String newDepartment = newCourse.getDepartment();

        coursesByDepartment.computeIfPresent(oldDepartment, (k, v) -> {
            v.remove(oldCourse);
            return v.isEmpty() ? null : v;
        });

        addToDepartmentMap(newCourse);
    }

    // Track and manage student enrollment
    public void enrollStudentInCourse(Student student, CourseModel course) {
        String courseTitle = course.getCourseTitle();
        studentEnrollment.computeIfAbsent(courseTitle, k -> new ArrayList<>()).add(student);
    }

    public void removeStudentFromCourse(Student student, CourseModel course) {
        String courseTitle = course.getCourseTitle();
        studentEnrollment.computeIfPresent(courseTitle, (k, v) -> {
            v.remove(student);
            return v.isEmpty() ? null : v;
        });
    }

    public int countStudentsInCourse(CourseModel course) {
        String courseTitle = course.getCourseTitle();
        return studentEnrollment.getOrDefault(courseTitle, new ArrayList<>()).size();
    }

    public List<CourseModel> getCoursesEnrolledForStudent(Student student) {
        List<CourseModel> enrolledCourses = new ArrayList<>();
        String studentName = student.getName();

        for (Map.Entry<String, List<Student>> entry : studentEnrollment.entrySet()) {
            if (entry.getValue().stream().anyMatch(s -> s.getName().equals(studentName))) {
                enrolledCourses.add(getCourseByTitle(entry.getKey()));
            }
        }

        return enrolledCourses;
    }

    private CourseModel getCourseByTitle(String courseTitle) {
        return courses.stream().filter(course -> course.getCourseTitle().equals(courseTitle)).findFirst().orElse(null);
    }

    // Track and manage class assignments
    public void addAssignmentForCourse(Assignment assignment, CourseModel course) {
        String courseTitle = course.getCourseTitle();
        classAssignments.computeIfAbsent(courseTitle, k -> new ArrayList<>()).add(assignment);
    }

    public void removeAssignmentForCourse(Assignment assignment, CourseModel course) {
        String courseTitle = course.getCourseTitle();
        classAssignments.computeIfPresent(courseTitle, (k, v) -> {
            v.remove(assignment);
            return v.isEmpty() ? null : v;
        });
    }

    public List<Assignment> displayAssignmentsForCourse(CourseModel course) {
        String courseTitle = course.getCourseTitle();
        return classAssignments.getOrDefault(courseTitle, new ArrayList<>());
    }

    // Calculate student's GPA
    public BigDecimal calculateStudentGPA(Student student) {
        // Implementation to calculate GPA based on grades in courses
        // You may need to access the grades and credits of each enrolled course
        // and perform the GPA calculation logic
        // For simplicity, let's assume a basic GPA calculation here
        List<CourseModel> enrolledCourses = getCoursesEnrolledForStudent(student);
        BigDecimal totalGradePoints = BigDecimal.ZERO;
        int totalCredits = 0;

        for (CourseModel course : enrolledCourses) {
            BigDecimal courseGrade = course.getGrades().getOrDefault(student.getName(), BigDecimal.ZERO);
            int courseCredits = 3; // Assuming each course has 3 credits, adjust as needed
            totalGradePoints = totalGradePoints.add(courseGrade.multiply(BigDecimal.valueOf(courseCredits)));
            totalCredits += courseCredits;
        }

        if (totalCredits == 0) {
            return BigDecimal.ZERO; // Avoid division by zero
        }

        return totalGradePoints.divide(BigDecimal.valueOf(totalCredits), 2, BigDecimal.ROUND_HALF_UP);
    }

    // Add/remove events in the calendar
    public void addEventToCalendar(String date, String event) {
        calendarEvents.computeIfAbsent(date, k -> new ArrayList<>()).add(event);
    }

    public void removeEventFromCalendar(String date, String event) {
        calendarEvents.computeIfPresent(date, (k, v) -> {
            v.remove(event);
            return v.isEmpty() ? null : v;
        });
    }


    @Override
    public String toString() {
        return "AdministratorModel{" +
                "courses=" + courses +
                ", instructors=" + instructors +
                ", coursesByDepartment=" + coursesByDepartment +
                ", studentEnrollment=" + studentEnrollment +
                ", classAssignments=" + classAssignments +
                ", calendarEvents=" + calendarEvents +
                '}';
    }
}
