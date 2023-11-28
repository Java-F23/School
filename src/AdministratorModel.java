import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

public class AdministratorModel {

    private static ArrayList<CourseModel> courses;
    private  ArrayList<InstructorModel> instructors;
    private static Map<String, List<CourseModel>> coursesByDepartment;
    private Map<String, List<StudentModel>> studentEnrollment;
    private Map<String, List<Assignment>> classAssignments;
    private Map<String, List<String>> calendarEvents;

    public AdministratorModel() {
        this.courses = new ArrayList<>();
        this.instructors = new ArrayList<>();
        this.coursesByDepartment = new HashMap<>();
        this.studentEnrollment = new HashMap<>();
        this.classAssignments = new HashMap<>();
        this.calendarEvents = new HashMap<>();
        // Load courses from CSV file
        System.out.println("in admin");
        CSVHandler.loadCoursesFromCsv("courses.csv",courses);
        System.out.println(courses);
    }
    public  ArrayList<InstructorModel> getInstructors() {
        return instructors;
    }
    public static ArrayList<CourseModel> getCourses() {
        return courses;
    }
    // Add a new course
    public static void addNewCourse(CourseModel course) {
        try {
            // Validate the course data
            ExceptionHandling.validateCourse(course);
            if (courses == null) {
                courses = new ArrayList<>();
            }
          //   Check if the course already exists
            System.out.println(course);
            if (courses.contains(course)) {
                throw new ExceptionHandling.DuplicateCourseException(course.getCourseTitle());
            }
            // Add the course if validation passes
            courses.add(course);
            addToDepartmentMap(course);
            // Add the new course to the CSV file
            CSVHandler.writeCoursesToCsv(courses, "courses.csv");

            AdministratorView.displayCourseAddedMessage(course.getCourseTitle());
        } catch (ExceptionHandling.DuplicateCourseException e) {
            ExceptionHandling.handleDuplicateCourseException(e.getMessage());
        } catch (ExceptionHandling.InvalidDataException e) {
            ExceptionHandling.handleInvalidDataException(e.getMessage());
        }
    }

    // Edit existing course
    public void editExistingCourse(CourseModel oldCourse, CourseModel newCourse) {
        try {
            // Check if the old course exists
            if (!courses.contains(oldCourse)) {
                throw new ExceptionHandling.CourseNotFoundException("Old course not found.");
            }

            // Validate data for the new course
            ExceptionHandling.validateCourse(newCourse);

            // Remove the old course and add the new course
            courses.remove(oldCourse);
            courses.add(newCourse);

            // Update department map
            updateDepartmentMap(oldCourse, newCourse);
            CSVHandler.editCourseInCsv(oldCourse, newCourse, "courses.csv");
        } catch (ExceptionHandling.CourseNotFoundException e) {
            ExceptionHandling.handleCourseNotFoundException(e.getMessage());
        } catch (ExceptionHandling.InvalidDataException e) {
            ExceptionHandling.handleInvalidDataException(e.getMessage());
        }
    }
    public void removeCourse(CourseModel courseToRemove) {
        try {
            // Check if the course exists
            if (!courses.contains(courseToRemove)) {
                throw new ExceptionHandling.CourseNotFoundException("Course not found.");
            }

            // Validate data for the course (optional, depending on your requirements)
            ExceptionHandling.validateCourse(courseToRemove);

            // Remove the course if validation passes
            courses.remove(courseToRemove);

            // Remove the course from the CSV file
            CSVHandler.removeCourseFromCsv(courseToRemove, "courses.csv");

            System.out.println("Course removed successfully.");
        } catch (ExceptionHandling.CourseNotFoundException e) {
            ExceptionHandling.handleCourseNotFoundException(e.getMessage());
        } catch (ExceptionHandling.InvalidDataException e) {
            ExceptionHandling.handleInvalidDataException(e.getMessage());
        }
    }

    // Add or remove an instructor
    public void addInstructor(InstructorModel instructor) {
        try {
            // Check if the instructor already exists
            if (instructors.contains(instructor)) {
                throw new ExceptionHandling.DuplicateInstructorException("Instructor already exists.");
            }

            // Validate data for the new instructor
            ExceptionHandling.validateInstructor(instructor);

            // Add the instructor if validation passes
            instructors.add(instructor);
            CSVHandler.writeInstructorsToCsv(instructor, "instructors.csv");

            System.out.println("Instructor added successfully.");
        } catch (ExceptionHandling.DuplicateInstructorException e) {
            ExceptionHandling.handleDuplicateInstructorException(e.getMessage());
        } catch (ExceptionHandling.InvalidInstructorDataException e) {
            ExceptionHandling.handleInvalidInstructorDataException(e.getMessage());
        }
    }

    public void removeInstructor(InstructorModel instructor) {
        try {
            // Check if the instructor exists
            if (!instructors.contains(instructor)) {
                throw new ExceptionHandling.InstructorNotFoundException("Instructor not found.");
            }

            // Validate data for the instructor (optional, depending on your requirements)
            ExceptionHandling.validateInstructor(instructor);

            // Remove the instructor if validation passes
            instructors.remove(instructor);
            //Remove it from the CSV file
            CSVHandler.removeInstructorFromCsv(instructor, "instructors.csv");
            System.out.println("Instructor removed successfully.");
        }catch (ExceptionHandling.InstructorNotFoundException e) {
            ExceptionHandling.handleInstructorNotFoundException(e.getMessage());
        }catch (ExceptionHandling.InvalidInstructorDataException e) {
            ExceptionHandling.handleInvalidInstructorDataException(e.getMessage());
        }
    }

    // Categorize courses by department
    private static void addToDepartmentMap(CourseModel course) {
        String department = course.getDepartment();
        if (coursesByDepartment == null)
        {
            coursesByDepartment = new HashMap<>();

        }
        coursesByDepartment.computeIfAbsent(department, k -> new ArrayList<>()).add(course);
        // Save courses to CSV by department
        CSVHandler.saveCoursesByDepartmentToCSV(coursesByDepartment, "courses_by_department.csv");
    }

    //For case editing a course
    private void updateDepartmentMap(CourseModel oldCourse, CourseModel newCourse) {
        String oldDepartment = oldCourse.getDepartment();
        String newDepartment = newCourse.getDepartment();

        coursesByDepartment.computeIfPresent(oldDepartment, (k, v) -> {
            v.remove(oldCourse);
            return v.isEmpty() ? null : v;
        });

        // Add this line to update the department map in the CSV file
        CSVHandler.updateDepartmentMapInCSV(coursesByDepartment, oldDepartment, oldCourse, newDepartment, newCourse, "courses_by_department.csv");
    }


    // Track and manage student enrollment
    public void enrollStudentInCourse(StudentModel student, CourseModel course) {
       try{
           String courseTitle = course.getCourseTitle();
        // Check if the student is already enrolled in the course
        List<StudentModel> enrolledStudents = studentEnrollment.computeIfAbsent(courseTitle, k -> new ArrayList<>());
        if (!enrolledStudents.contains(student)) {
            ExceptionHandling.validateStudent(student);
            enrolledStudents.add(student);
            CSVHandler.writeStudentsToCsv(enrolledStudents,course.getCourseTitle(), "EnrolledStudentsInCourse.csv");


        } else {
            throw new ExceptionHandling.StudentAlreadyEnrolledException("Student " + student.getName() + " is already enrolled in the course " + courseTitle);
        }
        if (!courses.contains(course)) {
               throw new ExceptionHandling.CourseNotFoundException("Course not found.");
        }

       } catch (ExceptionHandling.StudentAlreadyEnrolledException e) {
           ExceptionHandling.handleStudentAlreadyEnrolledException(e.getMessage());
       }
         catch(ExceptionHandling.InvalidStudentDataException e) {
            ExceptionHandling.handleStudentNotEnrolledException(e.getMessage());
       } catch (ExceptionHandling.CourseNotFoundException e) {
           ExceptionHandling.handleCourseNotFoundException(e.getMessage());
       }
    }


    public void removeStudentFromCourse(StudentModel student, CourseModel course) {
        String courseTitle = course.getCourseTitle();
        // Check if the student is enrolled in the course
        studentEnrollment.computeIfPresent(courseTitle, (k, v) -> {
            try {
                //Validate the administrator input
                ExceptionHandling.validateStudent(student);
                if (v.remove(student)) {
                    return v.isEmpty() ? null : v;
                } else {
                    // Student is not enrolled, throw an unchecked exception
                    throw new ExceptionHandling.StudentNotEnrolledException("Student " + student.getName() + " is not enrolled in the course " + courseTitle);
                }
            } catch (ExceptionHandling.StudentNotEnrolledException e) {
                // Handle the exception using the custom handling method
                ExceptionHandling.handleStudentNotEnrolledException(e.getMessage());
                return v; // Return the unmodified list to keep the map consistent
            }catch(ExceptionHandling.InvalidStudentDataException e) {
                ExceptionHandling.handleStudentNotEnrolledException(e.getMessage());
                return v; // Return the unmodified list to keep the map consistent
            }
        });
    }

    public int countStudentsInCourse(CourseModel course) {
        String courseTitle = course.getCourseTitle();
        return studentEnrollment.getOrDefault(courseTitle, new ArrayList<>()).size();
    }

    public List<CourseModel> getCoursesEnrolledForStudent(Student student) {
        List<CourseModel> enrolledCourses = new ArrayList<>();
        String studentName = student.getName();

        for (Map.Entry<String, List<StudentModel>> entry : studentEnrollment.entrySet()) {
            if (entry.getValue().stream().anyMatch(s -> s.getName().equals(studentName))) {
                enrolledCourses.add(getCourseByTitle(entry.getKey()));
            }
        }
        return enrolledCourses;
    }

    public static CourseModel getCourseByTitle(String courseTitle) {
        if (courses==null)
        {
            courses = new ArrayList<>();
        }
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
        try {
            ExceptionHandling.validateEventData(date, event);

            // Check if the date already has events
            List<String> events = calendarEvents.computeIfAbsent(date, k -> new ArrayList<>());
            if (!events.contains(event)) {
                events.add(event);
                CSVHandler.saveEventsToCsv(calendarEvents);
                System.out.println("Event '" + event + "' added for the date '" + date + "'.");
            } else {
                throw new ExceptionHandling.InvalidEventDataException("Event '" + event + "' is already added for the date '" + date + "'.");
            }
        } catch (ExceptionHandling.InvalidEventDataException e) {
            System.err.println("Error adding event: " + e.getMessage());
        }
    }

    public void removeEventFromCalendar(String date, String event) {
        try {
            ExceptionHandling.validateEventData(date, event);

            // Check if the date has events
            calendarEvents.computeIfPresent(date, (k, v) -> {
                try {
                    if (v.contains(event)) {
                        v.remove(event);
                        System.out.println("Event '" + event + "' removed for the date '" + date + "'.");
                        CSVHandler.saveEventsToCsv(calendarEvents); // Update CSV after removing event
                        return v.isEmpty() ? null : v;
                    } else {
                        // Event not found, throw an unchecked exception
                        throw new ExceptionHandling.InvalidEventDataException("Event '" + event + "' not found for the date '" + date + "'.");
                    }
                } catch (ExceptionHandling.InvalidEventDataException e) {
                    // Handle the exception using the custom handling method
                    ExceptionHandling.handleInvalidEventDataException(e.getMessage());
                    return v; // Return the unmodified list to keep the map consistent
                }
            });
            // If the date does not have any events, remove it from the map
            if (calendarEvents.get(date) != null && calendarEvents.get(date).isEmpty()) {
                calendarEvents.remove(date);
            }
        } catch (ExceptionHandling.InvalidEventDataException e) {
            System.err.println("Error removing event: " + e.getMessage());
        }
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
