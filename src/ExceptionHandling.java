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

    public static void handleDuplicateCourseException(String courseTitle) {
        System.err.println("Error: Course with title '" + courseTitle + "' already exists.");
    }

    public static void handleInvalidDataException(String message) {
        System.err.println("Invalid data: " + message);
    }

    public static void handleCourseNotFoundException(String message) {
        System.err.println("Error: " + message);
        // You can add additional logic for handling this exception if needed
    }

    public static void handleDuplicateInstructorException(String message) {
        System.err.println("Error: " + message);
        // You can add additional logic for handling this exception if needed
    }

    public static void handleInvalidInstructorDataException(String message) {
        System.err.println("Invalid instructor data: " + message);
        // You can add additional logic for handling this exception if needed
    }
    public static void handleInstructorNotFoundException(String message) {
        System.err.println("Error: " + message);
        // You can add additional logic for handling this exception if needed
    }

    public static void handleStudentAlreadyEnrolledException(String message) {
        // Custom handling for StudentAlreadyEnrolledException
        System.err.println("Error: " + message);
        System.err.println("Student is already enrolled in this course.");
    }
    public static void handleStudentNotEnrolledException(String message) {
        // Custom handling for StudentNotEnrolledException
        System.err.println("Error: " + message);
        System.err.println("Student is not enrolled in this course.");

    }
}
