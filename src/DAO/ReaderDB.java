package DAO;

import DataBase.DatabaseConnection;
import com.myproject.service.Audit;
import model.Reader;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
public class ReaderDB {

    // in fiecare clasa in care lucram cu baza de date , vom avea nevoie de conexiune
    Connection myConnection;
    public ReaderDB(Connection newconn){
        this.myConnection = newconn;
    }

    private  final Audit auditService = Audit.getInstance();


    // void pentru ca adaugam direct in baza de date
    public void createReader(Reader newReader) {
        try {
            String createReaderSQL = "INSERT INTO reader VALUES(?,?,?,?,?);";
            PreparedStatement prepstmt = myConnection.prepareStatement(createReaderSQL);

            prepstmt.setString(1, newReader.getUserId());
            prepstmt.setString(2, newReader.getfirstName());
            prepstmt.setString(3, newReader.getlastName());
            prepstmt.setString(4, newReader.getEmail());
            Date brwDate = Date.valueOf(newReader.getMembExpDate());
            prepstmt.setDate(5, brwDate);

            auditService.Write("createReader");
            prepstmt.executeUpdate();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public List<Reader> readReader(){
        List<Reader> readers = new ArrayList<>();
        try {
            Statement stmt = myConnection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM reader");
            while(result.next()){
                Reader currentReader = new Reader(result);
                readers.add(currentReader);
            }
            auditService.Write("readReader");
            stmt.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return readers;
    }

    public void updateBook(Reader updatedReader) {
        try {
            String updateSQL = "UPDATE Reader SET firstName= ?, lastName = ?, email = ?, membExpDate = ? where userID = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(updateSQL);
            preparedStatement.setString(1, updatedReader.getfirstName());
            preparedStatement.setString(2, updatedReader.getlastName());
            preparedStatement.setString(3, updatedReader.getEmail());
            // aici avem nevoie sa convertim data din LocalDate in Date
            Date brwDate = Date.valueOf(updatedReader.getMembExpDate());
            preparedStatement.setDate(4, brwDate);
            preparedStatement.setString(5, updatedReader.getUserId());
            preparedStatement.executeUpdate();
            auditService.Write("createReader");
            preparedStatement.close();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void deleteReader(Reader deletedReader) {
        try {
            String deleteSQL = "DELETE FROM Reader WHERE id = ? ";
            PreparedStatement preparedStatement = myConnection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, deletedReader.getUserId());
            preparedStatement.executeUpdate();
            auditService.Write("deleteReader");

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
