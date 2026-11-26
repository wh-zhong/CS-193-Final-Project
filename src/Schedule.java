import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

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
        // Sort by due date
        // Done so that tasks with earlier due dates are scheduled first
        tasks.sort(Comparator.comparing(Task::getDueDate));

        // Implement scheduling logic here
        for (DayOfWeek day : DayOfWeek.values()) {
            int[] blocks = getBlocksByDay(day);
            
            for (int i = 0; i < blocks.length; i++) {
                int blockDuration = blocks[i];
                for (int j = 0; j < tasks.size(); j++) {
                    Task task = tasks.get(j);
                    if (task.getDueDate().getDayOfWeek().compareTo(day) <= 0 && task.getDurationMin() <= blockDuration) {
                        // Schedule the task in this block
                        System.out.println("Scheduling task with duration " + task.getDurationMin() + " minutes on " + day);
                        // Remove the task from the list after scheduling
                        tasks.remove(j);
                        break; // Move to the next block after scheduling a task
                    } else if (task.getDueDate().getDayOfWeek().compareTo(day) > 0) {
                        System.out.println("Task with duration " + task.getDurationMin() + " minutes cannot be scheduled on " + day + " as its due date is later.");
                        tasks.remove(j);
                        continue;
                    }
                }
            }
        }

    }

    private int[] getBlocksByDay(DayOfWeek day) {
        ArrayList<Integer> timeBlocks = new ArrayList<Integer>();
        for (int i = 0; i < events.size(); i++) {
            Event curr = events.get(i);

            if (day != curr.getStart().getDayOfWeek()) {
                continue;
            }

            LocalTime start;
            if (i == 0) {
                start = startOfDay;
            } else {
                start = events.get(i - 1).getEnd().toLocalTime();
            }

            LocalTime end = curr.getStart().toLocalTime();

            Duration duration = Duration.between(start, end);
            timeBlocks.add((int) duration.toMinutes());
        }

        timeBlocks.add((int) Duration.between(events.get(events.size() - 1).getEnd().toLocalTime(), endOfDay).toMinutes());

        return timeBlocks.stream().mapToInt(i -> i).toArray();
    }
}