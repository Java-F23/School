import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.Map;
public class AdministratorView extends JFrame {
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
                AdministratorControl.addNewCourse(); // Delegate to the controller
            }
        });
        Enroll_student.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdministratorControl.enrollStudent(); // Delegate to the controller
            }
        });
        editCourseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdministratorControl.editExistingCourse(); // Delegate to the controller
            }
        });
        removeCourse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdministratorControl.removeCourse(); // Delegate to the controller
            }
        });

        categorizeCoursesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  AdministratorControl.addToDepartmentMap();
            }
        });
    }

    public static void displayCourseAddedMessage(String courseTitle) {
        JOptionPane.showMessageDialog(null, "Course '" + courseTitle + "' added successfully!");
    }

}
