import com.sun.jdi.ArrayReference;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.Map;
import java.io.IOException;

public class AdministratorView extends JFrame {


    public static boolean isCoursesByDepartmentOpen = false; // Flag to track whether the courses by department window is open
    private JMenuBar menuBar;
    private JMenu menu;
    private JButton logoutButton;
    private JLabel welcomeLabel;
    private JTable departmentMapTable;


    public AdministratorView(String name) {
        setTitle("Home Page");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


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
        JPanel mainpanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(welcomeLabel);
        mainpanel.add(topPanel, BorderLayout.CENTER);
        mainpanel.add(logoutButton, BorderLayout.SOUTH);

        add(mainpanel);

        setResizable(false);
        setVisible(true);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Courses Menu
        JMenu coursesMenu = new JMenu("Courses");
        menuBar.add(coursesMenu);

        JMenuItem addCourseItem = new JMenuItem("Add a new course");
        JMenuItem editCourseItem = new JMenuItem("Edit an existing course");
        JMenuItem removeCourse = new JMenuItem("Remove a course");

        JMenuItem categorizeCoursesItem = new JMenuItem("Categorize courses by department");
        coursesMenu.add(addCourseItem);
        coursesMenu.add(editCourseItem);
        coursesMenu.add(removeCourse);
        coursesMenu.add(categorizeCoursesItem);

        // Students Menu
        JMenu studentsMenu = new JMenu("Students");
        menuBar.add(studentsMenu);

        JMenuItem Enroll_student = new JMenuItem("Enroll student in a course");
        JMenuItem manageAssignmentItem = new JMenuItem("Track and manage class assignment");
        JMenuItem calculateGPAItem = new JMenuItem("Calculate students' GPA");
        studentsMenu.add(Enroll_student);
        studentsMenu.add(manageAssignmentItem);
        studentsMenu.add(calculateGPAItem);

        // Instructors Menu
        JMenu instructorsMenu = new JMenu("Instructors");
        menuBar.add(instructorsMenu);

        JMenuItem addRemoveInstructorItem = new JMenuItem("Add or remove an instructor");
        instructorsMenu.add(addRemoveInstructorItem);
        setVisible(true);

        addCourseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isCoursesByDepartmentOpen) {
                    isCoursesByDepartmentOpen = true;
                    AdministratorControl.addNewCourse(); // Delegate to the controller
                } else {
                    JOptionPane.showMessageDialog(null, "Another window is already open.");
                }
            }
        });
        Enroll_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isCoursesByDepartmentOpen) {
                    isCoursesByDepartmentOpen = true;
                    AdministratorControl.enrollStudent(); // Delegate to the controller
                } else {
                    JOptionPane.showMessageDialog(null, "Another window is already open.");
                }
            }
        });
        editCourseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isCoursesByDepartmentOpen) {
                    isCoursesByDepartmentOpen = true;
                    AdministratorControl.editExistingCourse(); // Delegate to the controller
                } else {
                    JOptionPane.showMessageDialog(null, "Another window is already open.");
                }
            }
        });
        removeCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isCoursesByDepartmentOpen) {
                    isCoursesByDepartmentOpen = true;
                    AdministratorControl.removeCourse(); // Delegate to the controller
                } else {
                    JOptionPane.showMessageDialog(null, "Another window is already open.");
                }
            }
        });

        categorizeCoursesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isCoursesByDepartmentOpen) {
                    isCoursesByDepartmentOpen = true;
                    displayCoursesByDepartment(AdministratorModel.getCoursesByDepartment());
                } else {
                    JOptionPane.showMessageDialog(null, "Courses by department window is already open.");
                }
            }
        });
    }

    public static void displayCourseAddedMessage(String courseTitle) {
        JOptionPane.showMessageDialog(null, "Course '" + courseTitle + "' added successfully!");

    }
    public static void displayCoursesByDepartment(Map<String, ArrayList<CourseModel>> coursesByDepartment) {
        try {
            coursesByDepartment = CSVHandler.loadCoursesByDepartmentFromCsv("courses_by_department.csv");

            JFrame frame = new JFrame("Courses by Department");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    // Make cells non-editable
                    return false;
                }
            };

            JTable table = new JTable(model);

            // Add columns to the table
            model.addColumn("Department");
            model.addColumn("Course Title");

            // Add data to the table
            for (Map.Entry<String, ArrayList<CourseModel>> entry : coursesByDepartment.entrySet()) {
                String department = entry.getKey();
                ArrayList<CourseModel> courses = entry.getValue();

                for (CourseModel course : courses) {
                    model.addRow(new Object[]{department, course.getCourseTitle()});
                }
            }

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);

            // Set the size of the frame
            frame.setSize(600, 400);

            // Center the frame on the screen
            frame.setLocationRelativeTo(null);

            frame.setVisible(true);

            frame.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                   isCoursesByDepartmentOpen = false; // Reset the flag when the window is closed
                }
            });
        } catch (IOException e) {
            // Handle the exception, e.g., log it or show an error message
            e.printStackTrace();
        }
    }
}
