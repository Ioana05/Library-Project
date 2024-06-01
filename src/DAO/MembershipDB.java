package DAO;

import DAO.GenericReadDAO;
import com.myproject.service.Audit;
import model.Membership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class MembershipDB {

    // in fiecare clasa in care lucram cu baza de date , vom avea nevoie de conexiune
    Connection myConnection;
    GenericReadDAO genericRead = GenericReadDAO.getInstance();

    private  final Audit auditService = Audit.getInstance();

    public MembershipDB(Connection newconn){
        this.myConnection = newconn;
    }

    // void pentru ca adaugam direct in baza de date
    public void createMembership(Membership newAddedMembership) {
        try {
            String createBook = "INSERT INTO membership VALUES(?,?);";
            PreparedStatement prepstmt = myConnection.prepareStatement(createBook);

            prepstmt.setInt(1, newAddedMembership.getLibraryID());
            prepstmt.setInt(2, newAddedMembership.getUserID());

            prepstmt.executeUpdate();
            auditService.Write("createMembership");

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public List<Membership> readMembership(){
        List<Membership> books = new ArrayList<>();
        try {
            Statement stmt = myConnection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM Membership");
            while(result.next()){
                Membership currentMembership = new Membership(result);
                books.add(currentMembership);
            }
            auditService.Write("readMembership");
            stmt.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return books;
    }

    public void readAllMemberships() {
        genericRead.readAll(myConnection, "SELECT * FROM membership");
        auditService.Write("readAllMemberships");

    }

    public void readMembershipByID(int id) {
        genericRead.readByID(myConnection, "SELECT * FROM membership WHERE userID = ? and libraryID = ?", id);
        auditService.Write("readMembershipById");

    }

    public void deleteMembership(Membership deletedMembersip) {
        try {
            String deleteSQL = "DELETE FROM membership WHERE libraryID = ? and userID = ? ";
            PreparedStatement preparedStatement = myConnection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, deletedMembersip.getLibraryID());
            preparedStatement.setInt(2, deletedMembersip.getUserID());
            preparedStatement.executeUpdate();
            auditService.Write("deleteMembership");

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
