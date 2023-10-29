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

    public StudentWelcomeFrame(String name) {
        setTitle("Home Page");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize the table with data from CommonData
        courseTable = new JTable(CommonData.courseTableModel);

        JScrollPane scrollPane = new JScrollPane(courseTable);





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
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCourseList();
                topPanel.add(scrollPane);
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

    private void showCourseList() {
     //   ArrayList<Course2> courses = CommonData.getAvailableCourses();

        // Create a frame and display the course list
      //  JFrame courseListFrame = new JFrame("Available Courses");
     //   courseListFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a JTable to display the courses
      //  JTable courseTable = createCourseTable(courses);

      //  JScrollPane scrollPane = new JScrollPane(courseTable);
      //  courseListFrame.add(scrollPane);
        courseTable.setModel(CommonData.courseTableModel);

       // courseListFrame.pack();
   //     courseListFrame.setVisible(true);
    }

    private JTable createCourseTable(ArrayList<Course2> courses) {
        String[] columnNames = {"Title", "Subject", "Instructor", "Content", "Department", "Days", "Time", "Level"};
        Object[][] data = new Object[courses.size()][8];

        for (int i = 0; i < courses.size(); i++) {
            Course2 course = courses.get(i);
            data[i][0] = course.getTitle();
            data[i][1] = course.getSubject();
            data[i][2] = course.getInstructor();
            data[i][3] = course.getContent();
            data[i][4] = course.getDepartment();
            data[i][5] = course.getDays();
            data[i][6] = course.getTime();
            data[i][7] = course.getLevel();
        }

        return new JTable(data, columnNames);
    }


}
