import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class CSVHandler {

    public static void writeCoursesToCsv(List<CourseModel> courses, String csvFilePath) {
        try (FileWriter writer = new FileWriter(csvFilePath,true)) {
            File file = new File("courses.csv");

            if (file.length() == 0) {
                // Write the header only if the file is empty
                writer.write("Department,CourseTitle,CourseSubject,Instructor,Content,Level,Schedule\n");
            }
            // Write each course to CSV
            for (CourseModel course : courses) {
                writer.append(String.format("%s,%s,%s,%s,%s,%d,%s\n",
                        course.getCourseTitle(),
                        course.getCourseSubject(),
                        course.getDepartment(),
                        course.getInstructor().getName(),
                        course.getContent(),
                        course.getLevel(),
                        course.getSchedule().toString()));
            }

            System.out.println("Courses written to CSV successfully.");
        } catch (IOException e) {
            System.err.println("Error writing courses to CSV: " + e.getMessage());
        }
    }

    public static void removeCourseFromCsv(CourseModel courseToRemove, String csvFilePath) {
        try {
            List<String> lines = Files.readAllLines(Path.of(csvFilePath));

            // Remove the line corresponding to the course to be removed
            lines.removeIf(line -> line.startsWith(courseToRemove.getCourseTitle()));

            // Write the updated lines back to the CSV file
            Files.write(Path.of(csvFilePath), lines);

            System.out.println("Course removed from CSV successfully.");
        } catch (IOException e) {
            System.err.println("Error removing course from CSV: " + e.getMessage());
        }
    }

    public static void editCourseInCsv(CourseModel oldCourse, CourseModel newCourse, String csvFilePath) {
        try {
            List<String> lines = Files.readAllLines(Path.of(csvFilePath));

            // Replace the line corresponding to the old course with the data for the new course
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith(oldCourse.getCourseTitle())) {
                    lines.set(i, String.format("%s,%s,%s,%s,%s,%d,%s",
                            newCourse.getCourseTitle(),
                            newCourse.getCourseSubject(),
                            newCourse.getDepartment(),
                            newCourse.getInstructor().getName(),
                            newCourse.getContent(),
                            newCourse.getLevel(),
                            newCourse.getSchedule().toString()));
                    break;
                }
            }

            // Write the updated lines back to the CSV file
            Files.write(Path.of(csvFilePath), lines);

            System.out.println("Course edited in CSV successfully.");
        } catch (IOException e) {
            System.err.println("Error editing course in CSV: " + e.getMessage());
        }
    }

    public static void writeInstructorsToCsv(InstructorModel instructors, String csvFilePath) {
        try (FileWriter writer = new FileWriter(csvFilePath, true)) {
            // Append to the existing CSV file (true parameter for FileWriter constructor)

                writer.append(String.format("%s,%s\n",
                        instructors.getName(),
                        instructors.getCourse())); // Assuming an attribute for the course in InstructorModel


            System.out.println("Instructors written to CSV successfully.");
        } catch (IOException e) {
            System.err.println("Error writing instructors to CSV: " + e.getMessage());
        }
    }

    public static void removeInstructorFromCsv(InstructorModel instructorToRemove, String csvFilePath) {
        try {
            List<String> lines = Files.readAllLines(Path.of(csvFilePath));

            // Remove the line corresponding to the instructor to be removed
            lines.removeIf(line -> line.startsWith(instructorToRemove.getName()));

            // Write the updated lines back to the CSV file
            Files.write(Path.of(csvFilePath), lines);

            System.out.println("Instructor removed from CSV successfully.");
        } catch (IOException e) {
            System.err.println("Error removing instructor from CSV: " + e.getMessage());
        }
    }
    // Add this method to save courses by department to a CSV file
    // Updated method without importing BufferedWriter
    public static void saveCoursesByDepartmentToCSV(Map<String, List<CourseModel>> coursesByDepartment, String filePath) {
        try (FileWriter writer = new FileWriter(filePath,true)) {

                // Write header
            File file = new File("courses_by_department.csv");

            if (file.length() == 0) {
                // Write the header only if the file is empty
                writer.write("Department,CourseTitle,CourseSubject,Instructor,Content,Level,Schedule\n");
            }
            // Write data
            for (Map.Entry<String, List<CourseModel>> entry : coursesByDepartment.entrySet()) {
                String department = entry.getKey();
                for (CourseModel course : entry.getValue()) {
                    String line = String.format("%s,%s,%s,%s,%s,%d,%s\n",
                            department,
                            course.getCourseTitle(),
                            course.getCourseSubject(),
                            course.getInstructor().getName(),
                            course.getContent(),
                            course.getLevel(),
                            course.getSchedule().toString());
                    writer.write(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }
    // Add this new method to update the department map in the CSV file
    // Updated method to update the department map in the CSV file
    public static void updateDepartmentMapInCSV(Map<String, List<CourseModel>> coursesByDepartment, String oldDepartment, CourseModel oldCourse, String newDepartment, CourseModel newCourse, String filePath) {
        try (FileWriter writer = new FileWriter(filePath,true)) {
            // Write header
            File file = new File("courses_by_department.csv");

            if (file.length() == 0) {
                // Write the header only if the file is empty
                writer.write("Department,CourseTitle,CourseSubject,Instructor,Content,Level,Schedule\n");
            }
            // Write data
            for (Map.Entry<String, List<CourseModel>> entry : coursesByDepartment.entrySet()) {
                String department = entry.getKey();
                for (CourseModel course : entry.getValue()) {
                    // Check if the course matches the old course and update the department
                    if (course.equals(oldCourse) && department.equals(oldDepartment)) {
                        department = newDepartment;
                    }
                    String line = String.format("%s,%s,%s,%s,%s,%d,%s\n",
                            department,
                            course.getCourseTitle(),
                            course.getCourseSubject(),
                            course.getInstructor().getName(),
                            course.getContent(),
                            course.getLevel(),
                            course.getSchedule().toString());
                    writer.write(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }
    public static void saveEventsToCsv(Map<String, List<String>> calendarEvents) {
        try (FileWriter writer = new FileWriter("EVENTS_Calender.csv",true)) {
            // Write header
            File file = new File("EVENTS_Calender.csv");

            if (file.length() == 0) {
                // Write the header only if the file is empty
                writer.write("Date,Event\n");
            }

            // Write data
            for (Map.Entry<String, List<String>> entry : calendarEvents.entrySet()) {
                String date = entry.getKey();
                for (String event : entry.getValue()) {
                    String line = String.format("%s,%s\n", date, event);
                    writer.write(line);
                }
            }

            System.out.println("Events written to CSV successfully.");
        } catch (IOException e) {
            System.err.println("Error writing events to CSV: " + e.getMessage());
        }
    }
}
