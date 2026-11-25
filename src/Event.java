import java.time.LocalDate;

public class Event {
    private LocalDate start;
    private LocalDate end;

    public Event(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}