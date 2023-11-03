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

    private ArrayList<Course2> enrolledCourses = new ArrayList<>();

    public ArrayList<Course2> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(ArrayList<Course2> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public StudentWelcomeFrame(String name) {
        setTitle("Home Page");
        setSize(1100, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        welcomeLabel = new JLabel("Welcome " + name + ", please choose one of the facilities:");
        welcomeLabel.setToolTipText("Check the facilities menu at the top of the page.");
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
        JPanel topPanel = new JPanel(new FlowLayout());
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
                    if (foundCourse != null && foundCourse.getTitle() != null && foundCourse.getTitle().equals(searchTerm)) {
                        foundCourse.enrollStudent(name);
                        // Add the enrolled course to the enrolledCourses ArrayList
                        enrolledCourses.add(foundCourse);
                        // Add the student name to the students list in this course and give initial vlaue for this course grade
                        foundCourse.addStudentGrade(name,null);
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

                String message = "Enrolled Courses:\n";
                for (Course2 course : enrolledCourses) {
                    message += "- " + course.getTitle() + "\n";
                }
                JOptionPane.showMessageDialog(null, message, "Enrolled Courses", JOptionPane.INFORMATION_MESSAGE);
            }
        });



        menuItem9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (enrolledCourses.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "You are not enrolled in any courses.", "No Enrolled Courses", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String message = "Your Grades:\n";

                    for (Course2 course : enrolledCourses) {
                        // Replace "getGradeForStudent(name)" with the actual method to retrieve the grade for the student
                        String grade = course.getGradeForStudent(name); // Assuming you have a method like this
                        message += course.getTitle() + ": " + grade + "\n";
                    }

                    JOptionPane.showMessageDialog(null, message, "Enrolled Courses Grades", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        menuItem10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a new frame to display the academic calendar
                JFrame calendarFrame = new JFrame("Academic Calendar");
                calendarFrame.setLocationRelativeTo(null);

                // Create a panel to hold the calendar content
                JPanel calendarPanel = new JPanel();
                calendarPanel.setLayout(new GridLayout(0, 2)); // Two columns for events and dates

                // Events and dates
                String[] eventsArray = {
                        "First day of classes", "Armed Forces Day", "Thanksgiving", "Final exam starting",
                        "Winter break begins", "New Year's Day", "Spring break", "Last day of classes",
                        "Graduation ceremony", "Summer vacation starts", "Independence Day", "Fall semester begins"
                };

                String[] datesArray = {
                        "1/9/2023", "6/10/2023", "23/11/2023", "15/12/2023",
                        "21/12/2023", "1/1/2024", "10/3/2024", "30/4/2024",
                        "15/5/2024", "1/6/2024", "4/7/2024", "5/9/2024"
                };


                // Add events and dates to the calendarPanel
                for (int i = 0; i < eventsArray.length; i++) {
                    JLabel eventLabel = new JLabel(eventsArray[i]);
                    JLabel dateLabel = new JLabel(datesArray[i]);
                    calendarPanel.add(eventLabel);
                    calendarPanel.add(dateLabel);
                }

                // Create a JScrollPane and add the calendarPanel to it
                JScrollPane scrollPane = new JScrollPane(calendarPanel);
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                // Add the scroll pane to the frame
                calendarFrame.getContentPane().add(scrollPane);

                // Set the frame size and make it visible
                calendarFrame.setSize(400, 200); // Adjust the size as needed
                calendarFrame.setResizable(false);
                calendarFrame.setVisible(true);
            }
        });

        menuItem11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a list to store old classes with course titles and grades
                ArrayList<String> oldClasses = new ArrayList<>();

                // Populate the list with sample data (course titles and grades)
                oldClasses.add("Math 101, UW 8:30,  Grade: A");
                oldClasses.add("Science 201, MR 2:00, Grade: B");
                oldClasses.add("Literature 301, MR 10:30,  Grade: A-");
                oldClasses.add("History 101, UW 11:30, Grade: B+");

                // Create a DefaultListModel to hold the old classes data
                DefaultListModel<String> listModel = new DefaultListModel<>();

                // Add old classes data to the list model
                for (String courseInfo : oldClasses) {
                    listModel.addElement(courseInfo);
                }

                // Create a JList to display old courses
                JList<String> oldCoursesList = new JList<>(listModel);

                // Enable tooltips for the JList items
                oldCoursesList.setToolTipText("Your historical class schedules and grades");

                // Create a JScrollPane to add scrolling functionality
                JScrollPane scrollPane = new JScrollPane(oldCoursesList);

                // Show the old courses list in a dialog message
                JOptionPane.showMessageDialog(
                        null, scrollPane, "Old Courses",
                        JOptionPane.INFORMATION_MESSAGE
                );
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
