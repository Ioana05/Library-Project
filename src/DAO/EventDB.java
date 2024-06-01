package DAO;

import DataBase.DatabaseConnection;
import com.myproject.service.Audit;
import model.Event;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class EventDB {

    // in fiecare clasa in care lucram cu baza de date , vom avea nevoie de conexiune
    Connection myConnection;

    private  final Audit auditService = Audit.getInstance();
    public EventDB(Connection newconn){
        this.myConnection = newconn;
    }

    // void pentru ca adaugam direct in baza de date
    public void createEvent(Event newEvent) {
        try {
            String createReaderSQL = "INSERT INTO event VALUES(?,?,?,?,?,?,?,?);";
            PreparedStatement prepstmt = myConnection.prepareStatement(createReaderSQL);

            prepstmt.setInt(1, newEvent.getEventId());
            prepstmt.setInt(2, newEvent.getLibraryId());
            prepstmt.setString(3, newEvent.getEventName());
            prepstmt.setString(4, newEvent.getEventDescription());
            prepstmt.setDate(5, Date.valueOf(newEvent.getDate()));
            prepstmt.setString(6, newEvent.getLocation());
            prepstmt.setInt(7, newEvent.getCapacity());
            prepstmt.setBoolean(8, newEvent.isFreeEntrance());

            auditService.Write("createEvent");
            prepstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public List<Event> readEvents(){
        List<Event> events = new ArrayList<>();
        try {
            Statement stmt = myConnection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM event");
            while(result.next()){
                Event currentEvent = new Event(result);
                events.add(currentEvent);
            }
            auditService.Write("readEvents");
            stmt.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return events;
    }

    public void setUpdatedEvent(Event updatedEvent) {
        try {
            String updateSQL = "UPDATE event SET libraryID= ?, eventName = ?, eventDescription = ?, localDate = ?, location = ?, capacity = ?, freeEntrance = ? where eventID = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(updateSQL);
            preparedStatement.setInt(1, updatedEvent.getLibraryId());
            preparedStatement.setString(2, updatedEvent.getEventName());
            preparedStatement.setString(3, updatedEvent.getEventDescription());
            preparedStatement.setDate(4, Date.valueOf(updatedEvent.getDate()));
            preparedStatement.setString(5, updatedEvent.getLocation());
            preparedStatement.setInt(6, updatedEvent.getCapacity());
            preparedStatement.setBoolean(7, updatedEvent.isFreeEntrance());
            preparedStatement.setInt(8, updatedEvent.getEventId());
            preparedStatement.executeUpdate();
            auditService.Write("updateEvent");
            preparedStatement.close();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void deleteEvent(Event deletedEvent) {
        try {
            String deleteSQL = "DELETE FROM event WHERE eventID = ? ";
            PreparedStatement preparedStatement = myConnection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, deletedEvent.getEventId());
            preparedStatement.executeUpdate();
            auditService.Write("deleteEvent");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
