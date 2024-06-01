package DAO;

import DataBase.DatabaseConnection;
import com.myproject.service.Audit;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class CategoryDB {

    // in fiecare clasa in care lucram cu baza de date , vom avea nevoie de conexiune
    Connection myConnection;
    private  final Audit auditService = Audit.getInstance();

    public CategoryDB(Connection newconn){
        this.myConnection = newconn;
    }

    // void pentru ca adaugam direct in baza de date
    public void createCategory(Category newAddedCategory) {
        try {
            String createBook = "INSERT INTO category VALUES(?,?,?);";
            PreparedStatement prepstmt = myConnection.prepareStatement(createBook);

            // vezi daca merge aici si nu trebuie facuta si conversie
            prepstmt.setInt(1, newAddedCategory.getCategoryID());
            prepstmt.setString(2, newAddedCategory.getCategoryName());
            prepstmt.setInt(3, newAddedCategory.getAgeLimit());

            auditService.Write("createCategory");
            prepstmt.executeUpdate();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public List<Category> readCategory(){
        List<Category> categories = new ArrayList<>();
        try {
            Statement stmt = myConnection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM Category");
            while(result.next()){
                Category currentCategory = new Category(result);
                categories.add(currentCategory);
            }
            auditService.Write("readCategory");
            stmt.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return categories;
    }

    public void updateCategory(Category updatedCategory) {
        try {
            String updateCategory = "UPDATE Category SET categoryName = ?, ageLimit = ? where categoryId = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(updateCategory);
            preparedStatement.setString(1, updatedCategory.getCategoryName());
            preparedStatement.setInt(2, updatedCategory.getAgeLimit());
            preparedStatement.setInt(3, updatedCategory.getCategoryID());
            preparedStatement.executeUpdate();
            auditService.Write("updateCategory");
            preparedStatement.close();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void deleteCategory(Category deletedCategory) {
        try {
            String deleteSQL = "DELETE FROM Category WHERE categoryId = ? ";
            PreparedStatement preparedStatement = myConnection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, deletedCategory.getCategoryID());
            auditService.Write("updateCategory");
            preparedStatement.executeUpdate();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }


}
