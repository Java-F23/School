import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;


public class AdministratorWelcomeFrame extends JFrame {
    private JMenuBar menuBar;
    private JMenu menu;
    private JButton logoutButton;
    private JLabel welcomeLabel;


    public AdministratorWelcomeFrame(String name) {
        setTitle("Home Page");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        DefaultTableModel departmentTableModel = new DefaultTableModel();
        DefaultTableModel courseTableModel = CommonData.courseTableModel;


        welcomeLabel = new JLabel("Welcome " + name + ", please choose one of the facilities:");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.BLUE);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true); // Open the login frame
            }
        });


        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu = new JMenu("Options");
        menuBar.add(menu);

        JMenuItem menuItem1 = new JMenuItem("Add a new course");
        JMenuItem menuItem2 = new JMenuItem("Edit an existing course");
        JMenuItem menuItem3 = new JMenuItem("Add or remove an instructor");
        JMenuItem menuItem4 = new JMenuItem("Categorize courses by department");
        JMenuItem menuItem5 = new JMenuItem("Track and manage student enrollment");
        JMenuItem menuItem6 = new JMenuItem("Track and manage class assignment");
        JMenuItem menuItem7 = new JMenuItem("Calculate students' GPA");


        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);
        menu.add(menuItem5);
        menu.add(menuItem6);
        menu.add(menuItem7);


        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create an instance of AddNewCourseFrame
                AddNewCourseFrame addNewCourseFrame = new AddNewCourseFrame();

                // Make the AddNewCourseFrame visible
                addNewCourseFrame.setVisible(true);

            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the administrator to enter the course title
                String courseTitle = JOptionPane.showInputDialog("Enter the course title to edit:");

                if (courseTitle != null && !courseTitle.isEmpty()) {
                    // Search for the course in the available courses
                    Course2 foundCourse = CommonData.searchCourseByCriteria(courseTitle, "Title");

                    if (foundCourse != null) {
                        // Display a combo box to choose the attribute to edit
                        String[] attributes = {"Title", "Subject", "Department", "Days", "Time", "Level", "Instructor", "Content"};
                        String selectedAttribute = (String) JOptionPane.showInputDialog(
                                null,
                                "Choose the attribute to edit:",
                                "Edit Course Attribute",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                attributes,
                                attributes[0]
                        );

                        if (selectedAttribute != null) {
                            // Prompt the administrator to enter the new value for the selected attribute
                            String newValue = JOptionPane.showInputDialog("Enter the new value for " + selectedAttribute + ":");
                            if (newValue != null) {
                                // Update the selected attribute of the course
                                switch (selectedAttribute) {
                                    case "Title":
                                        foundCourse.setTitle(newValue);
                                        break;
                                    case "Subject":
                                        foundCourse.setSubject(newValue);
                                        break;
                                    case "Department":
                                        foundCourse.setDepartment(CommonData.Department.valueOf(newValue)); // Assuming Department is an enum
                                        break;
                                    case "Days":
                                        foundCourse.setDays(CommonData.getWorkingDaysList(newValue));
                                        break;
                                    case "Time":
                                        foundCourse.setTime(CommonData.Time.valueOf(newValue));
                                        break;
                                    case "Level":
                                        foundCourse.setLevel(CommonData.Level.valueOf(newValue));
                                        break;
                                    case "Instructor":
                                        foundCourse.setInstructor(newValue);
                                        break;
                                    case "Content":
                                        foundCourse.setContent(newValue);
                                        break;
                                }
                                // Inform the administrator that the attribute has been updated
                                JOptionPane.showMessageDialog(null, selectedAttribute + " updated successfully.", "Attribute Updated", JOptionPane.INFORMATION_MESSAGE);

                            }
                        }
                    } else {
                        // Course not found in the available courses
                        JOptionPane.showMessageDialog(null, "Course not found in the available courses.", "Not Found", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });
        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
// Show a dialog to choose whether to add or remove an instructor
                JRadioButton addInstructorButton = new JRadioButton("Add Instructor");
                JRadioButton removeInstructorButton = new JRadioButton("Remove Instructor");
                ButtonGroup radioGroup = new ButtonGroup();
                radioGroup.add(addInstructorButton);
                radioGroup.add(removeInstructorButton);

                Object[] options = {"OK", "Cancel"};
                Object[] message = {
                        "Choose an action:",
                        addInstructorButton,
                        removeInstructorButton
                };

                int choice = JOptionPane.showOptionDialog(
                        null,
                        message,
                        "Add/Remove Instructor",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (choice == JOptionPane.OK_OPTION) {
                    if (addInstructorButton.isSelected()) {
                        // Add Instructor
                        String newInstructor = JOptionPane.showInputDialog("Enter the name of the new instructor:");
                        if (newInstructor != null && !newInstructor.isEmpty()) {
                            CommonData.getInstructorsList();
                            if (!CommonData.getInstructorsList().contains(newInstructor)) {
                                CommonData.getInstructorsList().add(newInstructor);
                                JOptionPane.showMessageDialog(null, "Instructor added successfully.", "Instructor Added", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Instructor already exists.", "Instructor Exists", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else if (removeInstructorButton.isSelected()) {
                        // Remove Instructor
                        if (CommonData.getInstructorsList().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No instructors to remove.", "No Instructors", JOptionPane.ERROR_MESSAGE);
                        } else {
                            String[] instructorsArray = CommonData.getInstructorsList().toArray(new String[0]);
                            String selectedInstructor = (String) JOptionPane.showInputDialog(
                                    null,
                                    "Select an instructor to remove:",
                                    "Remove Instructor",
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    instructorsArray,
                                    instructorsArray[0]
                            );

                            if (selectedInstructor != null) {
                                CommonData.getInstructorsList().remove(selectedInstructor);
                                JOptionPane.showMessageDialog(null, "Instructor removed successfully.", "Instructor Removed", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }
                    }
                }

            }
        });

        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create a frame to display department-based categorization
                JFrame categorizationFrame = new JFrame("Course Categorization by Department");
                categorizationFrame.setSize(800, 600);
                categorizationFrame.setLocationRelativeTo(null);
                categorizationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Get an ArrayList of all available departments (you might need to modify this)
                ArrayList<CommonData.Department> departments = new ArrayList<>();
                for (CommonData.Department department : CommonData.Department.values()) {
                    departments.add(department);
                }

                // Create a tabbed pane to hold tables for each department
                JTabbedPane tabbedPane = new JTabbedPane();


                // Iterate through departments and create a table for each
                for (CommonData.Department department : departments) {
                    // Get courses for the current department (modify as per your data structure)
                    System.out.println(department);
                    ArrayList<Course2> departmentCourses = CommonData.getCoursesByDepartment(department);

                    // Create a table model for the department courses
                    departmentTableModel.addColumn("Title");
                    departmentTableModel.addColumn("Instructor");
                    departmentTableModel.addColumn("Days");
                    departmentTableModel.addColumn("Time");

                    // Add course data to the table model
                    for (Course2 course : departmentCourses) {
                        Object[] courseData = {course.getTitle(), course.getInstructor(), course.getDays(), course.getTime()};
                        departmentTableModel.addRow(courseData);
                    }

                    // Create a table for the department and add it to a scroll pane
                    JTable departmentTable = new JTable(departmentTableModel);
                    JScrollPane scrollPane = new JScrollPane(departmentTable);
                    departmentTable.setDefaultEditor(Object.class, null);

                    // Add the scroll pane to the tabbed pane with the department name as the tab title
                    tabbedPane.addTab(department.toString(), scrollPane);
                }

                // Add the tabbed pane to the categorization frame
                categorizationFrame.add(tabbedPane);

                // Set the frame to be visible
                categorizationFrame.setVisible(true);

                setResizable(false);
            }
        });
        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create a dialog for the administrator to choose from four options
                String[] options = {
                        "Add/Remove Student to Course",
                        "Display Number of Students in Course",
                        "Display Enrolled Courses for Specific Student"
                };
                int choice = JOptionPane.showOptionDialog(
                        null,
                        "Choose an action:",
                        "Course and Student Management",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]
                );

                if (choice == 0) {
                    // Add/remove Student
                    // Show a dialog to choose to add or remove a student
                    String[] addRemoveOptions = {"Add Student to Course", "Remove Student from Course"};
                    int addRemoveChoice = JOptionPane.showOptionDialog(
                            null,
                            "Choose an action:",
                            "Add/Remove Student from Course",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            addRemoveOptions,
                            addRemoveOptions[0]
                    );

                    // Create an array of course titles for the dropdown
                    String[] courseTitles = new String[courseTableModel.getRowCount() - 1];
                    for (int row = 1; row < courseTableModel.getRowCount(); row++) {
                        String title = courseTableModel.getValueAt(row, CommonData.getColumnIndex("Title")).toString();

                        courseTitles[row - 1] = title;
                    }

                    // Show a dropdown with course titles
                    String selectedCourse = (String) JOptionPane.showInputDialog(
                            null,
                            "Choose a course to add the student to:",
                            "Add Student to Course",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            courseTitles,
                            courseTitles[0]
                    );

                    Course2 originalcourse= null;
                    for (int row = 1; row < courseTableModel.getRowCount(); row++) {
                        String title = courseTableModel.getValueAt(row, CommonData.getColumnIndex("Title")).toString();
                        if (title.equals(selectedCourse)) {
                            originalcourse = CommonData.getCourseDetails(row);
                            originalcourse=CommonData.searchCourseByCriteria(originalcourse.getTitle(), "Title");
                            break;
                        }
                    }

                    if (addRemoveChoice == 0) {
                        try {
                            String studentName = JOptionPane.showInputDialog("Enter the student's name:");

                            if (studentName == null || studentName.isEmpty()) {
                                throw new IllegalArgumentException("Please enter a valid student's name.");
                            }

                            if (originalcourse == null) {
                                throw new IllegalArgumentException("Course not found.");
                            }

                            originalcourse.enrollStudent(studentName);

                            // Notify the administrator that the student has been added to the course
                            JOptionPane.showMessageDialog(
                                    null,
                                    studentName + " added to " + selectedCourse + " successfully.",
                                    "Student Added to Course",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }else if (addRemoveChoice == 1)
                    {
                        // Get the list of students in the selected course
                        ArrayList<String> enrolledStudentsList = originalcourse.getEnrolledStudents();
                        if (enrolledStudentsList != null && !enrolledStudentsList.isEmpty()) {
                            // Get the list of students in the selected course
                            String[] enrolledStudentsArray = originalcourse.getEnrolledStudents().toArray(new String[0]);

                            if (enrolledStudentsArray.length > 0) {
                                // Show a dialog to choose one of the students in the course
                                int selectedStudentIndex = JOptionPane.showOptionDialog(
                                        null,
                                        "Choose a student to remove from the course:",
                                        "Remove Student from Course",
                                        JOptionPane.QUESTION_MESSAGE,
                                        JOptionPane.DEFAULT_OPTION,
                                        null,
                                        enrolledStudentsArray,
                                        enrolledStudentsArray[0]
                                );

                                if (selectedStudentIndex != -1) {
                                    // Remove the selected student from the course
                                    String selectedStudent = enrolledStudentsArray[selectedStudentIndex];
                                    enrolledStudentsList.remove(selectedStudent);

                                    // Notify the administrator that the student has been removed from the course
                                    JOptionPane.showMessageDialog(null,selectedStudent + " removed from " + selectedCourse + " successfully.",
                                            "Student Removed from Course",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                }
                            } else {
                                // Handle the case where there are no students in the course
                                JOptionPane.showMessageDialog(
                                        null,
                                        "No students in the course.",
                                        "No Students in Course",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } else {
                            // Handle the case where there are no enrolled students in the course
                            JOptionPane.showMessageDialog(
                                    null,
                                    "No students are enrolled in the course.",
                                    "No Students Enrolled",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                } else if (choice == 1) {
                    try {
                        String courseToDisplay = JOptionPane.showInputDialog("Enter the name of the course to display the number of students:");

                        if (courseToDisplay != null && !courseToDisplay.isEmpty()) {
                            int enrollmentCount = 0;
                            boolean courseFound = false;

                            // Iterate through all available courses and find the selected course
                            for (int row = 1; row < courseTableModel.getRowCount(); row++) {
                                String title = courseTableModel.getValueAt(row, CommonData.getColumnIndex("Title")).toString();
                                if (title.equals(courseToDisplay)) {
                                    Course2 selectedCourseObject = CommonData.getCourseDetails(row);
                                    enrollmentCount = selectedCourseObject.getEnrolledStudents().size();
                                    courseFound = true;
                                    break;
                                }
                            }

                            if (courseFound) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        courseToDisplay + " has " + enrollmentCount + " student(s) enrolled.",
                                        "Enrollment Count",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            } else {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Course not found: " + courseToDisplay,
                                        "Enrollment Count",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Please enter a valid course name.",
                                    "Invalid Course Name",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Invalid input. Please enter a valid course name.",
                                "Invalid Input",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else if (choice == 2) {
                    try {
                        String studentName = JOptionPane.showInputDialog("Enter the student's name");

                        if (studentName != null && !studentName.isEmpty()) {
                            StringBuilder enrolledCoursesText = new StringBuilder("Enrolled Courses for " + studentName + ":\n");
                            boolean coursesFound = false; // Flag to check if any courses were found

                            // Modify this section to check for enrolled students (case-insensitive)
                            for (int row = 1; row < courseTableModel.getRowCount(); row++) {
                                Course2 course = CommonData.getCourseDetails(row);
                                ArrayList<String> enrolledStudents = course.getEnrolledStudents();

                                boolean studentFound = enrolledStudents.stream()
                                        .anyMatch(student -> student.equalsIgnoreCase(studentName));

                                if (studentFound) {
                                    enrolledCoursesText.append(course.getTitle()).append("\n");
                                    coursesFound = true;
                                }
                            }

                            if (coursesFound) {
                                // Display the enrolled courses for the specific student
                                JOptionPane.showMessageDialog(
                                        null,
                                        enrolledCoursesText.toString(),
                                        "Enrolled Courses for " + studentName,
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            } else {
                                // Handle the case where no courses were found for the specific student
                                JOptionPane.showMessageDialog(
                                        null,
                                        "No courses found for student: " + studentName,
                                        "Student Not Found",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } else {
                            throw new Exception("Invalid Student Name");
                        }
                    } catch (Exception ex) {
                        // Handle any unexpected exceptions during input
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(
                                null,
                                "Please enter a valid student's name.",
                                "Invalid Student Name",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }

            }
        });

        menuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Create an array of course titles for the dropdown
                String[] courseTitles = new String[courseTableModel.getRowCount() - 1];
                for (int row = 1; row < courseTableModel.getRowCount(); row++) {
                    String title = courseTableModel.getValueAt(row, CommonData.getColumnIndex("Title")).toString();
                    courseTitles[row - 1] = title;
                }

                // Show a dropdown with course titles
                String selectedCourse = (String) JOptionPane.showInputDialog(
                        null,
                        "Choose a course:",
                        "Select a Course",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        courseTitles,
                        courseTitles[0]
                );
                if (selectedCourse != null) {
                    // Administrator options
                    String[] options = {
                            "Add Assignment to Course",
                            "Remove Assignment from Course",
                            "Display Assignments for Specific Course"
                    };

                    int choice = JOptionPane.showOptionDialog(
                            null,
                            "Choose an option:",
                            "Assignment Management",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            options,
                            options[0]
                    );

                    Course2 selectedCourseObject = null; // Retrieve the selected course object (Course2)

                    // Find the selected course object based on the course name
                    for (int row = 1; row < courseTableModel.getRowCount(); row++) {
                        String title = courseTableModel.getValueAt(row, CommonData.getColumnIndex("Title")).toString();
                        if (title.equals(selectedCourse)) {
                            selectedCourseObject = CommonData.getCourseDetails(row);
                            break;
                        }
                    }

                    if (choice == 0) {
                        // Add Assignment to Course
                        if (selectedCourseObject != null) {
                            // Gather assignment details from the administrator (You can use your GUI components)
                            String assignmentName = JOptionPane.showInputDialog("Enter assignment name:");
                            String assignmentDescription = JOptionPane.showInputDialog("Enter assignment description:");
                            String dueDateStr = JOptionPane.showInputDialog("Enter due date (yyyy-MM-dd):");

                            try {
                                // Add the assignment to the selected course
                                selectedCourseObject.addAssignment(assignmentName, assignmentDescription, dueDateStr);

                                // Notify the administrator that the assignment has been added
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Assignment added to " + selectedCourse + " successfully.",
                                        "Assignment Added",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            } catch (IllegalArgumentException e1) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Invalid date format. Please enter a valid date (yyyy-MM-dd).",
                                        "Invalid Date Format",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } else {
                            // Handle the case where the selected course is not found
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Course not found.",
                                    "Course Not Found",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else if (choice == 1) {
                        // Remove Assignment from Course
                        if (selectedCourseObject != null) {
                            // Retrieve the list of assignments in the course
                            ArrayList<Assignment> assignments = selectedCourseObject.getAssignments();

                            if (!assignments.isEmpty()) {
                                String[] assignmentNames = new String[assignments.size()];

                                for (int i = 0; i < assignments.size(); i++) {
                                    assignmentNames[i] = assignments.get(i).getName();
                                }

                                int selectedAssignmentIndex = JOptionPane.showOptionDialog(
                                        null,
                                        "Choose an assignment to remove:",
                                        "Remove Assignment from Course",
                                        JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        assignmentNames,
                                        assignmentNames[0]
                                );

                                if (selectedAssignmentIndex >= 0) {
                                    String assignmentNameToRemove = assignmentNames[selectedAssignmentIndex];

                                    // Find and remove the assignment from the course
                                    for (Assignment assignment : assignments) {
                                        if (assignment.getName().equals(assignmentNameToRemove)) {
                                            assignments.remove(assignment);
                                            break;
                                        }
                                    }

                                    // Notify the administrator that the assignment has been removed
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Assignment removed from " + selectedCourse + " successfully.",
                                            "Assignment Removed",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                }
                            } else {
                                // Handle the case where there are no assignments in the course
                                JOptionPane.showMessageDialog(
                                        null,
                                        "No assignments in the course.",
                                        "No Assignments",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } else {
                            // Handle the case where the selected course is not found
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Course not found.",
                                    "Course Not Found",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else if (choice == 2) {
                        // Display Assignments for Specific Course
                        if (selectedCourseObject != null) {
                            ArrayList<Assignment> assignments = selectedCourseObject.getAssignments();
                            if (!assignments.isEmpty()) {
                                String assignmentsText = "";
                                for (Assignment assignment : assignments) {
                                    assignmentsText += "Assignment Name: " + assignment.getName() + "\n";
                                    assignmentsText += "Description: " + assignment.getDescription() + "\n";
                                    assignmentsText += "Due Date: " + assignment.getDueDate() + "\n\n";
                                }
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Assignments for " + selectedCourse + ":\n\n" + assignmentsText,
                                        "Assignments for Course",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            } else {
                                // Handle the case where there are no assignments in the course
                                JOptionPane.showMessageDialog(
                                        null,
                                        "No assignments in the course.",
                                        "No Assignments",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            }
                        } else {
                            // Handle the case where the selected course is not found
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Course not found.",
                                    "Course Not Found",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            }
        });



        menuItem7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create an array of course titles for the dropdown
                String[] courseTitles = new String[courseTableModel.getRowCount() - 1];
                for (int row = 1; row < courseTableModel.getRowCount(); row++) {
                    String title = courseTableModel.getValueAt(row, CommonData.getColumnIndex("Title")).toString();
                    courseTitles[row - 1] = title;
                }

                // Show a dropdown with course titles
                String selectedCourse = (String) JOptionPane.showInputDialog(
                        null,
                        "Choose a course for GPA calculation:",
                        "Calculate Students' GPA",
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        courseTitles,
                        courseTitles[0]
                );

                if (selectedCourse != null) {
                    Course2 selectedCourseObject = null;

                    // Find the selected course object based on the chosen course name
                    for (int row = 1; row < courseTableModel.getRowCount(); row++) {
                        String title = courseTableModel.getValueAt(row, CommonData.getColumnIndex("Title")).toString();
                        if (title.equals(selectedCourse)) {
                            selectedCourseObject = CommonData.getCourseDetails(row);
                            break;
                        }
                    }

                    if (selectedCourseObject != null) {
                        ArrayList<String> students = selectedCourseObject.getEnrolledStudents();
                        if (!students.isEmpty()) {
                            // Display GPA for each student
                            String gpaText = "";

                            for (String student : students) {
                                double gpa = CommonData.calculateStudentGPA(student, selectedCourseObject);
                                gpaText += "Student: " + student + " - GPA: " + gpa + "\n";
                            }

                            // Display GPA information
                            JOptionPane.showMessageDialog(
                                    null,
                                    "GPA for students in " + selectedCourse + ":\n\n" + gpaText,
                                    "Students' GPA",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            // Handle the case where there are no students in the course
                            JOptionPane.showMessageDialog(
                                    null,
                                    "No students in the course.",
                                    "No Students in Course",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    } else {
                        // Handle the case where the selected course is not found
                        JOptionPane.showMessageDialog(
                                null,
                                "Course not found.",
                                "Course Not Found",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }

            // Function to calculate a student's GPA (you can implement your own logic here)

        });

        JPanel mainpanel = new JPanel(new BorderLayout());
             JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
             topPanel.add(welcomeLabel);
             mainpanel.add(topPanel, BorderLayout.CENTER);
             mainpanel.add(logoutButton, BorderLayout.SOUTH);

             add(mainpanel);

             setResizable(false);
             setVisible(true);

        
    }


   private void displayEnrollmentCounts() {
        DefaultTableModel departmentTableModel = CommonData.courseTableModel;
        // Create a dialog or frame to display enrollment counts
        JFrame enrollmentFrame = new JFrame("Enrollment Counts");
        enrollmentFrame.setSize(400, 300);
        enrollmentFrame.setLocationRelativeTo(null);
        enrollmentFrame.setResizable(false);

       JTextArea enrollmentTextArea = new JTextArea();
        enrollmentTextArea.setEditable(false);

        // Iterate through all available courses and display enrollment counts
        for (int row = 1; row < departmentTableModel.getRowCount(); row++) {
            //Course2 course = new Course2(null,null,null,null,null,null,null,null); // Initialize foundCourse to null
            // Use the getCourseDetails method to get course details
            Course2 course = CommonData.getCourseDetails(row);
           // System.out.println(departmentTableModel.getRowCount());
            //course = course.getCourseDetails(row);
            int enrollmentCount = course.getEnrolledStudents().size();
            enrollmentTextArea.append(course.getTitle() + ": " + enrollmentCount + " students\n");
        }

        enrollmentFrame.add(enrollmentTextArea);
        enrollmentFrame.setVisible(true);

   }

    public void getStudentCountsForAllCourses() {
        DefaultTableModel courseTableModel = CommonData.courseTableModel;

        StringBuilder counts = new StringBuilder();
        int ssize = 0;
        for (int row = 1; row < courseTableModel.getRowCount(); row++) {
            Course2 course = CommonData.getCourseDetails(row);
            ArrayList<String> enrollmentCount = course.getEnrolledStudents();
            counts.append(course.getTitle()).append(": ").append(enrollmentCount.size()).append(" students\n");
             ssize = enrollmentCount.size();
            System.out.println(course.getEnrolledStudents().size());

        }

    }


}