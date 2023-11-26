import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class StudentModel {
    private String name;
    private List<CourseModel> courseList;
    private List<CourseModel> favoriteCourses;
    private BigDecimal GPA;
    private List<CourseModel> pastCourses;
    private Map<String, List<String>> calendarEvents;
    public StudentModel(String name) {
        this.name = name;
        this.courseList = new ArrayList<>();
        this.favoriteCourses = new ArrayList<>();
        this.GPA = BigDecimal.ZERO; // Initial GPA is zero
    }

    // Methods for browsing, searching, and viewing courses
    public List<String> browseCourses(List<CourseModel> allCourses) {
        List<String> courseTitles = new ArrayList<>();
        for (CourseModel course : allCourses) {
            courseTitles.add(course.getCourseTitle());
        }
        return courseTitles;
    }

    public CourseModel searchCourse(List<CourseModel> allCourses, String courseTitle) {
        for (CourseModel course : allCourses) {
            if (course.getCourseTitle().equalsIgnoreCase(courseTitle)) {
                return course;
            }
        }
        return null;
    }

    public String viewCourseDetails(CourseModel course) {
        StringBuilder courseDetails = new StringBuilder();
        try {
            // Validate the course before displaying details
            ExceptionHandling.validateCourse(course);

            if (course != null) {
                courseDetails.append("Course Details:\n");
                courseDetails.append("Title: ").append(course.getCourseTitle()).append("\n");
                courseDetails.append("Subject: ").append(course.getCourseSubject()).append("\n");
                // Add other details as needed
            }
        } catch (ExceptionHandling.InvalidDataException e) {
            // Handle the exception, or just return an error message
            courseDetails.append("Error: ").append(e.getMessage());
        }
        return courseDetails.toString();
    }

    public String viewCourseSchedule(CourseModel course) {
        StringBuilder courseSchedule = new StringBuilder();
        try {
            // Validate the course before retrieving schedule
            ExceptionHandling.validateCourse(course);

            if (course != null) {
                courseSchedule.append("Course Schedule: ").append(course.getSchedule()).append("\n");
            }
        } catch (ExceptionHandling.InvalidDataException e) {
            // Handle the exception, or just return an error message
            courseSchedule.append("Error: ").append(e.getMessage());
        }

        // Return the schedule string instead of printing
        return courseSchedule.toString();
    }

    // Methods for managing favorite courses
    public void markAsFavorite(CourseModel course) {
        if (course != null && !favoriteCourses.contains(course)) {
            favoriteCourses.add(course);
            System.out.println("Marked as favorite: " + course.getCourseTitle());
        } else {
            System.out.println("Course is already a favorite or not found.");
        }
    }

    public void printFavoriteCourses() {
        if (favoriteCourses.isEmpty()) {
            System.out.println("No favorite courses.");
        } else {
            System.out.println("Favorite Courses:");
            for (CourseModel course : favoriteCourses) {
                System.out.println(course.getCourseTitle());
            }
        }
    }

    // Methods for enrollment and grades
    public void enrollInCourse(CourseModel course) {
        if (course != null && !courseList.contains(course)) {
            courseList.add(course);
            System.out.println("Enrolled in course: " + course.getCourseTitle());
        } else {
            System.out.println("Course is already enrolled or not found.");
        }
    }

    public void printMyCourses() {
        if (courseList.isEmpty()) {
            System.out.println("Not enrolled in any courses.");
        } else {
            System.out.println("Enrolled Courses:");
            for (CourseModel course : courseList) {
                System.out.println(course.getCourseTitle());
            }
        }
    }

    public void viewClassGrades(CourseModel course) {
        // Implement logic to view grades for a specific course
    }

    // Methods for calendar and past records
    public void viewCalendar() {
        // Implement logic to view the student's calendar
    }

    public void accessPastClassesSchedulesAndPerformance() {
        // Implement logic to access past classes schedules and performance records
    }

    // Other methods as needed...

    // Getters and setters for attributes

    public String getName() {
        return name;
    }

    public List<CourseModel> getCourseList() {
        return courseList;
    }

    public List<CourseModel> getFavoriteCourses() {
        return favoriteCourses;
    }

    public BigDecimal getGPA() {
        return GPA;
    }
}
