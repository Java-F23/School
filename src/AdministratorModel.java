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
        try {
            // Check if the course already exists
            if (courses.contains(course)) {
                throw new ExceptionHandling.DuplicateCourseException(course.getCourseTitle());
            }

            // Validate the course data
            validateCourse(course);

            // Add the course if validation passes
            courses.add(course);
            addToDepartmentMap(course);
            // Add the new course to the CSV file
            CSVHandler.writeCoursesToCsv(courses, "courses.csv");
            System.out.println("Course added successfully.");
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
            validateCourse(newCourse);

            // Remove the old course and add the new course
            courses.remove(oldCourse);
            courses.add(newCourse);

            // Update department map
            updateDepartmentMap(oldCourse, newCourse);
            CSVHandler.editCourseInCsv(oldCourse, newCourse, "courses.csv");

            System.out.println("Course edited successfully.");
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
             validateCourse(courseToRemove);

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
            validateInstructor(instructor);

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
             validateInstructor(instructor);

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
    private void addToDepartmentMap(CourseModel course) {
        String department = course.getDepartment();
        coursesByDepartment.computeIfAbsent(department, k -> new ArrayList<>()).add(course);
        // Add this line to save courses to CSV by department
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
    public void enrollStudentInCourse(Student student, CourseModel course) {
       try{
           String courseTitle = course.getCourseTitle();
        // Check if the student is already enrolled in the course
        List<Student> enrolledStudents = studentEnrollment.computeIfAbsent(courseTitle, k -> new ArrayList<>());
        if (!enrolledStudents.contains(student)) {
            validateStudent(student);
            enrolledStudents.add(student);
        } else {
            throw new ExceptionHandling.StudentAlreadyEnrolledException("Student " + student.getName() + " is already enrolled in the course " + courseTitle);
        }
        } catch (ExceptionHandling.StudentAlreadyEnrolledException e) {
           ExceptionHandling.handleStudentAlreadyEnrolledException(e.getMessage());
        }
         catch(ExceptionHandling.InvalidStudentDataException e) {
            ExceptionHandling.handleStudentNotEnrolledException(e.getMessage());
        }
    }


    public void removeStudentFromCourse(Student student, CourseModel course) {
        String courseTitle = course.getCourseTitle();
        // Check if the student is enrolled in the course
        studentEnrollment.computeIfPresent(courseTitle, (k, v) -> {
            try {
                //Validate the administrator input
                validateStudent(student);
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

    // Validation method
    private void validateCourse(CourseModel course) throws ExceptionHandling.InvalidDataException {
        // Implement validation logic for course data
        // Throw InvalidDataException if the data is invalid
        if (course.getCourseTitle() == null || course.getCourseTitle().isEmpty()) {
            throw new ExceptionHandling.InvalidDataException("Course title cannot be empty");
        }
    }
    private void validateInstructor(InstructorModel instructor) throws ExceptionHandling.InvalidInstructorDataException {
        // Implement validation logic for instructor data
        // Throw InvalidInstructorDataException if the data is invalid

        if (instructor.getName() == null || instructor.getName().isEmpty()) {
            throw new ExceptionHandling.InvalidInstructorDataException("Instructor name cannot be empty");
        }
    }

    private void validateStudent(Student student) throws ExceptionHandling.InvalidStudentDataException {
        if (student == null || student.getName() == null || student.getName().isEmpty()) {
            throw new ExceptionHandling.InvalidStudentDataException("Invalid student data");
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
