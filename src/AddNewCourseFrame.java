import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class AddNewCourseFrame extends JFrame {
    private JTextField courseNameTextField, titleTextField, instructorTextField, contentTextField;
    private JComboBox<CommonData.Department> departmentComboBox;
    private JComboBox<CommonData.Time> timeComboBox;
    private JComboBox<CommonData.Level> levelComboBox;
    private JCheckBox[] dayCheckBoxes;
    private int error =0, error2=0;

    public AddNewCourseFrame() {
        setTitle("Add New Course");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2));

        panel.add(new JLabel("Course Name:"));
        courseNameTextField = new JTextField();
        panel.add(courseNameTextField);

        panel.add(new JLabel("Title:"));
        titleTextField = new JTextField();
        panel.add(titleTextField);

        panel.add(new JLabel("Instructor:"));
        instructorTextField = new JTextField();
        panel.add(instructorTextField);

        panel.add(new JLabel("Content:"));
        contentTextField = new JTextField();
        panel.add(contentTextField);

        panel.add(new JLabel("Department:"));
        departmentComboBox = new JComboBox<>(CommonData.Department.values());
        panel.add(departmentComboBox);

        panel.add(new JLabel("Level:"));
       // Integer[] levels = {1, 2, 3, 4};
        levelComboBox = new JComboBox<>(CommonData.Level.values());
        panel.add(levelComboBox);

        panel.add(new JLabel("Days:"));
        JPanel dayPanel = new JPanel();
        CommonData.WorkingDays[] days = CommonData.WorkingDays.values();
        dayCheckBoxes = new JCheckBox[days.length];
        for (int i = 0; i < days.length; i++) {
            dayCheckBoxes[i] = new JCheckBox(days[i].toString());
            dayPanel.add(dayCheckBoxes[i]);
        }
        panel.add(dayPanel);

        panel.add(new JLabel("Time:"));
        CommonData.Time[] times = CommonData.Time.values();
        timeComboBox = new JComboBox<>(times);
        panel.add(timeComboBox);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle saving course information here
                String courseName = courseNameTextField.getText();
                String title = titleTextField.getText();
                String instructor = instructorTextField.getText();
                String content = contentTextField.getText();
                CommonData.Department department = (CommonData.Department) departmentComboBox.getSelectedItem();
                CommonData.Level level = (CommonData.Level) levelComboBox.getSelectedItem();
                ArrayList<CommonData.WorkingDays> selectedDays = new ArrayList<>();
                for (int i = 0; i < dayCheckBoxes.length; i++) {
                    if (dayCheckBoxes[i].isSelected()) {
                        selectedDays.add(days[i]);
                    }
                }
                CommonData.Time selectedTime = (CommonData.Time) timeComboBox.getSelectedItem();
                DefaultTableModel courseTableModel = new DefaultTableModel();

                // Validate input fields using try-catch
                try {
                    validateInput(courseName, title, instructor, content);
                    if (courseTableModel.equals(title)) {
                        throw new IllegalArgumentException("Course title already exists.");
                    }
                    error = error -1;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(AddNewCourseFrame.this, ex.getMessage());
                    error=1;
                }
                // Validate the number of selected days using try-catch
                try {
                    validateSelectedDays(selectedDays);
                    error2 = error2 -1;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(AddNewCourseFrame.this, ex.getMessage());
                    error2=1;
                }

                if (error <= 0 && error2<=0)
                {
                    CommonData.addCourseToTable(courseTableModel, title, courseName, instructor, content, department, selectedDays, selectedTime, level);
                    JOptionPane.showMessageDialog(AddNewCourseFrame.this,"Course added successfully");
                    dispose();
                }
            }
            private void validateInput(String courseName, String title, String instructor, String content) throws IllegalArgumentException {
                if (courseName.isEmpty() || !courseName.matches("[A-Za-z\\s]+") || title.isEmpty() || !title.matches("[A-Za-z\\s]+") || instructor.isEmpty() ||!instructor.matches("[A-Za-z\\s]+") || content.isEmpty() || !content.matches("[A-Za-z\\s]+")) {
                    throw new IllegalArgumentException("Please enter valid input for all fields.");
                }
                // Add more validation rules as needed.
            }
            private void validateSelectedDays(ArrayList<CommonData.WorkingDays> selectedDays) throws IllegalArgumentException {
                if (selectedDays.size() < 1 || selectedDays.size() > 2) {
                    throw new IllegalArgumentException("Please select 1 or 2 days.");
                }
            }
            private boolean isCourseTitleExists(DefaultTableModel courseTableModel, String title) {
                for (int row = 0; row < courseTableModel.getRowCount(); row++) {
                    if (title.equals(courseTableModel.getValueAt(row, 0))) {
                        return true; // Title exists in the table
                    }
                }
                return false; // Title does not exist in the table
            }
        });

        mainPanel.add(saveButton, BorderLayout.SOUTH);
        mainPanel.add(panel);
        add(mainPanel);

        setResizable(false);
        setVisible(true);
    }


}
