import java.util.ArrayList;

public class Student {
    private String name;
    private String enrolledClasses;
    private double totalCredits;
    private double totalGradePoints;

    public Student(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrolledClasses() {
        return enrolledClasses;
    }

    public void setEnrolledClasses(String enrolledClasses) {
        this.enrolledClasses = enrolledClasses;
    }
    public double getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(double totalCredits) {
        this.totalCredits = totalCredits;
    }

    public double getTotalGradePoints() {
        return totalGradePoints;
    }

    public void setTotalGradePoints(double totalGradePoints)
    {
        this.totalGradePoints = totalGradePoints;
    }

    public void viewAvailableCourses(Administrator administrator) {
        ArrayList<Course> availableCourses = administrator.getCourses();
        System.out.println("Available Courses:");
        for (Course course : availableCourses) {
            System.out.println(administrator.getCourses());
        }
    }



}
