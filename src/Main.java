import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.time.temporal.TemporalAdjusters;

public class Main {
    public static void main(String[] args) {
        IO.println("Wecome to your Scheduling Assistant!");

        Schedule schedule = new Schedule(new ArrayList<Event>(), new ArrayList<Task>());

        String startOfDayInput = IO.readln("Enter the start of your day (format: HH:mm) in military time: ");
        
        LocalTime startOfDay = null;
        while (startOfDay == null) {
            try {
                startOfDay = LocalTime.parse(startOfDayInput);
            } catch (Exception e) {
                IO.println("Invalid time format. Please use the format HH:mm.");
                startOfDayInput = IO.readln("Enter the start of your day (format: HH:mm) in military time: ");
            }
        }


        String endOfDayInput = IO.readln("Enter the end of your day (format: HH:mm) in military time: ");
        LocalTime endOfDay = null;
        while (endOfDay == null) {
            try {
                endOfDay = LocalTime.parse(endOfDayInput);
            } catch (Exception e) {
                IO.println("Invalid time format. Please use the format HH:mm.");
                endOfDayInput = IO.readln("Enter the end of your day (format: HH:mm) in military time: ");
            }
        }

        schedule.setStartOfDay(startOfDay);
        schedule.setEndOfDay(endOfDay);

        int numEvents = -1;
        while (numEvents == -1) {
            try {
                numEvents = Integer.parseInt(IO.readln("Please enter the number of events you would like to add: "));

                if (numEvents < 0) {
                    numEvents = -1; // Reset input to -1 to continue the loop
                    IO.println("Invalid input. Please enter a non-negative number.");
                }
            } catch (NumberFormatException e) {
                IO.println("Invalid input. Please enter a valid number.");
                numEvents = -1; // Reset input to -1 to continue the loop
            }
        }

        for (int i = 0; i < numEvents; i++) {
            String day = IO.readln("Enter an integer representing the day of the week for event " + (i + 1) + " (0 = Monday, 6 = Sunday): ");

            int dayOfWeek = -1;
            while (dayOfWeek == -1) {
                try {
                    dayOfWeek = Integer.parseInt(day);

                    if (dayOfWeek < 0 || dayOfWeek > 6) {
                        dayOfWeek = -1; // Reset input to -1 to continue the loop
                        IO.println("Invalid input. Please enter an integer between 0 and 6.");
                        day = IO.readln("Enter an integer representing the day of the week for event " + (i + 1) + " (0 = Monday, 6 = Sunday): ");
                    }
                } catch (NumberFormatException e) {
                    IO.println("Invalid input. Please enter a valid integer.");
                    day = IO.readln("Enter an integer representing the day of the week for event " + (i + 1) + " (0 = Monday, 6 = Sunday): ");
                }
            }

            String startTimeInput = IO.readln("Enter the start time for event " + (i + 1) + " (format: HH:mm) in military time: ");
            LocalTime startTime = null;
            while (startTime == null) {
                try {
                    startTime = LocalTime.parse(startTimeInput);
                } catch (Exception e) {
                    IO.println("Invalid time format. Please use the format HH:mm.");
                    startTimeInput = IO.readln("Enter the start time for event " + (i + 1) + " (format: HH:mm) in military time: ");
                }
            }

            String endTimeInput = IO.readln("Enter the end time for event " + (i + 1) + " (format: HH:mm) in military time: ");
            LocalTime endTime = null;
            while (endTime == null) {
                try {
                    endTime = LocalTime.parse(endTimeInput);
                } catch (Exception e) {
                    IO.println("Invalid time format. Please use the format HH:mm.");
                    endTimeInput = IO.readln("Enter the end time for event " + (i + 1) + " (format: HH:mm) in military time: ");
                }
            }

            DayOfWeek dayOfWeekEnum = DayOfWeek.of(dayOfWeek + 1); // Convert to DayOfWeek enum (1 = Monday, 7 = Sunday)
            LocalDateTime start = startTime.atDate(LocalDate.now().with(TemporalAdjusters.nextOrSame(dayOfWeekEnum)));
            LocalDateTime end = endTime.atDate(LocalDate.now().with(TemporalAdjusters.nextOrSame(dayOfWeekEnum)));

            schedule.addEvent(new Event(start, end));
        }
    }
}