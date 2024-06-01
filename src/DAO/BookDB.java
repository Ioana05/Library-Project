package DAO;
import model.Book;

import DataBase.DatabaseConnection;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.myproject.service.Audit;

public class BookDB {

    // in fiecare clasa in care lucram cu baza de date , vom avea nevoie de conexiune
    Connection myConnection;
    private  final Audit auditService = Audit.getInstance();

    public BookDB(Connection newconn){
        this.myConnection = newconn;
    }

    // void pentru ca adaugam direct in baza de date
    public void createBook(Book newAddedBook) {
        try {
            String createBook = "INSERT INTO book VALUES(?,?,?,?,?,?)";
            PreparedStatement prepstmt = myConnection.prepareStatement(createBook);

            // vezi daca merge aici si nu trebuie facuta si conversie
            prepstmt.setString(1, newAddedBook.getISBN());
            prepstmt.setString(2, newAddedBook.getTitle());
            prepstmt.setString(3, newAddedBook.getAuthor());
            prepstmt.setInt(4, newAddedBook.getPublicationYear());
            prepstmt.setFloat(5, newAddedBook.getPrice());
            prepstmt.setInt(6, newAddedBook.getLibraryID());

            prepstmt.executeUpdate();
            auditService.Write("createBook");
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }


    public List<Book> readBook(){
        List<Book> books = new ArrayList<>();
        try {
            Statement stmt = myConnection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT * FROM Book");
            while(result.next()){
                Book currentBook = new Book(result);
                books.add(currentBook);
            }
            auditService.Write("readBook");
            stmt.close();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return books;
    }

    public void updateBook(Book updatedBook) {
        try {
            String updateBook = "UPDATE Book SET title = ?, author = ?, publicationyear = ?, price = ?, libraryID = ? where id = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(updateBook);
            preparedStatement.setString(1, updatedBook.getTitle());
            preparedStatement.setString(2, updatedBook.getAuthor());
            preparedStatement.setInt(3, updatedBook.getPublicationYear());
            preparedStatement.setFloat(4, updatedBook.getPrice());
            preparedStatement.setInt(5, updatedBook.getLibraryID());
            preparedStatement.setString(6, updatedBook.getISBN());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            auditService.Write("updateBook");

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    public void deleteBook(Book deletedBook) {
        try {
            String deleteSQL = "DELETE FROM Book WHERE id = ? ";
            PreparedStatement preparedStatement = myConnection.prepareStatement(deleteSQL);
            preparedStatement.setString(1, deletedBook.getISBN());
            preparedStatement.executeUpdate();
            auditService.Write("deleteBook");

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    //
    public int getNumberOfBook(String name){
        try{
            int count = 0;
            String searchSQL = "SELECT COUNT(*) FROM book where upper(title) = ?";
            PreparedStatement preparedStatement = myConnection.prepareStatement(searchSQL);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);  // Retrieve the count from the result set
            }
            return  count;
        }
        catch (Exception e){
            System.out.println(e.toString());
            return 0;
        }
    }
}
