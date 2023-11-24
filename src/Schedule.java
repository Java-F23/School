public class Schedule {
    private String name;
    private int startTime; // Assuming startTime is an integer representing time

    public Schedule(String name, int startTime) {
        this.name = name;
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public int getStartTime() {
        return startTime;
    }

    @Override
    public String toString() {
        return name + " at " + startTime;
    }
}
