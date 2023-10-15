import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Thread;


public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name");
        String userName = scanner.next();

        Administrator administrator = new Administrator(userName);
        Student student = new Student(userName, null);
        administrator.setStudents(student);
        System.out.println("Are you an administrator or a student?");

        String userType = scanner.next();
        do {
                if (userType.equals("administrator")) {
                    System.out.println("Choose one of the following facilities:");
                    System.out.println("1. Add a new course");
                    System.out.println("2. Edit an existing course");
                    System.out.println("3. Add or remove an instructor");
                    System.out.println("4. Categorize courses by department");
                    System.out.println("5. Track and manage student enrollment");
                    System.out.println("6. Track and manage class assignment");
                    System.out.println("7. Calculate students' GPA");

                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Enter the title of the course:");
                            String title = scanner.next();
                            System.out.println("Enter the subject of the course:");
                            String subject = scanner.next();
                            System.out.println("Enter the instructor of the course:");
                            String instructor = scanner.next();
                            System.out.println("Enter the level of the course:");
                            String level = scanner.next();
                            System.out.println("Enter the content of the course:");
                            String content = scanner.next();
                            System.out.println("Enter the department of the course:");
                            String department = scanner.next();
                            System.out.println("Enter the day of the course:");
                            String day = scanner.next();
                            System.out.println("Enter the time of the course in this format (HH:mm):");
                            String timeString = scanner.next();
                            LocalTime time = LocalTime.parse(timeString);
                            administrator.addCourse(title, subject, instructor,level, content,department,day, time);

                            break;
                        case 2:
                            System.out.println("Enter the title of the course to be edited:");
                            String newTitle = scanner.next();
                            System.out.println("Enter the new subject of the course:");
                            String newSubject = scanner.next();
                            System.out.println("Enter the new instructor of the course:");
                            String newInstructor = scanner.next();
                            System.out.println("Enter the new level of the course:");
                            String newLevel = scanner.next();
                            System.out.println("Enter the new content of the course:");
                            String newContent = scanner.next();
                            System.out.println("Enter the new department of the course:");
                            String newDepartment = scanner.next();

                            administrator.editCourse(newTitle, newSubject, newInstructor,newLevel,newContent,newDepartment);

                            break;
                        case 3:
                            System.out.println("Enter 'add' to add an instructor or 'remove' to remove an instructor:");
                            String action = scanner.next();
                            if (action.equals("add")) {
                                System.out.println("Enter the name of the instructor:");
                                String name = scanner.next();
                                System.out.println("Enter the qualification of the instructor:");
                                String qualification = scanner.next();
                                administrator.addInstructor(name, qualification);
                            } else if (action.equals("remove")) {
                                System.out.println("Enter the name of the instructor to be removed:");
                                String name = scanner.next();
                                administrator.removeInstructor(name);
                            } else {
                                System.out.println("Invalid action");
                            }
                            break;
                        case 4:
                            // System.out.println("Enter the name of the department:");
                            //String departmentName = scanner.next();
                            // administrator.printCoursesInDepartment(administrator.getCourses(),departmentName);
                            System.out.println("Here is the result of the categorization:");
                            System.out.println(administrator.arrangeCoursesByDepartment(administrator.getCourses()));
                            break;
                        case 5:
                            System.out.println("Choose the option number:");
                            System.out.println("1. Print number of students in each course");
                            System.out.println("2. Print the enrolled courses for specific student");
                            System.out.println("3. Add student to a course");
                            System.out.println("4. Remove student from a course");
                            int option = scanner.nextInt();
                            switch(option){
                                //print number of students in each counter
                                case 1:
                                    System.out.println(administrator.countStudentsPerCourse());
                                    break;
                                //print the enrolled courses for specific student
                                case 2:
                                    System.out.println("Enter the student name:");
                                    String name = scanner.next();
                                    administrator. printMyCourses( name);
                                   break;
                                //add student to a course
                                case 3:
                                    System.out.println("Enter the title of the course:");
                                    String courseTitle = scanner.next();
                                    System.out.println("Enter the student's name ");
                                     student.setName(scanner.next());
                                    administrator.addCourseToStudent(student,courseTitle);
                                    break;
                                //remove student from a course
                                case 4:
                                    System.out.println("Enter the title of the course:");
                                    courseTitle = scanner.next();
                                    System.out.println("Enter the student's name ");
                                    student.setName(scanner.next());
                                    administrator.removeCourseFromStudent(student,courseTitle);
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                            }
                              break;
                        case 6:
                            System.out.println("Enter the course title");
                            String courseTitle = scanner.next();
                            int counter=0;
                            for ( Course course : administrator.getCourses())
                            {
                                if (course.getTitle().equals(courseTitle))
                                {

                                    System.out.println("Choose the option number:");
                                    System.out.println("1. Add an assignment");
                                    System.out.println("2. Remove an assignment");
                                    System.out.println("3. Display assignment");
                                    option = scanner.nextInt();
                                    administrator.manageAssignments(courseTitle, option);
                                    counter=1;  //to know that the course is available
                                }
                            }
                            if (counter==0)
                            {
                                System.out.println("Course not found");
                            }

                            break;
                        case 7:
                            administrator.calculateGPA(student);
                            break;

                        default:
                            System.out.println("Invalid choice");
                    }


                    // Perform the selected operation
                } else if (userType.equals("student")) {
                    // Perform student operations
                    System.out.println("Choose one of the following facilities:");
                    System.out.println("1. Browse and view a list of available courses");
                    System.out.println("2. Search for a course based on specific criteria");
                    System.out.println("3. View detailed information about a specific course");
                    System.out.println("4. View the schedule of classes for a specific course");
                    System.out.println("5. Mark courses as favourites");
                    System.out.println("6. Print the favourite courses list");
                    System.out.println("7. Enroll in a class");
                    System.out.println("8. Print my courses");
                    System.out.println("9. View your class grades");
                    System.out.println("10. View school academic calender");
                    System.out.println("11. Access historical class schedules and academic performance records");

                    int choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Here is the list of the available courses: /n");
                            student.viewAvailableCourses(administrator);

                            break;
                        case 2:
                            Scanner scanner2 = new Scanner(System.in);
                            System.out.println("Enter the parameter to search for (title, subject, instructor, department, level, or content):");
                            String parameter = scanner2.next();
                            String title = null;
                            String subject = null;
                            String instructor = null;
                            String department = null;
                            String level = null;
                            String content = null;
                            if (parameter.equals("title")) {
                                System.out.println("Enter the title to search for:");
                                title = scanner2.next();
                            } else if (parameter.equals("subject")) {
                                System.out.println("Enter the subject to search for:");
                                subject = scanner2.next();
                            } else if (parameter.equals("instructor")) {
                                System.out.println("Enter the instructor to search for:");
                                instructor = scanner2.next();
                            } else if (parameter.equals("department")) {
                                System.out.println("Enter the department to search for:");
                                department = scanner2.next();
                            } else if (parameter.equals("level")) {
                                System.out.println("Enter the level to search for:");
                                level = scanner2.next();
                            } else if (parameter.equals("content")) {
                                System.out.println("Enter the content to search for:");
                                content = scanner2.nextLine();
                            }
                            student.searchAndPrintCourses(administrator, title, subject, instructor, department, level, content);
                            break;
                        case 3:
                            System.out.println("Enter the course title: ");
                            String courseTitle = scanner.next();
                            student.getCourseDetails(administrator,courseTitle);
                            break;
                        case 4:
                            System.out.print("Enter the course title: ");
                            courseTitle = scanner.next();
                            student.printCourseSchedule(administrator,courseTitle);
                            break;

                        case 5:
                            System.out.print("Enter the title of the favorite course: ");
                            courseTitle = scanner.next();
                            int favcourseCounter =0;
                            for ( Course course : administrator.getCourses())
                            {
                                if (course.getTitle().equals(courseTitle))
                                {

                                    student.addFavoriteCourse(courseTitle);
                                  favcourseCounter=1;  //to know that the course is available
                                }
                            }
                            if (favcourseCounter==0)
                            {
                                System.out.println("Course not found");
                            }

                            break;

                        case 6:
                             student.printFavoriteCourses();
                           break;
                        case 7:
                            System.out.print("Enter the name of the course: ");
                             courseTitle = scanner.next();
                            int enrolledcoursesCounter =0;
                            for ( Course course : administrator.getCourses())
                            {
                                if (course.getTitle().equals(courseTitle)) {

                                    student.enrollInCourse(courseTitle);
                                    enrolledcoursesCounter =1;
                                }
                            }
                            if (enrolledcoursesCounter==0)
                            {
                                System.out.println("Course is not found");
                            }

                            break;
                        case 8:
                            student.printMyCourses(administrator,userName);
                            break;
                        case 9:
                            student.viewGrades(administrator,userName);
                            break;
                        case 10:
                            student.Calendar();
                            break;
                        case 11:
                            student.printHistory(administrator,userName);
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }

                } else {
                    System.out.println("Invalid input. Enter the user type again");
                    userType = scanner.next();
                }


            System.out.println("Do you want to choose another option? (yes/no)");
        } while (scanner.next().equals("yes"));

    }
}
