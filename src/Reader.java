import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

public class Reader implements ReadWrite{
    private String name;
    protected String userId;
    private String email;
    private String membExpDate;
    protected List<Book> booksBorrowed;
    protected Set<Book> readingHistory;

    Reader(String name, String userId, String email, String membExpDate, List<String> booksBorrowed, List<String> readingHistory){
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.membExpDate = membExpDate;
        this.booksBorrowed = new ArrayList<Book>();
        this.readingHistory = new HashSet<Book>();
    }

    Reader(){
        this.name = "";
        this.userId = "";
        this.email = "";
        this.membExpDate = "";
        this.booksBorrowed = new ArrayList<Book>();
        this.readingHistory = new HashSet<Book>();
    }
    public String getName() {
        return name;
    }

    public List<Book> getBooksBorrowed() {
        return booksBorrowed;
    }

    public String getUserId() {
        return userId;
    }

    public void readData(Scanner scanner) {
        System.out.print("Enter name: ");
        this.name = scanner.nextLine();

        System.out.print("Enter user ID: ");
        this.userId = scanner.nextLine();

        System.out.print("Enter email: ");
        this.email = scanner.nextLine(); // Consume the newline character
        this.email = scanner.nextLine();

        System.out.print("Enter membership expiration date: ");
        this.membExpDate = scanner.nextLine();

//        // Read books borrowed
//        System.out.print("Enter number of books borrowed: ");
//        int numBooksBorrowed = scanner.nextInt();
//        this.booksBorrowed = new ArrayList<>();
//        scanner.nextLine(); // Consume the newline character
//        for (int i = 0; i < numBooksBorrowed; i++) {
//            Book book = new Book();
//            book.readData(scanner);
//            this.booksBorrowed.add(book);
//        }
//
//        // Read reading history
//        int numReadingHistory = scanner.nextInt();
//        this.readingHistory = new HashSet<Book>() {};
//        scanner.nextLine(); // Consume the newline character
//        for (int i = 0; i < numReadingHistory; i++) {
//            Book book = new Book();
//            book.readData(scanner);
//            this.readingHistory.add(book);
//        }
    }

    @Override
    public String toString() {
        return "Reader{" +
                "name='" + name + '\'' +
                ", userId='" + userId + '\'' +
                ", email='" + email + '\'' +
                ", membExpDate='" + membExpDate + '\'' +
                ", booksBorrowed=" + booksBorrowed +
                ", readingHistory=" + readingHistory +
                '}';
    }
}
