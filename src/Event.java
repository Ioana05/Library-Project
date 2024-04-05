import java.time.LocalDate;
import java.util.Scanner;

public class Event implements ReadWrite{
    private String eventName;
    private String eventDescription;
    protected LocalDate date;
    private String location;
    private int capacity;
    private boolean freeEntrance;

    public Event(String eventName, String eventDescription, LocalDate date, String location, int capacity, boolean freeEntrance) {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
        this.freeEntrance = freeEntrance;
    }

    public Event()
    {
        eventName = "";
        eventDescription = "";
        date = LocalDate.now();
        location = "";
        capacity = 0;
        freeEntrance = false;

    }

    public void readData(Scanner scanner) {
        System.out.print("Enter event name: ");
        this.eventName = scanner.nextLine();

        System.out.print("Enter event description: ");
        this.eventDescription = scanner.nextLine();

        System.out.print("Enter date(yyyy, m, d): ");
        this.date = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter location: ");
        this.location = scanner.nextLine();

        System.out.print("Enter capacity: ");
        this.capacity = scanner.nextInt();

        System.out.print("Is it free entrance? (true/false): ");
        this.freeEntrance = scanner.nextBoolean();

    }

    ///// Getteri /////////////


    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", freeEntrance=" + freeEntrance +
                '}';
    }

}
