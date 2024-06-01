package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Borrow implements ReadWrite {
    String userID;
    String borrowedBook;
    LocalDate borrowedDate;
    LocalDate dueDate;
    Boolean dueDateRespected;

    public Borrow(String userID, String bookID, String borrowedBook, LocalDate borrowedDate, LocalDate dueDate, Boolean dueDateRespected) {
        this.userID = userID;
        this.borrowedBook =borrowedBook;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.dueDateRespected = dueDateRespected;
    }

    public Borrow(){
        this.userID = "";
        this.borrowedBook ="";
        this.borrowedDate = LocalDate.now();
        this.dueDate = LocalDate.now();
        this.dueDateRespected = true;
    }

    public Borrow(ResultSet result) throws SQLException {
        this.userID = result.getString("userID");
        this.borrowedBook = result.getString("bookID");
        this.borrowedDate = result.getDate("borrowedDate").toLocalDate();
        this.dueDate = result.getDate("dueDate").toLocalDate();
        this.dueDateRespected = result.getBoolean("dueDateRespected");

    }

    @Override
    public void readData(Scanner scanner) {
        System.out.print("Enter user ID: ");
        this.userID = scanner.nextLine();

        System.out.print("Enter borrowed book: ");
        this.borrowedBook = scanner.nextLine();

        System.out.print("Enter borrowed date: ");
        this.borrowedDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter due date: ");
        this.dueDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Is due date respected? (true/false): ");
        this.dueDateRespected = scanner.nextBoolean();
    }

    //////// GETTERI /////////////////


    public String getUserID() {
        return userID;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public String getBorrowedBook() {
        return borrowedBook;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Boolean getDueDateRespected() {
        return dueDateRespected;
    }

    @Override
    public String toString() {
        return "model.Borrow{" +
                "ID of the reader who borrowed ='" + userID + '\'' +
                ", Borrowed model.Book='" + borrowedBook + '\'' +
                ", Borrowed Date='" + borrowedDate + '\'' +
                ", Due Date='" + dueDate + '\'' +
                ", Due Date Respected=" + dueDateRespected +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrow borrow = (Borrow) o;
        return Objects.equals(userID, borrow.userID) && Objects.equals(borrowedBook, borrow.borrowedBook) && Objects.equals(borrowedDate, borrow.borrowedDate) && Objects.equals(dueDate, borrow.dueDate) && Objects.equals(dueDateRespected, borrow.dueDateRespected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, borrowedBook, borrowedDate, dueDate, dueDateRespected);
    }
}


