import java.util.Date;
public class Assignment {
    private String name;
    private String description;
    private Date dueDate;

    public Assignment(String name, String description, Date dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    // Getter and setter methods for the assignment properties
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }
}
