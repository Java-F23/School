import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your name");
        String userName = scanner.next();

        Administrator administrator = new Administrator(userName);
        Student student = new Student(userName);
        System.out.println("Are you an administrator or a student?");

        String userType = scanner.next();
        do {
                if (userType.equals("administrator")) {
                    System.out.println("Choose one of the following facilities:");
                    System.out.println("1. Add a new course");
                    System.out.println("2. Edit an existing course");
                    System.out.println("3. Add or remove an instructor");
                    System.out.println("4. Categorize courses by department");
                    //   System.out.println("5. Track student enrollment");
                    System.out.println("6. Calculate students' GPA");


                    // Perform the selected operation
                } else if (userType.equals("student")) {
                    // Perform student operations
                    System.out.println("Choose one of the following facilities:");
                    System.out.println("1. Browse and view a list of available courses");
                    System.out.println("2. Search for a course based on specific criteria");
                    System.out.println("3. View detailed information about a specific course");
                    System.out.println("4. View the schedule of classes for a specific course");
                    System.out.println("5. Mark courses as favourites");
                    System.out.println("6. Enroll in a class");
                    System.out.println("7. View your class grades");
                    System.out.println("7. View school academic calender");
                    System.out.println("8. Access historical class schedules and academic performance records");
                } else {
                    System.out.println("Invalid input. Enter the user type again");
                    userType = scanner.next();
                }

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
                administrator.addCourse(title, subject, instructor,level, content,department);

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
          //  case 5:
          //      break;
            case 6:
                administrator.calculateGPA(student);
                break;

            default:
                System.out.println("Invalid choice");
        }

            System.out.println("Do you want to choose another option? (yes/no)");
        } while (scanner.next().equals("yes"));

    }
}
