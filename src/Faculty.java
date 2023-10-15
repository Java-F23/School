public class Faculty {

    private String name;
    private String qualification;
    public Faculty(String name,String qualification) {
        this.name = name;
        this.qualification = qualification;

    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getQualification() {
        return qualification;
    }
}
