import java.time.LocalDate;

public class Task {
    private int durationMin;
    private LocalDate dueDate; 

    public Task(int durationMin, LocalDate dueDate) {
        this.durationMin = durationMin;
        this.dueDate = dueDate;
    }

    public int getDurationMin() {
        return durationMin;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}