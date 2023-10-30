import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StudentWelcomeFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JButton logoutButton;
    private JLabel welcomeLabel;

    private JTable courseTable;

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

            }
        });


        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Prompt the user to enter a course title or subject
                String searchTerm = JOptionPane.showInputDialog("Enter the course title or subject:");

                if (searchTerm != null && !searchTerm.isEmpty()) {
                    boolean courseFound = false;
                    int rowIndex = -1;

                    // Search for the course in the table
                    try {
                        for (int row = 0; row < courseTable.getRowCount(); row++) {
                            String title = courseTable.getValueAt(row, 0).toString();
                            String subject = courseTable.getValueAt(row, 1).toString();

                            if (title.equalsIgnoreCase(searchTerm) || subject.equalsIgnoreCase(searchTerm)) {
                                courseFound = true;
                                rowIndex = row;
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        // Handle any unexpected exceptions during the search
                        JOptionPane.showMessageDialog(null, "An error occurred while searching for the course.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (courseFound) {
                        // Display course details
                        try {
                            String courseTitle = courseTable.getValueAt(rowIndex, 0).toString();
                            String courseSubject = courseTable.getValueAt(rowIndex, 1).toString();
                            String courseInstructor = courseTable.getValueAt(rowIndex, 2).toString();
                            String courseContent = courseTable.getValueAt(rowIndex, 3).toString();
                            String courseDepartment = courseTable.getValueAt(rowIndex, 4).toString();
                            // Add other details as needed

                            // Display the course details to the user
                            JOptionPane.showMessageDialog(null, "Course Title: " + courseTitle + "\nCourse Subject: " + courseSubject
                                            + "\nInstructor: " + courseInstructor + "\nContent: " + courseContent + "\nDepartment: " + courseDepartment,
                                    "Course Details", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            // Handle any unexpected exceptions during the course details display
                            JOptionPane.showMessageDialog(null, "An error occurred while displaying course details.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Course not found
                        JOptionPane.showMessageDialog(null, "Course not found.", "Not Found", JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        });

        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        menuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        menuItem7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
