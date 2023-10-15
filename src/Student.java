import java.util.ArrayList;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


public class Student {
    private String name;
    private String course;
    private String enrolledClasses;
    private double totalCredits;
    private double totalGradePoints;

    private ArrayList<String> favoriteCourses;

    private ArrayList<String> MyCourses;  //includes courses that student enrolled in

    //events and dates are related to the calendar
    private ArrayList<String> events;
    private ArrayList<String> dates;
    private ArrayList<String> history;  //includes the courses that student took in previous years and his grades



    public Student(String name, String course) {
        this.name = name;
        this.course = course;
        favoriteCourses = new ArrayList<>();
        MyCourses = new ArrayList<>();
        MyCourses.add(course);
        events = new ArrayList<>();
        dates = new ArrayList<>();
        history = new ArrayList<>();
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

    public ArrayList<String> getMyCourses() {
        return MyCourses;

    }


    public void setHistory(String course, String grade) {
        history.add(course + ": " + grade);
    }
    public void setMyCourses(int index,String myCourse) {
        //  MyCourses.set(index,myCourses);

        if (MyCourses.size() <= index) {
            MyCourses.add(myCourse);
        } else {
            MyCourses.set(index, myCourse);
        }

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

    public void printCourseSchedule(Administrator administrator,String courseTitle) {
        for (Course course : administrator.getCourses()) {
            if (course.getTitle().equals(courseTitle)) {
                System.out.println("Course Schedule:");
                System.out.println("Day: " + course.getDay());
                System.out.println("Time: " + course.getTime().format(DateTimeFormatter.ofPattern("hh:mm")));
                return;  //to end the function if the course is found
            }
        }
        System.out.println("Course not found.");
    }

    public void addFavoriteCourse(String title) {
        favoriteCourses.add(title);
        System.out.println("The course is added to the fav list successfully!");
    }

    public void printFavoriteCourses() {
        if (favoriteCourses.isEmpty()) {
            System.out.println("No favorite courses.");
        } else {
            System.out.println("Favorite courses:");
            for (String favoriteCourse : favoriteCourses) {
                System.out.println(favoriteCourse);
            }
        }
    }
    public void enrollInCourse(String title) {
        MyCourses.add(title);
        System.out.println("The course is added to the fav list successfully!");
    }

    public void printMyCourses() {
        if (MyCourses.isEmpty()) {
            System.out.println("No enrolled courses.");
        } else {
            System.out.println("Enrolled courses:");
            for (String myCourses : MyCourses) {
                System.out.println(myCourses);
            }
        }
    }
    public void viewGrades(Administrator administrator, String studentName) {
        Student student = null;
        for (Student s : administrator.getStudents()) {
            if (s.getName().equals(studentName)) {
                student = s;
                break;
            }
        }
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Grades and academic progress for enrolled courses:");
            ArrayList<String> assignments = new ArrayList<>();
            for (String mycourse : student.getMyCourses()) {
                for (Course availableCourse : administrator.getCourses()) {
                    if (availableCourse.getTitle().equals(mycourse)) {
                        assignments = availableCourse.getAssignments();
                    }
                }
                if (assignments.isEmpty()) {
                    System.out.println(mycourse + ": No assignments found.");
                } else {
                    double totalScore = student.getTotalGradePoints();
                    double maxScore = assignments.size() * 100;
                    double progress = totalScore / maxScore * 100;
                    System.out.printf("%s: Progress: %.2f%%\n", mycourse, progress);
                }
            }
        }
    }
    public void Calendar() {
        // Adding important dates to the calendar
        events.add("First day of classes");
        dates.add("1/9/2023");

        events.add("Armed Forces Day");
        dates.add("6/10/2023");

        events.add("Thanksgiving");
        dates.add("23/11/2023");

        events.add("Final exam starting");
        dates.add("15/12/2023");
        System.out.println("Event\t\t\t\t\t\tDate");
        for (int i = 0; i < events.size(); i++) {
            System.out.println(String.format("%-25s %10s", events.get(i), dates.get(i)));


        }
    }


    public void printHistory(Administrator administrator, String studentName) {
        Student student = null;
        for (Student s : administrator.getStudents()) {
            if (s.getName().equals(studentName)) {
                student = s;
                break;
            }
        }
        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Course History:");
            System.out.println("Course\t\t\tGrade");
            for (String course : student.history) {
                String[] parts = course.split(": ");
                System.out.println((parts[0] + "\t\t\t" + parts[1]));
            }
        }

    }

}
