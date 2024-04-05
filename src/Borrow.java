import java.util.Scanner;

public class Borrow implements ReadWrite{
    String userID;
    String borrowedBook;
    String borrowedDate;
    String dueDate;
    Boolean dueDateRespected;

    public Borrow(String userID, String borrowedBook, String borrowedDate, String dueDate, Boolean dueDateRespected) {
        this.userID = userID;
        this.borrowedBook =borrowedBook;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.dueDateRespected = dueDateRespected;
    }

    public Borrow(){
        this.userID = "";
        this.borrowedBook ="";
        this.borrowedDate = "";
        this.dueDate = "";
        this.dueDateRespected = true;
    }

    @Override
    public void readData(Scanner scanner) {
        System.out.print("Enter user ID: ");
        this.userID = scanner.nextLine();

        System.out.print("Enter borrowed book: ");
        this.borrowedBook = scanner.nextLine();

        System.out.print("Enter borrowed date: ");
        this.borrowedDate = scanner.nextLine();

        System.out.print("Enter due date: ");
        this.dueDate = scanner.nextLine();

        System.out.print("Is due date respected? (true/false): ");
        this.dueDateRespected = scanner.nextBoolean();
    }

    //////// GETTERI /////////////////


    public String getUserID() {
        return userID;
    }

    public String getBorrowedBook() {
        return borrowedBook;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "ID of the reader who borrowed ='" + userID + '\'' +
                ", Borrowed Book='" + borrowedBook + '\'' +
                ", Borrowed Date='" + borrowedDate + '\'' +
                ", Due Date='" + dueDate + '\'' +
                ", Due Date Respected=" + dueDateRespected +
                '}';
    }
}


