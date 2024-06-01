package DAO;

import DAO.EmployeeDB;
import DAO.GenericReadDAO;
import com.myproject.service.Audit;
import model.Librarian;

import java.sql.*;

public class LibrarianDB {


    // in fiecare clasa in care lucram cu baza de date , vom avea nevoie de conexiune
    Connection myConnection;
    GenericReadDAO genericRead = GenericReadDAO.getInstance();

    private  final Audit auditService = Audit.getInstance();

    EmployeeDB employeeDB;

    public LibrarianDB(Connection newconn){
        this.myConnection = newconn;
        this.employeeDB = new EmployeeDB(this.myConnection);
    }

    // void pentru ca adaugam direct in baza de date
    public void createLibrarian(Librarian newLibrarian) {
        try {
            // creare in tabela angajat
            employeeDB.createEmployee(newLibrarian);

            String createLibrarian = "INSERT INTO librarian VALUES(?,?,?,?);";
            PreparedStatement prepstmt = myConnection.prepareStatement(createLibrarian);

            // vezi daca merge aici si nu trebuie facuta si conversie
            prepstmt.setInt(1, newLibrarian.getCNP());
            prepstmt.setInt(2, newLibrarian.getNoOfWorkedDays());
            prepstmt.setInt(3, newLibrarian.getNoOfExtraWorkingHours());
            prepstmt.setFloat(4, newLibrarian.getBonus());

            auditService.Write("createLibrarian");
            prepstmt.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    public void readAllLibrarians() {
        genericRead.readAll(myConnection, "SELECT * FROM librarian JOIN employee ON employee.CNP = librarian.CNP");
        auditService.Write("readLibrarians");

    }

    public void readLibrarianByID(int id) {
        genericRead.readByID(myConnection, "SELECT * FROM librarian JOIN employee ON employee.CNP = librarian.CNP WHERE CNP = ?", id);
        auditService.Write("readLibrarianbyID");

    }

    public void updateLibraryDirector(Librarian updatedLibrarian) {
        try {
            // updatare tabela angajat
            employeeDB.updateEmployee(updatedLibrarian);

            String updateBook = "UPDATE librarian SET extraWorkingHours = ?, noOfWorkedDays = ?, bonus = ? where CNP = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(updateBook);

            preparedStatement.setInt(1, updatedLibrarian.getNoOfExtraWorkingHours());
            preparedStatement.setFloat(2, updatedLibrarian.getNoOfExtraWorkingHours());
            preparedStatement.setInt(3, updatedLibrarian.getBonus());
            preparedStatement.setInt(4, updatedLibrarian.getCNP());

            preparedStatement.executeUpdate();
            auditService.Write("updateLibrarian");
            preparedStatement.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void deleteLibrarian(Librarian deletedLibrarian) {
        employeeDB.deleteEmployee(deletedLibrarian);
        auditService.Write("deleteLibrarian");

    }
}
