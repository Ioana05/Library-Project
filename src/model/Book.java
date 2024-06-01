package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.System.in;

public class Book implements ReadWrite {
    private String title;
    private String author;
    private int publicationYear;
    private String ISBN;
    private float price;
    private String category;
    private int libraryID;

    public Book(String title, String author, int publicationYear ,String ISBN, float price, String category, int libraryID) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.ISBN = ISBN;
        this.price = price;
        this.category = category;
        this.libraryID = libraryID;
    }

    public Book(){
        this.title = "";
        this.author = "";
        this.publicationYear = 0;
        this.ISBN = "";
        this.price = 0;
        this.category = "";
        this.libraryID = 0;

    }

    public Book(ResultSet result) throws SQLException {
        this.title = result.getString("title");
        this.ISBN = result.getString("ISBN");
        this.author = result.getString("author");
        this.publicationYear = result.getInt("publication year");
        this.price = result.getFloat("price");
        this.libraryID = result.getInt("libraryID");

    }

    //////////////// Getteri  ////////////////
    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return (ISBN);
    }

    public float getPrice() {
        return price;
    }

    public int getLibraryID() {
        return libraryID;
    }

    public String getCategory() { return category;}

    //////////////// Setteri  ////////////////

    public void setPrice(float price) {
        this.price = price;
    }

    public void readData(Scanner scanner) {
        System.out.print("Enter title: ");
        this.title = scanner.nextLine();

        System.out.print("Enter author: ");
        this.author = scanner.nextLine();

        System.out.print("Enter publication year: ");
        this.publicationYear = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter ISBN: ");
        this.ISBN = scanner.nextLine();

        System.out.print("Enter price: ");
        this.price = scanner.nextFloat();

        System.out.print("Enter libraryID: ");
        this.libraryID = scanner.nextInt();


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publicationYear == book.publicationYear && Float.compare(price, book.price) == 0 && libraryID == book.libraryID && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(ISBN, book.ISBN) && Objects.equals(category, book.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, publicationYear, ISBN, price, category, libraryID);
    }

    @Override
    public String toString() {
        return "model.Book {" +
                "title ='" + title + '\'' +
                ", author ='" + author + '\'' +
                ", publication year =" + publicationYear + '\'' +
                ", ISBN = '" + ISBN + '\'' +
                ", price = " + price + '\'' +
                ", libraryID = " + libraryID + '\'' +
                '}';
    }
}