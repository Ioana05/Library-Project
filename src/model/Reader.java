package model;

import model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Reader implements ReadWrite {
    public String firstName;

    public String lastName;
    protected String userId;
    private String email;
    private LocalDate membExpDate;
    protected List<Book> booksBorrowed;
    protected Set<Book> readingHistory;

    Reader(String firstName,String lastName, String userId, String email, LocalDate membExpDate, List<String> booksBorrowed, List<String> readingHistory){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.email = email;
        this.membExpDate = membExpDate;
        this.booksBorrowed = new ArrayList<Book>();
        this.readingHistory = new HashSet<Book>();
    }

    public Reader(){
        this.firstName = "";
        this.lastName = "";
        this.userId = "";
        this.email = "";
        this.membExpDate = LocalDate.now();
        this.booksBorrowed = new ArrayList<Book>();
        this.readingHistory = new HashSet<Book>();
    }

    public Reader(ResultSet result) throws SQLException {
            this.userId = result.getString("userID");
            this.firstName = result.getString("firstName");
            this.lastName = result.getString("lastName");
            this.email = result.getString("email");
            this.membExpDate = result.getDate("membExpDate").toLocalDate();

    }

    public String getfirstName() {
        return firstName;
    }

    public String getEmail() { return email;}

    public LocalDate getMembExpDate() { return membExpDate;}

    public String getlastName(){ return lastName; }

    public List<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    public String getUserId() {
        return userId;
    }

    public void readData(Scanner scanner) {
        System.out.print("Enter first name: ");
        this.firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        this.lastName = scanner.nextLine();

        System.out.print("Enter user ID: ");
        this.userId = scanner.nextLine();

        System.out.print("Enter email: ");
        this.email = scanner.nextLine(); // Consume the newline character

        System.out.print("Enter membership expiration date: ");
        this.membExpDate = LocalDate.parse(scanner.nextLine());

//        // Read books borrowed
//        System.out.print("Enter number of books borrowed: ");
//        int numBooksBorrowed = scanner.nextInt();
//        this.booksBorrowed = new ArrayList<>();
//        scanner.nextLine(); // Consume the newline character
//        for (int i = 0; i < numBooksBorrowed; i++) {
//            model.Book book = new model.Book();
//            book.readData(scanner);
//            this.booksBorrowed.add(book);
//        }
//
//        // Read reading history
//        int numReadingHistory = scanner.nextInt();
//        this.readingHistory = new HashSet<model.Book>() {};
//        scanner.nextLine(); // Consume the newline character
//        for (int i = 0; i < numReadingHistory; i++) {
//            model.Book book = new model.Book();
//            book.readData(scanner);
//            this.readingHistory.add(book);
//        }
    }

    @Override
    public String toString() {
        return "model.Reader{" +
                "firstName='" + firstName + '\'' +
                "lastName='" + lastName + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", membExpDate='" + membExpDate + '\'' +
                ", booksBorrowed=" + booksBorrowed +
                ", readingHistory=" + readingHistory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return Objects.equals(firstName, reader.firstName) && Objects.equals(lastName, reader.lastName) && Objects.equals(userId, reader.userId) && Objects.equals(email, reader.email) && Objects.equals(membExpDate, reader.membExpDate) && Objects.equals(booksBorrowed, reader.booksBorrowed) && Objects.equals(readingHistory, reader.readingHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userId, email, membExpDate, booksBorrowed, readingHistory);
    }
}
