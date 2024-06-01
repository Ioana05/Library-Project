package DAO;

import DataBase.DatabaseConnection;
import com.myproject.service.Audit;
import model.Employee;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDB {

    // in fiecare clasa in care lucram cu baza de date , vom avea nevoie de conexiune
    Connection myConnection;
    private final Audit auditService = Audit.getInstance();

    public EmployeeDB(Connection newconn){
        this.myConnection = newconn;
    }

    // void pentru ca adaugam direct in baza de date
    public void createEmployee(Employee newAddedEmployee) {
        try {
            String createEmployee = "INSERT INTO Employee VALUES(?,?,?,?,?,?,?);";
            PreparedStatement prepstmt = myConnection.prepareStatement(createEmployee);

            // vezi daca merge aici si nu trebuie facuta si conversie
            prepstmt.setString(2, newAddedEmployee.getFirstName());
            prepstmt.setString(3, newAddedEmployee.getLastName());
            prepstmt.setInt(1, newAddedEmployee.getCNP());
            prepstmt.setString(4, newAddedEmployee.getPhoneNumber());

            // a trebuit sa fac cast la date pt ca returna un tip de Date dintr-un pachet diferit
            Date dateOfHire = Date.valueOf(newAddedEmployee.getDateOfHire());
            prepstmt.setDate(5, dateOfHire);

            prepstmt.setFloat(6, newAddedEmployee.getBaseSalary());
            prepstmt.setInt(7, newAddedEmployee.getLibraryID());

            auditService.Write("createEmployee");
            prepstmt.executeUpdate();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    // nu o sa am nevoie de metoda READ pt Employee simplu pentru ca nu
    // nu pot instantia obliecte simple de tip Employee(e clasa abstracta)

    public void updateEmployee(Employee updatedEmployee) {
        try {
            String updateEmployee = "UPDATE Employee SET firstName = ?, lastName = ?, phoneNumber = ?, dateOfHire = ?, baseSalary = ?, libraryID = ? where CNP = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(updateEmployee);
            preparedStatement.setString(1, updatedEmployee.getFirstName());
            preparedStatement.setString(2, updatedEmployee.getLastName());
            preparedStatement.setString(3, updatedEmployee.getPhoneNumber());
            Date dateOfHire = Date.valueOf(updatedEmployee.getDateOfHire());
            preparedStatement.setDate(4, dateOfHire);
            preparedStatement.setFloat(5, updatedEmployee.getBaseSalary());
            preparedStatement.setInt(6, updatedEmployee.getLibraryID());
            preparedStatement.executeUpdate();
            auditService.Write("updateEmployee");
            preparedStatement.close();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void deleteEmployee(Employee deletedEmployee) {
        try {
            String deleteSQL = "DELETE FROM Employee WHERE CNP = ? ";
            PreparedStatement preparedStatement = myConnection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, deletedEmployee.getCNP());
            preparedStatement.executeUpdate();
            auditService.Write("deleteEmployee");
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
