import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class Administrator {

    private ArrayList<Course> courses;
    private ArrayList<Faculty> instructors;
    private ArrayList<String> departments;
    private String name;
    public Administrator(String name) {
        this.name = name;
        courses = new ArrayList<Course>();
        courses.add(new Course("Intro to Java","Java","Ashraf", "undergrad","Programming language","CS"));
        courses.add(new Course("C++ Fundamentals","C++","Ahmed", "undergrad","Programming language","CS"));
        instructors = new ArrayList<Faculty>();
        departments = new ArrayList<String>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }
    public void addCourse( String title, String subject, String instructor, String level, String content, String department) {
        Scanner scanner = new Scanner(System.in);

        if (!departments.contains(department)) {  //check if it is a new department
            departments.add(department);
        }

        Course newCourse = new Course(title,subject,instructor,level,content,department);
        courses.add(newCourse);
        System.out.println("New course added successfully!");
        for (Course course : courses)
        {
            System.out.println(course.getTitle());
        }


    }

    public void editCourse(String title, String subject, String instructor,String level, String content, String department) {
        int index = findCourse(title);
        Scanner scanner = new Scanner(System.in);


        if (!departments.contains(department)) {  //check if it is a new department
            departments.add(department);
        }

        if (index != -1) {
            Course course = courses.get(index);
            course.setTitle(title);
            course.setSubject(subject);
            course.setInstructor(instructor);
            course.setLevel(level);
            course.setContent(content);
            course.setDepartment(department);

            System.out.println("Course updated successfully!");
        }
        else {
            System.out.println("Course not found!");
        }
    }


    //findCourse is a method to get the number of the course that the administrator want to edit in the course list
    public int findCourse(String title) {
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getTitle().equals(title)) {
                return i;
            }
        }
        return -1;
    }


    public void addInstructor(String name, String qualification) {
        Scanner scanner = new Scanner(System.in);

        instructors.add(new Faculty(name,qualification));
        System.out.println("New instructor added successfully!");
    }

    public void removeInstructor(String name) {
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getName().equals(name)) {
                instructors.remove(i);
                break;
            }
        }
        System.out.println("Instructor removed successfully!");

    }

    //Categorize courses into different departments
    public void printCoursesInDepartment(ArrayList<Course>courses, String department) {
        for (Course course : courses) //loop on the list of courses
        {
            if (course.getDepartment().equals(department)) {
                System.out.println(course.getTitle());
            }
        }
    }

    public HashMap<String, ArrayList<Course>> arrangeCoursesByDepartment(ArrayList<Course> courses) {
            HashMap<String, ArrayList<Course>> coursesByDepartment = new HashMap<String, ArrayList<Course>>();

            for (Course course : courses) {
                String department = course.getDepartment();
                if (!coursesByDepartment.containsKey(department)) {
                    coursesByDepartment.put(department, new ArrayList<Course>());
                }
                coursesByDepartment.get(department).add(course);
            }

            return coursesByDepartment;
    }





    /* public void trackstudents(ArrayList<Student>students)
    {

    }
*/
    public Double  calculateGPA (Student student)
    {

            double gpa = (student.getTotalGradePoints()/student.getTotalCredits());

        return gpa;
    }


    public ArrayList<Course> searchCourses(String title, String subject, String instructor, String department, String level, String content) {
        ArrayList<Course> availableCourses = getCourses();
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
        return matchedCourses;
    }


}
