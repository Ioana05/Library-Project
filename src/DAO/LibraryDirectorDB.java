package DAO;

import DAO.EmployeeDB;
import DAO.GenericReadDAO;
import com.myproject.service.Audit;
import model.LibraryDirector;

import java.sql.*;

public class LibraryDirectorDB {

    // in fiecare clasa in care lucram cu baza de date , vom avea nevoie de conexiune
    Connection myConnection;
    GenericReadDAO genericRead = GenericReadDAO.getInstance();
    private  final Audit auditService = Audit.getInstance();

    EmployeeDB employeeDB;

    public LibraryDirectorDB(Connection newconn){
        this.myConnection = newconn;
        this.employeeDB = new EmployeeDB(this.myConnection);
    }

    // void pentru ca adaugam direct in baza de date
    public void createLibraryDirector(LibraryDirector newLibraryDirector) {
        try {
            // creare in tabela angajat
            employeeDB.createEmployee(newLibraryDirector);

            String createLibraryDirector = "INSERT INTO librarydirector VALUES(?,?,?);";
            PreparedStatement prepstmt = myConnection.prepareStatement(createLibraryDirector);

            System.out.println("facem un mic debug" + newLibraryDirector.getCNP());

            // vezi daca merge aici si nu trebuie facuta si conversie
            prepstmt.setInt(1, newLibraryDirector.getCNP());
            prepstmt.setInt(2, newLibraryDirector.getExperience());
            prepstmt.setFloat(3, newLibraryDirector.getSalaryIncreases());

            prepstmt.executeUpdate();
            auditService.Write("createLibraryDirector");

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void readAllLibraryDirectors() {
        genericRead.readAll(myConnection, "SELECT * FROM librarydirector JOIN employee ON employee.CNP = library_director.CNP");
        auditService.Write("readLibraryDirectors");

    }

    public void readLibraryDirectorByID(int id) {
        genericRead.readByID(myConnection, "SELECT * FROM librarydirector JOIN employee ON employee.CNP = library_director.CNP WHERE CNP = ?", id);
        auditService.Write("readDirectorsByID");

    }

    public void updateLibraryDirector(LibraryDirector updatedLibraryDirector) {
        // updatare tabela angajat
        employeeDB.updateEmployee(updatedLibraryDirector);

        try {
            String updateBook = "UPDATE librarydirector SET experience = ?, salaryIncreases = ? where CNP = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(updateBook);

            preparedStatement.setInt(1, updatedLibraryDirector.getExperience());
            preparedStatement.setFloat(2, updatedLibraryDirector.getSalaryIncreases());
            preparedStatement.setInt(3, updatedLibraryDirector.getCNP());

            preparedStatement.executeUpdate();
            auditService.Write("updateLibraryDirector");
            preparedStatement.close();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void deleteLibraryDirector(LibraryDirector deletedLibraryDirector) {
        employeeDB.deleteEmployee(deletedLibraryDirector);
        auditService.Write("deleteLibraryDirector");

    }
}
