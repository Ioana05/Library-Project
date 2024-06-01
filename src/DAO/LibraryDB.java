package DAO;

import DAO.GenericReadDAO;
import com.myproject.service.Audit;
import model.Library;

import java.sql.*;

public class LibraryDB {

    // in fiecare clasa in care lucram cu baza de date , vom avea nevoie de conexiune
    Connection myConnection;

    private  final Audit auditService = Audit.getInstance();

    GenericReadDAO genericRead = GenericReadDAO.getInstance();

    public LibraryDB(Connection newconn){
        this.myConnection = newconn;
    }

    // void pentru ca adaugam direct in baza de date
    public void createLibrary(Library newLibrary) {
        try {
            String createLibraryInsert = "INSERT INTO library VALUES(?,?,?,?,?,?);";
            PreparedStatement prepstmt = myConnection.prepareStatement(createLibraryInsert);


            // vezi daca merge aici si nu trebuie facuta si conversie
            prepstmt.setInt(1,newLibrary.getLibraryID());
            prepstmt.setString(2, newLibrary.getName());
            prepstmt.setString(3, newLibrary.getAddress());
            prepstmt.setString(4, newLibrary.getCity());
            prepstmt.setString(5, newLibrary.getPhoneNumber());
            prepstmt.setString(6, newLibrary.getProgram());

            auditService.Write("createLibrary");
            prepstmt.executeUpdate();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void readAllLibraries() {
        genericRead.readAll(myConnection, "SELECT * FROM library");
        auditService.Write("readLibraries");

    }

    public void readLibrariesByID(int id) {
        genericRead.readByID(myConnection, "SELECT * FROM library WHERE libraryID = ?", id);
        auditService.Write("readLibrariesbyID");

    }

    public void updateLibrary(Library updatedLibrary) {
        try {
            String updateBook = "UPDATE library SET name = ?, address = ?, city = ?, phoneNumber = ?, schedule = ? where libraryID = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(updateBook);

            preparedStatement.setString(1, updatedLibrary.getName());
            preparedStatement.setString(2, updatedLibrary.getAddress());
            preparedStatement.setString(3, updatedLibrary.getCity());
            preparedStatement.setString(4, updatedLibrary.getPhoneNumber());
            preparedStatement.setString(5, updatedLibrary.getProgram());

            preparedStatement.executeUpdate();
            auditService.Write("updateLibrary");
            preparedStatement.close();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void deleteLibrary(Library deletedLibrary) {
        try {
            String deleteSQL = "DELETE FROM Library WHERE libraryID = ? ";
            PreparedStatement preparedStatement = myConnection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, deletedLibrary.getLibraryID());
            preparedStatement.executeUpdate();
            auditService.Write("deleteLibrary");

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
