import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


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
                    DefaultTableModel departmentTableModel = new DefaultTableModel();
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


        JPanel mainpanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanel.add(welcomeLabel);
        mainpanel.add(topPanel, BorderLayout.CENTER);
        mainpanel.add(logoutButton, BorderLayout.SOUTH);

        add(mainpanel);

        setResizable(false);
        setVisible(true);
    }

}
