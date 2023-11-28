public class newMain {

    public static void main(String[] args) {

        AdministratorModel c = new AdministratorModel();
        System.out.println(CSVHandler.loadCoursesFromCsv("courses.csv"));
        System.out.println(c.getCourses());
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}