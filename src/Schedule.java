import java.time.LocalTime;
import java.util.ArrayList;

public class Schedule {
    private LocalTime startOfDay;
    private LocalTime endOfDay;

    private int minutesBetweenTasks;

    private ArrayList<Event> events;
    private ArrayList<Task> tasks;

    public Schedule(ArrayList<Event> events, ArrayList<Task> tasks) {
        this.events = events;
        this.tasks = tasks;
    }

    public Event[] getEvents() {
        return events.toArray(new Event[0]);
    }

    public Task[] getTasks() {
        return tasks.toArray(new Task[0]);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void calculateSchedule() {
        // Implement scheduling logic here
    }
}