import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentWelcomeFrame extends JFrame {

    private JMenuBar menuBar;
    private JMenu menu;
    private JButton logoutButton;
    private JLabel welcomeLabel;
    public StudentWelcomeFrame(String name) {
        setTitle("Home Page");
        setSize(500, 500);
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

        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                BrowseCoursesFrame browseCoursesFrame = new BrowseCoursesFrame();
               // browseCoursesFrame.setVisible(true); // Open the BrowseCoursesFrame
            }
        });

        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                SearchCourseFrame searchCourseFrame = new SearchCourseFrame();
              //  searchCourseFrame.setVisible(true); // Open the SearchCourseFrame
            }
        });

        menuItem3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                ViewDetailedInformationFrame viewDetailedInformationFrame = new ViewDetailedInformationFrame();
             //   viewDetailedInformationFrame.setVisible(true); // Open the ViewDetailedInformationFrame
            }
        });

        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                ViewScheduleOfClassFrame viewScheduleOfClassFrame = new ViewScheduleOfClassFrame();
            //    viewScheduleOfClassFrame.setVisible(true); // Open the ViewScheduleOfClassFrame
            }
        });

        menuItem5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                MarkCoursesAsFavouritesFrame markCoursesAsFavouritesFrame = new MarkCoursesAsFavouritesFrame();
            //    markCoursesAsFavouritesFrame.setVisible(true); // Open the MarkCoursesAsFavouritesFrame
            }
        });

        menuItem6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                PrintFavouriteCoursesList printFavouriteCoursesList = new PrintFavouriteCoursesList();
             //   printFavouriteCoursesList.setVisible(true); // Open the PrintFavouriteCoursesList
            }
        });

        menuItem7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                EnrollInAClass enrollInAClass = new EnrollInAClass();
            //    enrollInAClass.setVisible(true); // Open the EnrollInAClass
            }
        });

        menuItem8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                PrintMyCourses printMyCourses = new PrintMyCourses();
            //    printMyCourses.setVisible(true); // Open the PrintMyCourses
            }
        });

        menuItem9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                ViewYourClassGrades viewYourClassGrades = new ViewYourClassGrades();
            //    viewYourClassGrades.setVisible(true); // Open the ViewYourClassGrades
            }
        });

        menuItem10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                ViewSchoolAcademicCalendar viewSchoolAcademicCalendar = new ViewSchoolAcademicCalendar();
            }
        });

        menuItem11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                HistoricalClassScheduleandRecords viewSchoolAcademicCalendar = new HistoricalClassScheduleandRecords();
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
