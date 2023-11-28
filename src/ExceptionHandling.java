import javax.swing.*;

public class ExceptionHandling {

    public static class DuplicateCourseException extends Exception {
        public DuplicateCourseException(String message) {
            super(message);
        }
    }
    public static class InvalidDataException extends Exception {
        public InvalidDataException(String message) {
            super(message);
        }
    }

    public static class CourseNotFoundException extends Exception {
        public CourseNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateInstructorException extends Exception {
        public DuplicateInstructorException(String message) {
            super(message);
        }
    }

    public static class InvalidInstructorDataException extends Exception {
        public InvalidInstructorDataException(String message) {
            super(message);
        }
    }
    public static class InvalidStudentDataException extends Exception {
        public InvalidStudentDataException(String message) {
            super(message);
        }
    }


    public static class InstructorNotFoundException extends Exception {
        public InstructorNotFoundException(String message) {
            super(message);
        }
    }

    public static class StudentAlreadyEnrolledException extends Exception {
        public StudentAlreadyEnrolledException(String message) {
            super(message);
        }
    }
    public static class StudentNotEnrolledException extends Exception {
        public StudentNotEnrolledException(String message) {
            super(message);
        }
    }
    public static class InvalidEventDataException extends Exception {
        public InvalidEventDataException(String message) {
            super(message);
        }
    }
    public static void handleDuplicateCourseException(String courseTitle) {
        String errorMessage = "Error: Course with title '" + courseTitle + "' already exists.";
        JOptionPane.showMessageDialog(null, errorMessage, "Duplicate Course Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void handleInvalidDataException(String message) {
        System.err.println("Invalid data: " + message);
    }

    public static void handleCourseNotFoundException(String message) {
        JOptionPane.showMessageDialog(null, message, "Course not found Error", JOptionPane.ERROR_MESSAGE);    }

    public static void handleDuplicateInstructorException(String message) {
        JOptionPane.showMessageDialog(null, message, "Duplicate Instructor Error", JOptionPane.ERROR_MESSAGE);}

    public static void handleInvalidInstructorDataException(String message) {
        JOptionPane.showMessageDialog(null, message, "Invalid instructor data", JOptionPane.ERROR_MESSAGE);    }

    public static void handleInstructorNotFoundException(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE); }


    public static void handleStudentAlreadyEnrolledException(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE); }

    public static void handleStudentNotEnrolledException(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE); }

    public static void handleNoFavoriteCourses() {
        JOptionPane.showMessageDialog(null, "No favorite courses available.", "Error", JOptionPane.ERROR_MESSAGE); }


    public static void handleInvalidEventDataException(String errorMessage) {

        JOptionPane.showMessageDialog(null, "Invalid Event Data Exception:", "Error", JOptionPane.ERROR_MESSAGE); }

    public static void handleEnrollmentError() {

        JOptionPane.showMessageDialog(null, "Error enrolling in course: Course is already enrolled or not found.", "Error", JOptionPane.ERROR_MESSAGE); }

    // Validation method
    public static void validateCourse(CourseModel course) throws ExceptionHandling.InvalidDataException {
        // Implement validation logic for course data
        // Throw InvalidDataException if the data is invalid

        StringBuilder errorMessage = new StringBuilder("Error: Invalid course data.\n");

        if (course.getCourseTitle() == null || course.getCourseTitle().isEmpty()) {
            errorMessage.append(" - Course title cannot be empty\n");
        }

        // Add validation checks for other attributes here...
        if (course.getCourseSubject() == null || course.getCourseSubject().isEmpty()) {
            errorMessage.append(" - Course subject cannot be empty\n");
        }

        if (course.getDepartment() == null || course.getDepartment().isEmpty()) {
            errorMessage.append(" - Department cannot be empty\n");
        }

        // Check if the instructor is set
        if (course.getInstructor() == null) {
            errorMessage.append(" - Instructor cannot be null\n");
        }

        // Add more checks for other attributes and their data types...

        if (errorMessage.length() > "Error: Invalid course data.\n".length()) {
            // Display the error message using JOptionPane
            JOptionPane.showMessageDialog(null, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            throw new ExceptionHandling.InvalidDataException(errorMessage.toString());
        }
    }

    public static void validateInstructor(InstructorModel instructor) throws ExceptionHandling.InvalidInstructorDataException {
        // Implement validation logic for instructor data
        // Throw InvalidInstructorDataException if the data is invalid

        StringBuilder errorMessage = new StringBuilder("Error: Invalid instructor data.\n");

        if (instructor.getName() == null || instructor.getName().isEmpty()) {
            errorMessage.append(" - Instructor name cannot be empty\n");
        }

        // Add additional checks for other attributes if needed

        if (errorMessage.length() > "Error: Invalid instructor data.\n".length()) {
            // Display the error message using JOptionPane
            JOptionPane.showMessageDialog(null, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            throw new ExceptionHandling.InvalidInstructorDataException(errorMessage.toString());
        }
    }

    public static void validateStudent(String student) throws ExceptionHandling.InvalidStudentDataException {
        // Implement validation logic for student data
        // Throw InvalidStudentDataException if the data is invalid

        StringBuilder errorMessage = new StringBuilder("Error: Invalid student data.\n");

        if (student == null || student.isEmpty()) {
            errorMessage.append(" - Student name cannot be empty\n");
        }

        // Add additional checks for other attributes if needed

        if (errorMessage.length() > "Error: Invalid student data.\n".length()) {
            // Display the error message using JOptionPane
            JOptionPane.showMessageDialog(null, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            throw new ExceptionHandling.InvalidStudentDataException(errorMessage.toString());
        }
    }

    // Validation method for event data
    public static void validateEventData(String date, String event) throws ExceptionHandling.InvalidEventDataException {
        // Implement validation logic for date and event
        // Throw InvalidEventDataException if the data is invalid

        StringBuilder errorMessage = new StringBuilder("Error: Invalid event data.\n");

        if (date == null || date.isEmpty()) {
            errorMessage.append(" - Date cannot be empty\n");
        }

        if (event == null || event.isEmpty()) {
            errorMessage.append(" - Event cannot be empty\n");
        }

        // Add additional checks for date and event if needed

        if (errorMessage.length() > "Error: Invalid event data.\n".length()) {
            // Display the error message using JOptionPane
            JOptionPane.showMessageDialog(null, errorMessage.toString(), "Validation Error", JOptionPane.ERROR_MESSAGE);
            throw new ExceptionHandling.InvalidEventDataException(errorMessage.toString());
        }
    }
}
