import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {


    private JLabel welcomeLabel,nameLabel,roleLabel;
    private JTextField nameField;
    private JRadioButton studentButton,adminButton;
    private ButtonGroup roleGroup;
    private JButton loginButton;

    private String name;
    public LoginFrame() {
        //setting frame name, size
        setTitle("Login");
        setSize(390,230);

      //  setLayout(new GridLayout(4, 3));
        //To stop the program if the frame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //To show the frame in the center of the screen
        setLocationRelativeTo(null);

        welcomeLabel = new JLabel("Welcome To A.B.C School");
        nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        roleLabel = new JLabel("Role:");
        studentButton = new JRadioButton("Student");
        adminButton = new JRadioButton("Administrator");
        roleGroup = new ButtonGroup();
        roleGroup.add(studentButton);
        roleGroup.add(adminButton);
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    name = nameField.getText();

                    if (isValidName(name)) {
                        if (studentButton.isSelected()) {
                            StudentWelcomeFrame studentWelcome = new StudentWelcomeFrame(name);
                           // studentWelcome.setVisible(true);
                            dispose();
                        } else if (adminButton.isSelected()) {
                            AdministratorWelcomeFrame adminWelcome = new AdministratorWelcomeFrame(name);
                           // adminWelcome.setVisible(true);
                            dispose();
                        }

                    }
                } catch ( Exception ex) {
                    JOptionPane.showMessageDialog(LoginFrame.this,"Name is not valid");
                }
            }
        });

        //setting the label font and color
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 20));
        welcomeLabel.setForeground(Color.BLUE);


        //creating 4 panels; top panel for the welcome label, center panel for the name label, textfiled, role label and the radiopanel, radiopanel for the radiobuttons and main panel to include the previous panels
        JPanel mainpanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel centerPanel = new JPanel(new GridLayout(2, 2,10,40));
        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //centerpanel is two rows and two columns. the first row for the name label and name field and the second row for the role llabel and the two radio buttons
        topPanel.add(welcomeLabel);
        centerPanel.add(nameLabel);
        centerPanel.add(nameField);
        radioPanel.add(studentButton);
        radioPanel.add(adminButton);
        centerPanel.add(roleLabel);
        centerPanel.add(radioPanel);
        mainpanel.add(topPanel, BorderLayout.NORTH);
        mainpanel.add(centerPanel, BorderLayout.CENTER);
        mainpanel.add(loginButton, BorderLayout.SOUTH);

        add(mainpanel);

        //To disable minimization or maximization
        setResizable(false);
        setVisible(true);



    }

    //Check if the name is valid or not
    private boolean isValidName(String name) throws Exception {
        if (name.isEmpty() || !name.matches("[A-Za-z\\s]+")) {
            throw new Exception("Invalid username");
        }
        return true;
    }

    public String getName() {
        return nameField.getText();
    }
}
