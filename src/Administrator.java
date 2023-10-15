import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.time.LocalTime;

public class Administrator {

    private ArrayList<Course> courses;
    private ArrayList<Faculty> instructors;
    private ArrayList<String> departments;
    private ArrayList<Student> students;
    private String name;


    public Administrator(String name) {
        this.name = name;
        courses = new ArrayList<Course>();
        Course course1 = new Course("IntroToJava","Java","Ashraf", "undergrad","Programming language","CS","UW",LocalTime.of(10,30));
        course1.setAssignments("Ass1");
        course1.setAssignments("Ass2");
        courses.add(course1);
        courses.add(new Course("C++Fundamentals","C++","Ahmed", "undergrad","Programming language","CS","MR",LocalTime.of(2,30)));
        students = new ArrayList<Student>();
        Student student1 = new Student("Toqa","IntroToJava");
        student1.setTotalGradePoints(190);//190 is total grades of the assignments for this student
        student1.setHistory("Python","A");
        student1.setHistory("Math01","A-");

        students.add(student1);
        instructors = new ArrayList<Faculty>();
        departments = new ArrayList<String>();

    }
    public void setStudents(Student student) {
        students.add(student);
    }
    public ArrayList<Student> getStudents() {
        return students;
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
    public void addCourse( String title, String subject, String instructor, String level, String content, String department, String day, LocalTime time) {
        Scanner scanner = new Scanner(System.in);

        if (!departments.contains(department)) {  //check if it is a new department
            departments.add(department);
        }

        Course newCourse = new Course(title,subject,instructor,level,content,department,day,time);
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


    public HashMap<Course, Integer> countStudentsPerCourse() {
        HashMap<Course, Integer> studentsPerCourse = new HashMap<>();
        for (Course course : courses) {
            int count = 0;
            for ( Student student : students) {
                if (student.getMyCourses().contains(course.getTitle())) {
                    //System.out.println(student.getName());
                    //System.out.println(student.getMyCourses());
                    count++;
                }
            }
            studentsPerCourse.put(course, count);
        }
        return studentsPerCourse;
    }

    public Double  calculateGPA (Student student)
    {

            double gpa = (student.getTotalGradePoints()/student.getTotalCredits());

        return gpa;
    }

    public void addCourseToStudent(Student student, String courseName) {
        for (Course course : courses) {
            if (course.getTitle().equals(courseName)) {
                if (student.getMyCourses().equals(courseName)) {
                    System.out.println(student.getName() + " is already enrolled in " + courseName);
                } else {
                    if (student.getMyCourses().get(0)==null)
                    {
                        student.setMyCourses(0,courseName);  //to overwrite null
                    }
                    else {
                        int nextIndex = student.getMyCourses().size();
                        student.setMyCourses(nextIndex,courseName);
                    }
                    System.out.println(courseName + " has been added to " + student.getName() + "'s courses.");
                }
            }
        }
    }
    public void printMyCourses( String Studentname) {
        for ( Student student : students)
        {
            if (student.getName().equals(Studentname))
            {        System.out.println(student.getMyCourses());

            }
            else{
                System.out.println("Enter valid student name");
            }
        }
    }
    public void removeCourseFromStudent(Student student, String courseName) {
        int index = student.getMyCourses().indexOf(courseName);
        if (index >= 0) {
            student.getMyCourses().remove(index);
            System.out.println(courseName + " has been removed from " + student.getName() + "'s courses.");
        } else {
            System.out.println(student.getName() + " is not enrolled in " + courseName);
        }
    }



    public void manageAssignments(String courseTitle, int option) {

            int index = findCourse(courseTitle);
            Course course = courses.get(index);
            if (course == null) {
                System.out.println("Course not found.");
            }
            else {
                switch (option)
                {
            //add assignment for this course
            case 1:
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter the name of the new assignment: ");
                    String newAss= scanner.next();
                for (String assignment : course.getAssignments()) {
                    if (assignment.equals(newAss)) {
                        System.out.println("Assignment already exist!");
                        break;
                    }
                    else
                    {
                        course.setAssignments(newAss);
                        System.out.printf("%s is added to %s.\n", newAss, course.getTitle());
                        break;
                    }
                }
                break;
            //remove assignment from this course
            case 2:
                Scanner scanner2 = new Scanner(System.in);
                System.out.print("Enter the name of the assignment to remove: ");
                    String removAss=scanner2.next();
                for (String assignment : course.getAssignments()) {
                    if (assignment.equals(removAss)) {
                        course.getAssignments().remove(removAss);
                        System.out.printf("%s is removed \n", removAss);
                        break;
                    }
                    else
                    {
                        System.out.println("Assignment does not exist!");
                        break;
                    }
                }
                break;
            //Display the assignments for this course
            case 3:
                Scanner scanner3 = new Scanner(System.in);

                for (Course c : courses) {
                    if (c.getTitle().equals(courseTitle) )
                    {
                        System.out.println("Assignments for " + courseTitle + ":");
                        System.out.println(c.getAssignments());
                    }

                }
                break;

            default:
                System.out.println("Invalid choice.");
                }
            }
    }





}
