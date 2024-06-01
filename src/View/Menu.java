package View;

import DAO.*;
import model.*;
import DataBase.DatabaseConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class Menu {
    private static Menu menu;
    private DatabaseConnection databaseConnection;
    Connection myConnection;

    private Library library;
    private Scanner scanner;

    private LibraryDirector director;

    private BookDB bookDB;
    private BorrowDB borrowDB;
    private CategoryDB categoryDB;
    private EmployeeDB employeeDB;
    private EventDB eventDB;
    private LibraryDB libraryDB;
    private LibrarianDB librarianDB;
    private LibraryDirectorDB libraryDirectorDB;
    private MembershipDB membershipDB;
    private ReaderDB readerDB;

    private Menu() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
        this.director = new LibraryDirector();

        this.databaseConnection = DatabaseConnection.getInstance();
        this.myConnection = this.databaseConnection.start();

        bookDB = new BookDB(myConnection);
        borrowDB = new BorrowDB(myConnection);
        categoryDB = new CategoryDB(myConnection);
        employeeDB = new EmployeeDB(myConnection);
        eventDB = new EventDB(myConnection);
        libraryDB = new LibraryDB(myConnection);
        librarianDB  = new LibrarianDB(myConnection);
        libraryDirectorDB = new LibraryDirectorDB(myConnection);
        membershipDB = new MembershipDB(myConnection);
        readerDB = new ReaderDB(myConnection);

        this.InitiateLibrary(this.library);
    }

    public static Menu getInstance() {
        if (menu == null) {
            menu = new Menu();
        }

        return menu;
    }

    void InitiateLibrary(Library library) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! Welcome to your virtual library! First you'll have to give some details about your library :");

        System.out.println("Set an ID for your library.(it should consists of 6 digits)");
        int libID = scanner.nextInt();
        library.setLibraryID(libID);
        scanner.nextLine();

        System.out.println("What is the name of the library?");
        String libName = scanner.nextLine();
        library.setName(libName);

        System.out.println("What is the city where is located?");
        String libcity = scanner.nextLine();
        library.setCity(libcity);

        System.out.println("What is the address of the library?");
        String libadd = scanner.nextLine();
        library.setAddress(libadd);

        System.out.println("What is the phone number of the library?");
        String libnum  = scanner.nextLine();
        library.setPhoneNumber(libnum);

        System.out.println("What is the program of the library?");
        String libprogram = scanner.nextLine();
        library.setProgram(libprogram);

        this.director.readData(this.scanner);
        library.setDirector(this.director);

        // adaugam libraria in baza de date
        libraryDB.createLibrary(library);
        libraryDirectorDB.createLibraryDirector(director);

        System.out.println("Congrats! You finished the configuration of your library. Now you'll be redirected to the real menu of the library: ");
    }
    void initiateMenu() {
        int userschoice = 0;

        do {
            System.out.println("What kind of user are you?");
            System.out.println("1. Library Director");
            System.out.println("2. Librarian");
            System.out.println("3. Exit");

            userschoice = this.scanner.nextInt();
            this.scanner.nextLine();

            try {
                switch (userschoice) {
                    case 1:
                        System.out.println("Hello, director! Introduce the password:");
                        String password;
                        password = this.scanner.nextLine();
                        if (password.equals("IAMTHEBOSS"))
                            directorMenu();
                        else
                            System.out.println("The password you introduced is not correct. Are you the real director? :/");
                        break;
                    case 2:
                        System.out.println("Hello, librarian !");
                        librarianMenu();
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        closeConnection();
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
            catch(NoSuchElementException e)
            {
                System.out.println("Invalid choice. Please enter a valid option.");
            }

        } while (userschoice != 3);
    }

    private void closeConnection() {
        if (myConnection != null) {
            try {
                myConnection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void directorMenu() {
        int dirishere = 1;
        while (dirishere == 1) {
            System.out.println("Choose from the next options:");
            System.out.println("10. Add a new event:");
            System.out.println("11. See future events:");
            System.out.println("12. Delete a future event:");
            System.out.println("13. See list of librarians:");
            System.out.println("14. Add a new librarian: ");
            System.out.println("15. Delete a librarian from the list:");
            System.out.println("16. Return to the previous menu");

            int dirchoice = this.scanner.nextInt();
            this.scanner.nextLine();

            try {
                switch (dirchoice) {
                    case 10:
                        System.out.println("What event do you want to add? :");
                        addaNewEvent();
                        break;
                    case 11:
                        System.out.println("Here are the future events: ");
                        seeFutureEvents();
                        break;
                    case 12:
                        deleteFutureEvent();
                        break;
                    case 13:
                        displayLibrarians();
                        break;
                    case 14:
                        addLibrarian();
                        break;
                    case 15:
                        deleteEmployee();
                        break;
                    case 16:
                        System.out.println("Exiting...");
                        dirishere = 0;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            }
            catch(NoSuchElementException e){
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void librarianMenu() {
        int libishere = 1;
        while (libishere == 1) {
            System.out.println("Choose from the next options:");
            System.out.println("20. Add a new event:");
            System.out.println("21. See future events:");
            System.out.println("22. Delete a future event:");
            System.out.println("23. Search a book by its name:");
            System.out.println("24. Search a book by its author:");
            System.out.println("25. Search for a reader:");
            System.out.println("26. Search the available numbers of books with a given name:  ");
            System.out.println("27. Return a book:");
            System.out.println("28. Search for the due date of a book given the name of the book and name of the reader: ");
            System.out.println("29. See what books a reader has borrowed: ");
            System.out.println("30. Add a new book");
            System.out.println("31. Add a new reader");
            System.out.println("32. Add a new borrow");
            System.out.println("34. Edit a borrow ");
            System.out.println("33. Go back to the previous menu ");




            int libchoice = this.scanner.nextInt();
            this.scanner.nextLine();

            try {
                switch (libchoice) {
                    case 20:
                        System.out.println("What event do you want to add? :");
                        addaNewEvent();
                        break;
                    case 21:

                        System.out.println("Here are the future events: ");
                        seeFutureEvents();
                        break;
                    case 22:
                        deleteFutureEvent();
                        break;
                    case 23:
                        searchBookbyName();
                        break;
                    case 24:
                        searchBookbyAuthor();
                        break;
                    case 25:
                        searchforReader();
                        break;
                    case 26:
                        searchforNoofBooks();
                        break;
                    case 27:
                        returnofaBook();
                        break;
                    case 28:
                        searchforDueDate();
                        break;
                    case 29:
                        booksofaReader();
                        break;
                    case 30:
                        addaBook();
                        break;
                    case 31:
                        addaReader();
                        break;
                    case 32:
                        addaBorrow();
                        break;
                    case 33:
                        System.out.println("Exiting...");
                        libishere = 0;
                        break;
                    case 34:
                        editaBorrow();

                }
            }
            catch(NoSuchElementException e){
                System.out.println("Invalid choice. Please enter a valid option.");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void editaBorrow() {
        Borrow borrow = new Borrow();
        borrow.readData(this.scanner);
        borrowDB.updateBoroow(borrow);
        System.out.println("The borrow was modified");
    }

    private void addaBorrow() {
        Borrow borrow = new Borrow() ;
        borrow.readData(this.scanner);
        library.AddBorrowtoList(borrow);


        // adaugam cartea in lista de carti imprumutate si in baza de date
        borrowDB.createBorrow(borrow);
        //String aux = borrow.borrowedBook.toUpperCase();

//        for (int i = 0 ; i < library.getBooks().size(); i++){
//            model.Book currentBook = library.getBooks().get(i);

//            if (aux.equals(currentBook.getTitle().toUpperCase()) && currentBook.getNoOfPieces() > 0){
//                // scad numarul de exemplare din cartea pe care tocmai o imprumut
//                int NoofBooks = currentBook.getNoOfPieces();
//                currentBook.setNoOfPieces(NoofBooks - 1);
//
//                /// adaugam cartea si in lista de imprumuri a celui care a cerut-0
//                String Borrower =  borrow.getUserID();
//
//                for (int j = 0; j <library.getReaders().size(); j++)
//                {
//                    if((Borrower.equals(library.getReaders().get(j).getUserId()))) {
//                        library.getReaders().get(j).booksBorrowed.add(currentBook);
//                        library.getReaders().get(j).readingHistory.add(currentBook);
//                    }
//                }
//            }
//            else {
//                System.out.println("Cartea cautata nu este disponibila!");
//            }
//        }
    }

    private void addaReader() {
        Reader reader = new Reader();
        reader.readData(this.scanner);

        library.AddReadertoList(reader);
        // adaugam cititorul in baza de date
        readerDB.createReader(reader);
    }

    private void addaBook() throws SQLException {
        Book book = new Book() ;
        book.readData(this.scanner);

        library.AddBooktoList(book);
        // adaugam cartea in baza de date
        bookDB.createBook(book);
    }

    private void booksofaReader() {
        System.out.println("Write the ID of the reader: ");
        String ID = scanner.nextLine();

        // afisare folosind baza de date
        List <Borrow> borrows = borrowDB.readBorrow();

         System.out.print("Books borrowed by the reader: ");
         for(int j = 0; j < borrows.size(); j++) {
             if (borrows.get(j).getUserID().equals(ID))
                System.out.println(borrows.get(j).getBorrowedBook());

         }

        // ne ocupam de lista
        for (int i = 0; i < library.getReaders().size(); i++){
             int result = ID.compareTo(library.getReaders().get(i).getUserId());
             if (result == 0){
                 List<Book> borrowedBooks = library.getReaders().get(i).getBooksBorrowed();
                 Collections.sort(borrowedBooks, Comparator.comparing(Book::getTitle));

//                 System.out.print("Books borrowed by the reader: ");
//                 for(int j = 0; j < borrowedBooks.size(); j++) {
//                     System.out.print(borrowedBooks.get(j).toString());
//                     System.out.println();
//                 }
            }
        }
    }

    private void searchforDueDate() {
        System.out.println("Write the ID of the borrowed book:");
        String brrwdBook = scanner.nextLine().toUpperCase();
        System.out.println("Write the ID of the person who borrowed it: ");
        String borrower = scanner.nextLine();

        List<Borrow> aux = borrowDB.readBorrow();
        for (int i = 0; i < aux.size(); i++)
        {
            if (aux.get(i).getBorrowedBook().toUpperCase().equals(brrwdBook) && aux.get(i).getUserID().equals(borrower)){
                System.out.println(aux.get(i).getDueDate());
            }
        }


//        for (int i = 0; i < library.getBorrows().size(); i++)
//        {
//            if (library.getBorrows().get(i).borrowedBook.toUpperCase().equals(brrwdBook) && borrower == library.getBorrows().get(i).userID){
//                System.out.println(library.getBorrows().get(i).dueDate);
//            }
//        }

    }

    private void returnofaBook() {
        System.out.println("What is the book you want to return?(ISBN) ");
        String book = scanner.nextLine().toUpperCase();
        System.out.println("Write the ID of the person who wants to return it: ");
        String borrower = scanner.nextLine();

        // stergem cartea din baza de date
        borrowDB.deleteBorrow(borrower, book);

        // stergem cartea din lista de imprumuturi si din lista de imprumuturi a cititorului
        for(int i = 0; i < library.getBorrows().size(); i++) {
            if (book.equals(library.getBorrows().get(i).getBorrowedBook().toUpperCase())){
                library.getBorrows().remove(i);
            }
        }

        for(int i = 0; i < library.getReaders().size(); i++) {
            if (borrower.equals(library.getReaders().get(i).getUserId())){
                for (int j = 0; j < library.getReaders().get(i).getBooksBorrowed().size(); j++)
                {
                    if (book.equals(library.getReaders().get(i).getBooksBorrowed().get(i).getTitle().toUpperCase()))
                        library.getReaders().get(i).getBooksBorrowed().remove(i);
                }
            }
        }
    }

    private void searchforNoofBooks() {
        System.out.println("For what book do you want to know the number of available pieces: ");
        String bookName = scanner.nextLine().toUpperCase();
        // for ul era folosit atunci cand foloseam liste
//        for (int i = 0 ; i < library.getBooks().size(); i++){
//            if (bookName.equals(library.getBooks().get(i).getTitle().toUpperCase())){
//                System.out.println("Numarul de bucati din cartea cautata este egal cu: ");
//                System.out.println(library.getBooks().get(i).getNoOfPieces());
//                return;
//            }
//        }

        int result = bookDB.getNumberOfBook(bookName);
        System.out.println("Number of books with the given name: " + result);
    }

    private void searchforReader() {
        System.out.println("Introduce the first name of the reader: ");
        String readerName = scanner.nextLine();
        List<Reader> aux = readerDB.readReader();
        for (int i = 0 ; i < aux.size(); i++){
            if (readerName.equals(aux.get(i).getfirstName().toUpperCase())){
                System.out.println(aux.get(i).toString());
                return;
            }
        }
    }

    private void searchBookbyAuthor() {
        System.out.println("Introduce the name of the writer: ");

        List <Book> aux = bookDB.readBook();
        String writerName = scanner.nextLine().toUpperCase();
        for (int i = 0 ; i < aux.size(); i++){
            if (writerName.equals(aux.get(i).getAuthor().toUpperCase())){
                System.out.println(aux.get(i).toString());
                return;
            }
        }
        System.out.println("The book you asked for doesn't exist in our library");
    }
    
    private void searchBookbyName() {
        System.out.println("Introduce the name of the book you want to search for:");
        // citim cartile din baza de date
        List<Book> aux = bookDB.readBook();
        String bookName = scanner.nextLine().toUpperCase();
        for (int i = 0 ; i < aux.size(); i++){
            if (bookName.equals(aux.get(i).getTitle().toUpperCase())){
                System.out.println(aux.get(i).toString());
                return;
            }
        }
        System.out.println("In this library doesn't exist the book with the specified name");
    }

    private void deleteEmployee() {
        System.out.println("What librarian do you want to delete from the database? Type the ID:");
        librarianDB.readAllLibrarians();
//        List<model.Librarian> aux = library.getLibrarian();
//        for (int i = 0; i < aux.size(); i++){
//            System.out.println(aux.get(i).toString());
//        }
        // fac un obiect de tpul model.Librarian care sa contina id ul pe care ul vreau sa il trimit
        int ind = scanner.nextInt();
        Librarian aux = new Librarian();
        aux.setCNP(ind);

        System.out.println(ind);
        // stergem angajatul din baza de date
        employeeDB.deleteEmployee(aux);
        // stergem angajatul din clasa model.Library
        //aux.remove(ind);

        System.out.println("The selected employee was removed");
    }

    private void addLibrarian() {
        Librarian lib = new Librarian() ;
        lib.readData(this.scanner);

        library.AddLibrariantoList(lib);
        // adaugam bibliotecarul in baza de date
        librarianDB.createLibrarian(lib);
    }

    private void displayLibrarians() {
        // metoda apelate inainte de existenta bazei de date
        // library.ShowLibrarians();

        // afisam libraria direct din baza de date
        librarianDB.readAllLibrarians();
    }

    private void addaNewEvent(){
        Event event = new Event() ;
        event.readData(this.scanner);

        library.AddEventtoList(event);
        // adaugam evenimentul in baza de date
        eventDB.createEvent(event);
    }
    private void seeFutureEvents(){
        //List<model.Event> aux = library.getEvents();
        List<Event> aux = eventDB.readEvents();
        LocalDate today = LocalDate.now();
        int comparisonResault ;
        for (int i = 0; i < aux.size(); i++){
            comparisonResault = today.compareTo(aux.get(i).getDate());
            if (comparisonResault < 0 )
            {
                System.out.println(aux.get(i).toString());
            }
        }

    }
    private void deleteFutureEvent(){
        System.out.println("What model.Event do you want to delete? Type the index:");
        List<Event> aux = eventDB.readEvents();
        for (int i = 0; i < aux.size(); i++){
            System.out.println(aux.get(i).toString());
        }
        int ind = scanner.nextInt();

        // stergem evenimentul din baza de date
        eventDB.deleteEvent(aux.get(ind));
        // stergem evenimentul din clasa model.Library
        aux.remove(ind);

        System.out.println("The selected event was deleted");
    }
}
