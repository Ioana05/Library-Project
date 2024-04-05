import java.util.Scanner;

class Book implements ReadWrite{
    private String title;
    private String author;
    private int publicationYear;
    private int noOfPieces;
    private String ISBN;
    private float price;
    private String category;

    public Book(String title, String author, int publicationYear, int NoOfPieces ,String ISBN, float price, String category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.noOfPieces = NoOfPieces;
        this.ISBN = ISBN;
        this.price = price;
        this.category = category;
    }

    public Book(){
        this.title = "";
        this.author = "";
        this.publicationYear = 0;
        this.noOfPieces = 0;
        this.ISBN = "";
        this.price = 0;
        this.category = "";

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

    public int getNoOfPieces() {
        return noOfPieces;
    }

    public String getISBN() {
        return (ISBN);
    }

    public float getPrice() {
        return price;
    }

    //////////////// Setteri  ////////////////

    public void setNoOfPieces(int noOfPieces) {
        this.noOfPieces = noOfPieces;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void readData(Scanner scanner) {
        System.out.print("Enter title: ");
        this.title = scanner.nextLine();

        System.out.print("Enter author: ");
        this.author = scanner.nextLine();

        System.out.print("Enter category: ");
        this.category = scanner.nextLine();

        System.out.print("Enter publication year: ");
        this.publicationYear = scanner.nextInt();

        System.out.print("Enter number of pieces: ");
        this.noOfPieces = scanner.nextInt();

        System.out.print("Enter ISBN: ");
        this.ISBN = scanner.nextLine();

        System.out.print("Enter price: ");
        this.price = scanner.nextFloat();

    }


    @Override
    public String toString() {
        return "Book {" +
                "title ='" + title + '\'' +
                ", author ='" + author + '\'' +
                ", publication year =" + publicationYear +
                ", ISBN = '" + ISBN + '\'' +
                ", price = " + price +
                '}';
    }
}