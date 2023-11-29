import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashMap;


public class CSVHandler {
    private static Map<String, List<CourseModel>> departmentMap = new LinkedHashMap<>();

    public static void writeCoursesToCsv(CourseModel course, String csvFilePath) {
        try (FileWriter writer = new FileWriter(csvFilePath,true)) {
            File file = new File("courses.csv");

            if (file.length() == 0) {
                // Write the header only if the file is empty
                writer.write("CourseTitle,CourseSubject,Department,Instructor,Content,Level,Schedule\n");
            }
            // Write each course to CSV
           // for (CourseModel course : courses) {
                writer.append(String.format("%s,%s,%s,%s,%s,%d,%s\n",
                        course.getCourseTitle(),
                        course.getCourseSubject(),
                        course.getDepartment(),
                        course.getInstructor().getName(),
                        course.getContent(),
                        course.getLevel(),
                        course.getSchedule().toString()));
           // }

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
    public static void saveCoursesByDepartmentToCSV(Map<String, ArrayList<CourseModel>> coursesByDepartment, String filePath) {
        try (FileWriter writer = new FileWriter(filePath,true)) {

                // Write header
            File file = new File("courses_by_department.csv");

            if (file.length() == 0) {
                // Write the header only if the file is empty
                writer.write("Department,CourseTitle,CourseSubject,Instructor,Content,Level,Schedule\n");
            }
            // Write data
            for (Map.Entry<String, ArrayList<CourseModel>> entry : coursesByDepartment.entrySet()) {
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
    public static void updateDepartmentMapInCSV(Map<String, ArrayList<CourseModel>> coursesByDepartment, String oldDepartment, CourseModel oldCourse, String newDepartment, CourseModel newCourse, String filePath) {
        try (FileWriter writer = new FileWriter(filePath,true)) {
            // Write header
            File file = new File("courses_by_department.csv");

            if (file.length() == 0) {
                // Write the header only if the file is empty
                writer.write("Department,CourseTitle,CourseSubject,Instructor,Content,Level,Schedule\n");
            }
            // Write data
            for (Map.Entry<String, ArrayList<CourseModel>> entry : coursesByDepartment.entrySet()) {
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
    public static void writeStudentsToCsv(List<StudentModel> students, String courseName, String fileName) {
        try (FileWriter writer = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) { // Using BufferedWriter for efficiency

            // Check if the file is empty
            if (new File(fileName).length() == 0) {
                bufferedWriter.write("Name,Course\n");
            }

            // Write student data
            for (StudentModel student : students) {
                String line = String.format("%s,%s\n", student.getName(), courseName);
                bufferedWriter.write(line);
            }
        } catch (IOException e) {
            // Handle the exception (e.g., log or display an error message)
            e.printStackTrace();
        }
    }


    public static ArrayList<CourseModel> loadCoursesFromCsv(String csvFilePath) {
        Map<String, List<CourseModel>> departmentMap = new LinkedHashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            // Skip the header row
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into individual fields
                String[] fields = line.split(",");

                // Extract information from fields
                String department = fields[2];
                String courseTitle = fields[0];
                String courseSubject = fields[1];
                String instructorName = fields[3];
                InstructorModel instructor = new InstructorModel(instructorName);
                String content = fields[4];
                int level = Integer.parseInt(fields[5]);
                String schedule = fields[6];
                String[] scheduleParts = schedule.split("at");
                String day = scheduleParts[0].trim();
                int time = Integer.parseInt(scheduleParts[1].trim());
                Schedule courseSchedule = new Schedule(day, time);

                // Create a CourseModel object
                CourseModel course = new CourseModel(courseTitle, courseSubject, department, instructor, content, level, courseSchedule);

                // Add the CourseModel object to the departmentMap
                departmentMap.computeIfAbsent(department, k -> new ArrayList<>()).add(course);
            }
        } catch (IOException e) {
            // Handle the exception (e.g., log an error)
            e.printStackTrace();
        }

        // Combine all courses from the departmentMap into a single list
        ArrayList<CourseModel> courses = new ArrayList<>();
        departmentMap.values().forEach(courses::addAll);

        return courses;
    }

    public static Map<String, ArrayList<CourseModel>> loadCoursesByDepartmentFromCsv(String csvFilePath) throws IOException {
        Map<String, ArrayList<CourseModel>> coursesByDepartment = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            // Skip the header row
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into individual fields
                String[] fields = line.split(",");

                // Extract information from fields
                String department = fields[0];
                String courseTitle = fields[1];
                String courseSubject = fields[2];
                String instructorName = fields[3];
                InstructorModel instructor = new InstructorModel(instructorName);
                String content = fields[4];
                int level = Integer.parseInt(fields[5]);
                String schedule = fields[6];
                String[] scheduleParts = schedule.split("at");
                String day = scheduleParts[0].trim();
                int time = Integer.parseInt(scheduleParts[1].trim());
                Schedule courseSchedule = new Schedule(day, time);

                // Create a CourseModel object
                CourseModel course = new CourseModel(courseTitle, courseSubject, department, instructor, content, level, courseSchedule);


                // Add the CourseModel object to the coursesByDepartment map
                coursesByDepartment.computeIfAbsent(department, k -> new ArrayList<>()).add(course);
            }
        }

        return coursesByDepartment;
    }




}
