import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
public class AdministratorView extends JFrame {
    private JMenuBar menuBar;
    private JMenu menu;
    private JButton logoutButton;
    private JLabel welcomeLabel;

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
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Courses Menu
        JMenu coursesMenu = new JMenu("Courses");
        menuBar.add(coursesMenu);

        JMenuItem addCourseItem = new JMenuItem("Add a new course");
        JMenuItem editCourseItem = new JMenuItem("Edit an existing course");
        JMenuItem categorizeCoursesItem = new JMenuItem("Categorize courses by department");
        coursesMenu.add(addCourseItem);
        coursesMenu.add(editCourseItem);
        coursesMenu.add(categorizeCoursesItem);

        // Students Menu
        JMenu studentsMenu = new JMenu("Students");
        menuBar.add(studentsMenu);

        JMenuItem manageEnrollmentItem = new JMenuItem("Track and manage student enrollment");
        JMenuItem manageAssignmentItem = new JMenuItem("Track and manage class assignment");
        JMenuItem calculateGPAItem = new JMenuItem("Calculate students' GPA");
        studentsMenu.add(manageEnrollmentItem);
        studentsMenu.add(manageAssignmentItem);
        studentsMenu.add(calculateGPAItem);

        // Instructors Menu
        JMenu instructorsMenu = new JMenu("Instructors");
        menuBar.add(instructorsMenu);

        JMenuItem addRemoveInstructorItem = new JMenuItem("Add or remove an instructor");
        instructorsMenu.add(addRemoveInstructorItem);

        setVisible(true);

    }
}
