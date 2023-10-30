import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class StudentWelcomeFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JButton logoutButton;
    private JLabel welcomeLabel;
    private JTable courseTable;
    private ArrayList<Course2> favoriteCourses = new ArrayList<>();


    public StudentWelcomeFrame(String name) {
        setTitle("Home Page");
        setSize(1100, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        welcomeLabel = new JLabel("Welcome " + name + ", please choose one of the facilities:");
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.BLUE);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        menu = new JMenu("Facilities");
        menuBar.add(menu);

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true); // Open the login frame
            }
        });

        JMenuItem menuItem1 = new JMenuItem("Browse and view a list of available courses");
        JMenuItem menuItem2 = new JMenuItem("Search for a course based on specific criteria");
        JMenuItem menuItem3 = new JMenuItem("View detailed information about a specific course");
        JMenuItem menuItem4 = new JMenuItem("View the schedule of classes for a specific course");
        JMenuItem menuItem5 = new JMenuItem("Mark courses as favourites");
        JMenuItem menuItem6 = new JMenuItem("Print the favourite courses list");
        JMenuItem menuItem7 = new JMenuItem("Enroll in a class");
        JMenuItem menuItem8 = new JMenuItem("Print my courses");
        JMenuItem menuItem9 = new JMenuItem("View your class grades");
        JMenuItem menuItem10 = new JMenuItem("View school academic calendar");
        JMenuItem menuItem11 = new JMenuItem("Access historical class schedules and academic performance records");

        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.add(menuItem3);
        menu.add(menuItem4);
        menu.add(menuItem5);
        menu.add(menuItem6);
        menu.add(menuItem7);
        menu.add(menuItem8);
        menu.add(menuItem9);
        menu.add(menuItem10);
        menu.add(menuItem11);

        JPanel mainpanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(welcomeLabel);
        courseTable = new JTable(CommonData.courseTableModel);

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Initialize the table with data from CommonData

                courseTable.setDefaultEditor(Object.class, null);

                // Set the preferred column widths
                courseTable.getColumnModel().getColumn(0).setPreferredWidth(150); // Title
                courseTable.getColumnModel().getColumn(1).setPreferredWidth(150); // Subject
                courseTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Instructor
                courseTable.getColumnModel().getColumn(3).setPreferredWidth(190); // Content
                courseTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Department
                courseTable.getColumnModel().getColumn(5).setPreferredWidth(100); // Days
                courseTable.getColumnModel().getColumn(6).setPreferredWidth(100); // Time
                courseTable.getColumnModel().getColumn(7).setPreferredWidth(100); // Level
                //Add the table to the panel and make it visible
                topPanel.add(courseTable);
                setVisible(true);
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] searchOptions = {"Title", "Subject", "Instructor", "Department"};

                // Prompt the user to choose a search option
                String selectedOption = (String) JOptionPane.showInputDialog(
                        null, "Select a search option:", "Search Course",
                        JOptionPane.QUESTION_MESSAGE, null, searchOptions, searchOptions[0]
                );

                if (selectedOption != null) {
                    // Prompt the user to enter the search term
                    String searchTerm = JOptionPane.showInputDialog("Enter the " + selectedOption + " to search for:");

                    if (searchTerm != null && !searchTerm.isEmpty()) {
                        Course2 foundCourse = CommonData.searchCourseByCriteria(searchTerm, selectedOption);
                        if (foundCourse != null) {
                            // Display course details
                            String courseDetails = "Course Title: " + foundCourse.getTitle() + "\nCourse Subject: " + foundCourse.getSubject()
                                    + "\nInstructor: " + foundCourse.getInstructor() + "\nContent: " + foundCourse.getContent()
                                    + "\nDepartment: " + foundCourse.getDepartment();

                            // Retrieve and append additional attributes to courseDetails
                            // ...

                            JOptionPane.showMessageDialog(null, courseDetails, "Course Details", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            // Course not found
                            JOptionPane.showMessageDialog(null, "Course not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter a course title or subject
                String searchTerm = JOptionPane.showInputDialog("Enter the course title:");

                if (searchTerm != null && !searchTerm.isEmpty()) {
                    Course2 foundCourse = CommonData.searchCourseByCriteria(searchTerm, "Title"); // Search by title
                    if (foundCourse != null) {
                        // Display course details
                        String courseDetails = "Course Title: " + foundCourse.getTitle() + "\nCourse Subject: " + foundCourse.getSubject()
                                + "\nInstructor: " + foundCourse.getInstructor() + "\nContent: " + foundCourse.getContent()
                                + "\nDepartment: " + foundCourse.getDepartment()  + "\nDays: " + foundCourse.getDays()+ "\nTime: " + foundCourse.getTime()+  "\nLevel: " + foundCourse.getLevel() ;

                        JOptionPane.showMessageDialog(null, courseDetails, "Course Details", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Course not found
                        JOptionPane.showMessageDialog(null, "Course not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
                    }
                }else {

                    // Course not found
                    JOptionPane.showMessageDialog(null, "Enter valid input", "Input is not valid", JOptionPane.ERROR_MESSAGE);
                }
            }

        });


        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = JOptionPane.showInputDialog("Enter the course:");

                if (searchTerm != null && !searchTerm.isEmpty()) {
                    // Search for the course in the table
                    Course2 foundCourse = CommonData.searchCourseByCriteria(searchTerm, "Title"); // Search by title

                    if (foundCourse != null) {
                        // Show the course details to the user
                        JOptionPane.showMessageDialog(null, "Course Name: " + searchTerm + "\nCourse Days: " + foundCourse.getDays()
                                + "\nCourse Time: " + foundCourse.getTime(), "Course Days and Time", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Course not found
                        JOptionPane.showMessageDialog(null, "Course not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter the course title they want to add to their favorite list
                String searchTerm = JOptionPane.showInputDialog("Enter the course title to add to your favorite list:");

                if (searchTerm != null && !searchTerm.isEmpty()) {
                    // Search for the course in the available courses
                    Course2 foundCourse = CommonData.searchCourseByCriteria(searchTerm, "Title"); // Search by title

                    if (foundCourse != null) {
                        // Add the found course to the favorite course list
                        favoriteCourses.add(foundCourse);

                        // Show a message confirming the addition
                        JOptionPane.showMessageDialog(null, "Course added to your favorite list.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Course not found in the available courses
                        JOptionPane.showMessageDialog(null, "Course not found in the available courses.", "Not Found", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        menuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (favoriteCourses.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Your favorite course list is empty.", "Favorite Courses", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Create a string to display the favorite course list
                    StringBuilder favoriteCoursesList = new StringBuilder("Favorite Courses:\n");

                    for (Course2 course : favoriteCourses) {
                        favoriteCoursesList.append(course.getTitle()).append("\n");
                    }

                    // Show the favorite course list to the user
                    JOptionPane.showMessageDialog(null, favoriteCoursesList.toString(), "Favorite Courses", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        menuItem7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter the course title they want to enroll in
                String searchTerm = JOptionPane.showInputDialog("Enter the course title you want to enroll in:");

                if (searchTerm != null && !searchTerm.isEmpty()) {
                    // Search for the course in the available courses
                    Course2 foundCourse = CommonData.searchCourseByCriteria(searchTerm, "Title"); // Search by title
                    if (foundCourse != null && foundCourse.getTitle() != null && foundCourse.getTitle().equals(searchTerm)) {                        foundCourse.enrollStudent(name);
                        // Show a message confirming the enrollment
                        JOptionPane.showMessageDialog(null, "You have successfully enrolled in the course.", "Enrollment Successful", JOptionPane.INFORMATION_MESSAGE);
                    } else  {
                        // Course not found in the available courses
                        JOptionPane.showMessageDialog(null, "Course not found in the available courses.", "Not Found", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        menuItem8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        menuItem9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        menuItem10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        menuItem11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //topPanel.add(scrollPane);
        mainpanel.add(topPanel, BorderLayout.CENTER);
        mainpanel.add(logoutButton, BorderLayout.SOUTH);

        add(mainpanel);

        setResizable(false);
        setVisible(true);
    }




}
