import java.util.ArrayList;

public class Student {
    private String name;
    private String enrolledClasses;
    private double totalCredits;
    private double totalGradePoints;

    public Student(String name) {
        this.name = name;
    }
    private int counter; //used for getCourseDetails
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
        System.out.println("Available Courses are:");
        System.out.println("Course Title        Course Subject");

        for (Course course : availableCourses) {
            System.out.println(course.getTitle() + "                  " + course.getSubject());
        }
    }


    public void searchAndPrintCourses(Administrator administrator, String title, String subject, String instructor, String department, String level, String content) {
        ArrayList<Course> availableCourses = administrator.getCourses();
        ArrayList<Course> matchedCourses = new ArrayList<>();
        for (Course course : availableCourses) {
            if ((title == null || course.getTitle().equals(title)) &&
                    (subject == null || course.getSubject().equals(subject)) &&
                    (instructor == null || course.getInstructor().equals(instructor)) &&
                    (department == null || course.getDepartment().equals(department)) &&
                    (level == null || course.getLevel().equals(level)) &&
                    (content == null || course.getContent().equals(content))) {
                matchedCourses.add(course);
            }
        }
        if (matchedCourses.isEmpty()) {
            System.out.println("No courses found.");
           System.out.println(title);
        } else {
            System.out.println("Matched Available Courses:");
            for (Course course : matchedCourses) {
                System.out.println( course.getTitle());
            }
        }
    }

    public void getCourseDetails(Administrator administrator,String courseTitle) {
        for (Course course : administrator.getCourses()) {
            if (course.getTitle().equals(courseTitle)) {
                System.out.println( "Title: " + course.getTitle() + "\nSubject: " + course.getSubject() + "\nInstructor: " + course.getInstructor() + "\nDepartment: " + course.getDepartment() + "\nLevel: " + course.getLevel() + "\nContent: " + course.getContent());
                counter = 1;
            }

        }
            if(counter !=1)
            {
                 //to make sure that it did not enter the first if statement since it loops on all the available courses
                {System.out.println("Course is not found.");}
            }

    }





}
