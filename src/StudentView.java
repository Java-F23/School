import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// StudentView.java
public class StudentView {
    private JFrame frame;
    private JTextArea outputTextArea;
    // Constructor
    public StudentView(String name) {
        frame = new JFrame("Student View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);

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

        // Set up action listeners for menu items
        browseCoursesItem.addActionListener(e -> displayMessage("Browsing List of Current Courses"));

        browseCoursesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                browseCurrentCourses();
            }
        });
        searchCourseItem.addActionListener(e -> displayMessage("Searching for a Course"));
        viewCourseDetailsItem.addActionListener(e -> displayMessage("Viewing Details for a Course"));
        viewCourseScheduleItem.addActionListener(e -> displayMessage("Viewing Schedule for a Course"));

        markAsFavoriteItem.addActionListener(e -> displayMessage("Marking a Course as Favorite"));
        showFavoritesItem.addActionListener(e -> displayMessage("Showing My Favorite Courses List"));

        enrollInClassItem.addActionListener(e -> displayMessage("Enrolling in a Class"));
        printMyCoursesItem.addActionListener(e -> displayMessage("Printing My Courses"));

        viewClassGradesItem.addActionListener(e -> displayMessage("Viewing Class Grades"));
        viewPastClassesItem.addActionListener(e -> displayMessage("Accessing Past Classes Schedule and Performance Records"));

        frame.setVisible(true);
        // Make the frame non-resizable
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // Method to display messages in the output text area
    private void displayMessage(String message) {
        outputTextArea.append(message + "\n");
    }

    // Method to display current courses in the GUI
    public void displayCurrentCourses(String courses) {
        outputTextArea.setText(courses);
    }

    // Method to display current courses in a new frame
    private void browseCurrentCourses() {
        // Get the list of courses from the AdministratorModel
        ArrayList<CourseModel> courses = adminModel.getCourses();

        // Display courses in the view
        StringBuilder coursesText = new StringBuilder();
        for (int i = 0; i < courses.size(); i++) {
            coursesText.append(i + 1).append(". ").append(courses.get(i).getCourseTitle()).append("\n");
        }

        // Update the view to display current courses
        displayCurrentCourses(coursesText.toString());
    }


}
