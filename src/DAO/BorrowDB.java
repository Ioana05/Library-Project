package DAO;

import DataBase.DatabaseConnection;
import com.myproject.service.Audit;
import model.Borrow;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.sql.Date;

public class BorrowDB {

    // in fiecare clasa in care lucram cu baza de date , vom avea nevoie de conexiune
    Connection myConnection;
    private Audit auditService = Audit.getInstance();

    public BorrowDB(Connection newconn){
        this.myConnection = newconn;
    }

    // void pentru ca adaugam direct in baza de date
    public void createBorrow(Borrow newAddedBorrow) {
        try {
            if (!checkBorrow(newAddedBorrow.getBorrowedBook()))
                System.out.println("Cartea ceruta este deja imprumutata!");

            else{
                String createBorrow = "INSERT INTO borrow VALUES(?,?,?,?,?)";
                PreparedStatement prepstmt = myConnection.prepareStatement(createBorrow);

                // vezi daca merge aici si nu trebuie facuta si conversie
                prepstmt.setString(1, newAddedBorrow.getUserID());
                prepstmt.setString(2, newAddedBorrow.getBorrowedBook());
                // aici avem nevoie sa convertim data din LocalDate in Date
                Date brwDate = Date.valueOf(newAddedBorrow.getBorrowedDate());
                prepstmt.setDate(3, brwDate);

                Date dueDate = Date.valueOf(newAddedBorrow.getDueDate());
                prepstmt.setDate(4, dueDate);

                prepstmt.setBoolean(5, newAddedBorrow.getDueDateRespected());
                auditService.Write("createBorrow");

                prepstmt.executeUpdate();
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }

    }
    public List<Borrow> readBorrow(){
        List<Borrow> books = new ArrayList<>();
        try {
            Statement stmt = myConnection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM Borrow");
            while(result.next()){
                Borrow currentBorrow = new Borrow(result);
                books.add(currentBorrow);
            }
            auditService.Write("readBorrow");
            stmt.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return books;
    }

    public void updateBoroow(Borrow updatedBorrow) {
        try {
            String updateBorrow = "UPDATE Borrow SET borrowedDate = ?, dueDate = ?, dueDateRespected = ? where userID = ? and bookID = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(updateBorrow);
            Date brwDate = Date.valueOf(updatedBorrow.getBorrowedDate());
            preparedStatement.setDate(1, brwDate);

            Date dueDate = Date.valueOf(updatedBorrow.getDueDate());
            preparedStatement.setDate(2, dueDate);


            preparedStatement.setBoolean(3, updatedBorrow.getDueDateRespected());
            preparedStatement.setString(4, updatedBorrow.getUserID());
            preparedStatement.setString(5, updatedBorrow.getBorrowedBook());
            preparedStatement.executeUpdate();
            auditService.Write("updateBorrow");
            preparedStatement.close();

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void deleteBorrow(String userID, String bookID) {
        try {
            String deleteSQL = "DELETE FROM Borrow WHERE userID = ? and bookID = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, bookID);
            preparedStatement.executeUpdate();
            auditService.Write("deleteBorrow");
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public Boolean checkBorrow(String ISBN)
    {
        try{
            Boolean result = false;
            String check = "SELECT * FROM Borrow WHERE bookID = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(check);
            preparedStatement.setString(1, ISBN);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
            return  true;
        }
       catch(Exception e){
            System.out.println(e.toString());
            return false;
       }


    }


}
