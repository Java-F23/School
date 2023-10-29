import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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
