package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Event implements ReadWrite {

    private int eventId;
    private String eventName;
    private String eventDescription;
    protected LocalDate date;
    private String location;
    private int capacity;
    private boolean freeEntrance;

    private int libraryId;

    public Event(int eventId, String eventName, String eventDescription, LocalDate date, String location, int capacity, boolean freeEntrance, int libraryId) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
        this.freeEntrance = freeEntrance;
        this.libraryId = libraryId;
    }

    public Event()
    {
        eventId = 0;
        eventName = "";
        eventDescription = "";
        date = LocalDate.now();
        location = "";
        capacity = 0;
        freeEntrance = false;
        libraryId = 0;

    }

    public Event(ResultSet result) throws SQLException{
            this.eventId = result.getInt("eventID");
            this.libraryId = result.getInt("libraryID");
            this.eventName = result.getString("eventName");
            this.eventDescription = result.getString("eventdescription");
            this.date = result.getDate("localDate").toLocalDate();
            this.location = result.getString("location");
            this.capacity = result.getInt("capacity");
            this.freeEntrance = result.getBoolean("freeEntrance");

    }

    public void readData(Scanner scanner) {
        System.out.print("Enter event id: ");
        this.eventId = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter event name: ");
        this.eventName = scanner.nextLine();

        System.out.print("Enter event description: ");
        this.eventDescription = scanner.nextLine();

        System.out.print("Enter date(yyyy-mm-dd): ");
        this.date = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter location: ");
        this.location = scanner.nextLine();

        System.out.print("Enter capacity: ");
        this.capacity = scanner.nextInt();

        System.out.print("Is it free entrance? (true/false): ");
        this.freeEntrance = scanner.nextBoolean();

        System.out.print("ID of the library which organised it: ");
        this.libraryId = scanner.nextInt();


    }

    ///// Getteri /////////////


    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFreeEntrance() {
        return freeEntrance;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId && capacity == event.capacity && freeEntrance == event.freeEntrance && libraryId == event.libraryId && Objects.equals(eventName, event.eventName) && Objects.equals(eventDescription, event.eventDescription) && Objects.equals(date, event.date) && Objects.equals(location, event.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, eventDescription, date, location, capacity, freeEntrance, libraryId);
    }

    @Override
    public String toString() {
        return "model.Event{" +
                "eventId='" + eventId + '\'' +
                "eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", date='" + date + '\'' +
                ", location='" + location + '\'' +
                ", capacity=" + capacity +
                ", freeEntrance=" + freeEntrance +
                ", library which hosts the event =" + libraryId +
                '}';
    }

}
