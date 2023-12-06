import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.util.ArrayList;

// StudentView.java
public class StudentView {
    private JFrame frame;
    private JTextArea outputTextArea;
    private AdministratorModel adminModel;

    // Constructor
    public StudentView(String name) {
        frame = new JFrame("Student View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        frame.setVisible(true);
        frame.setResizable(false);

        // Center the frame on the screen
        frame.setLocationRelativeTo(null);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        frame.add(scrollPane);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Create menus
        JMenu coursesMenu = new JMenu("Courses");
        JMenu favoritesMenu = new JMenu("Favorites");
        JMenu enrollmentMenu = new JMenu("Enrollment");
        JMenu gradesMenu = new JMenu("Grades");

        // Add menus to the menu bar
        menuBar.add(coursesMenu);
        menuBar.add(favoritesMenu);
        menuBar.add(enrollmentMenu);
        menuBar.add(gradesMenu);

        // Create menu items for "Courses" menu
        JMenuItem browseCoursesItem = new JMenuItem("Browse List of Current Courses");
        JMenuItem searchCourseItem = new JMenuItem("Search for a Course");
        JMenuItem viewCourseDetailsItem = new JMenuItem("View Details for a Course");
        JMenuItem viewCourseScheduleItem = new JMenuItem("View Schedule for a Course");

        // Add menu items to the "Courses" menu
        coursesMenu.add(browseCoursesItem);
        coursesMenu.add(searchCourseItem);
        coursesMenu.add(viewCourseDetailsItem);
        coursesMenu.add(viewCourseScheduleItem);

        // Create menu items for "Favorites" menu
        JMenuItem markAsFavoriteItem = new JMenuItem("Mark a Course as Favorite");
        JMenuItem showFavoritesItem = new JMenuItem("Show My Favorite Courses List");

        // Add menu items to the "Favorites" menu
        favoritesMenu.add(markAsFavoriteItem);
        favoritesMenu.add(showFavoritesItem);

        // Create menu items for "Enrollment" menu
        JMenuItem enrollInClassItem = new JMenuItem("Enroll in a Class");
        JMenuItem printMyCoursesItem = new JMenuItem("Print My Courses");

        // Add menu items to the "Enrollment" menu
        enrollmentMenu.add(enrollInClassItem);
        enrollmentMenu.add(printMyCoursesItem);

        // Create menu items for "Grades" menu
        JMenuItem viewClassGradesItem = new JMenuItem("View Class Grades");
        JMenuItem viewPastClassesItem = new JMenuItem("Access Past Classes Schedule and Performance Records");

        // Add menu items to the "Grades" menu
        gradesMenu.add(viewClassGradesItem);
        gradesMenu.add(viewPastClassesItem);

        browseCoursesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coursesText = StudentControl.browseCurrentCourses();
                // Update the view to display current courses
                outputTextArea.setFont(outputTextArea.getFont().deriveFont(Font.BOLD));
                outputTextArea.setText("Browsing List of Current Courses:\n" + coursesText);
            }
        });
        searchCourseItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user for input
                String courseName = JOptionPane.showInputDialog(frame, "Enter the course name:");

                // Check if the user entered a course name
                if (courseName != null && !courseName.isEmpty()) {
                    // Call the method to display course details by name
                    String courseDetails = StudentControl.displayCourseDetailsByName(courseName);

                    // Update the view to display course details
                    outputTextArea.setFont(outputTextArea.getFont().deriveFont(Font.BOLD));
                    outputTextArea.setText("Searching for a course:\n" + courseDetails);
                } else {
                    // User canceled or entered an empty string
                    outputTextArea.setText("Search canceled or empty course name.");
                }
            }
        });

        viewCourseDetailsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coursesText = StudentControl.browseAllCourseDetails();
                outputTextArea.setFont(outputTextArea.getFont().deriveFont(Font.BOLD));
                outputTextArea.setText("Viewing course schedule:\n" + coursesText);
            }
        });
        viewCourseScheduleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coursesText = StudentControl.browseCourseSchedules();
                outputTextArea.setFont(outputTextArea.getFont().deriveFont(Font.BOLD));
                outputTextArea.setText("Viewing course schedule:\n" + coursesText);
            }
        });
        markAsFavoriteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.setFont(outputTextArea.getFont().deriveFont(Font.BOLD));
                outputTextArea.setText("Mark course as favourite :\n" );
            }
        });
    }

    // Method to display messages in the output text area
    private void displayMessage(String message) {
        outputTextArea.append(message + "\n");
    }

}
