import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class Menu {
    private static Menu menu;
    private Library library;
    private Scanner scanner;

    private LibraryDirector director;

    private Menu() {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
        this.director = new LibraryDirector();

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

        System.out.println("Congrats! You finished the configuration of your library. Now you'll be redirected to the real menu of the library: ");
    }
    void InitiateMenu() {
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
                            DirectorMenu();
                        else
                            System.out.println("The password you introduced is not correct. Are you the real director? :/");
                        break;
                    case 2:
                        System.out.println("Hello, librarian !");
                        LibrarianMenu();
                        break;
                    case 3:
                        System.out.println("Exiting...");
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

    private void DirectorMenu() {
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
                        AddaNewEvent();
                        break;
                    case 11:
                        System.out.println("Here are the future events: ");
                        SeeFutureEvents();
                        break;
                    case 12:
                        System.out.println("What event do you want to delete?");
                        DeleteFutureEvent();
                        break;
                    case 13:
                        DisplayLibrarians();
                        break;
                    case 14:
                        AddLibrarian();
                        break;
                    case 15:
                        DeleteEmployee();
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

    private void LibrarianMenu() {
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
            System.out.println("33. Go back to the previous menu ");



            int libchoice = this.scanner.nextInt();
            this.scanner.nextLine();

            try {
                switch (libchoice) {
                    case 20:
                        System.out.println("What event do you want to add? :");
                        AddaNewEvent();
                        break;
                    case 21:

                        System.out.println("Here are the future events: ");
                        SeeFutureEvents();
                        break;
                    case 22:
                        DeleteFutureEvent();
                        break;
                    case 23:
                        SearchBookbyName();
                        break;
                    case 24:
                        SearchBookbyAuthor();
                        break;
                    case 25:
                        SearchforReader();
                        break;
                    case 26:
                        SearchforNoofBooks();
                        break;
                    case 27:
                        ReturnofaBook();
                        break;
                    case 28:
                        SearchforDueDate();
                        break;
                    case 29:
                        BooksofaReader();
                        break;
                    case 30:
                        AddaBook();
                        break;
                    case 31:
                        AddaReader();
                        break;
                    case 32:
                        AddaBorrow();
                        break;
                    case 33:
                        System.out.println("Exiting...");
                        libishere = 0;
                        break;

                }
            }
            catch(NoSuchElementException e){
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private void AddaBorrow() {
        Borrow borrow = new Borrow() ;
        borrow.readData(this.scanner);
        library.AddBorrowtoList(borrow);

        // adaugam cartea in lista de carti imprumutate
        String aux = borrow.borrowedBook.toUpperCase();
        for (int i = 0 ; i < library.getBooks().size(); i++){
            Book currentBook = library.getBooks().get(i);

            if (aux.equals(currentBook.getTitle().toUpperCase()) && currentBook.getNoOfPieces() > 0){
                // scad numarul de exemplare din cartea pe care tocmai o imprumut
                int NoofBooks = currentBook.getNoOfPieces();
                currentBook.setNoOfPieces(NoofBooks - 1);

                /// adaugam cartea si in lista de imprumuri a celui care a cerut-0
                String Borrower =  borrow.getUserID();

                for (int j = 0; j <library.getReaders().size(); j++)
                {
                    if((Borrower.equals(library.getReaders().get(j).getUserId()))) {
                        library.getReaders().get(j).booksBorrowed.add(currentBook);
                        library.getReaders().get(j).readingHistory.add(currentBook);
                    }
                }
            }
            else
                System.out.println("Cartea cautata nu este disponibila!");
        }
    }

    private void AddaReader() {
        Reader reader = new Reader();
        reader.readData(this.scanner);
        library.AddReadertoList(reader);

    }

    private void AddaBook() {
        Book book = new Book() ;
        book.readData(this.scanner);
        library.AddBooktoList(book);

    }

    private void BooksofaReader() {
        System.out.println("Write the ID of the reader: ");
        String ID = scanner.nextLine();

        for (int i = 0; i < library.getReaders().size(); i++){
             int result = ID.compareTo(library.getReaders().get(i).userId);
             if (result == 0){
                 List<Book> borrowedBooks = library.getReaders().get(i).getBooksBorrowed();
                 Collections.sort(borrowedBooks, Comparator.comparing(Book::getTitle));

                 System.out.print("Books borrowed by the reader: ");
                 for(int j = 0; j < borrowedBooks.size(); j++) {
                     System.out.print(borrowedBooks.get(j).toString());
                     System.out.println();
                 }
            }
        }
    }

    private void SearchforDueDate() {
        System.out.println("Write the name of the borrowed book:");
        String brrwdBook = scanner.nextLine().toUpperCase();
        System.out.println("Write the ID of the person who borrowed it: ");
        String borrower = scanner.nextLine();

        for (int i = 0; i < library.getBorrows().size(); i++)
        {
            if (library.getBorrows().get(i).borrowedBook.toUpperCase().equals(brrwdBook) && borrower == library.getBorrows().get(i).userID){
                System.out.println(library.getBorrows().get(i).dueDate);
            }
        }

    }

    private void ReturnofaBook() {
        System.out.println("What is the book you want to return? ");
        String book = scanner.nextLine().toUpperCase();
        System.out.println("Write the ID of the person who wants to return it: ");
        String borrower = scanner.nextLine();

        // crestem numarul de carti disponibile din cartea care este returnata
        for(int i = 0; i < library.getBooks().size(); i++) {
            if (book.equals(library.getBooks().get(i).getTitle().toUpperCase())){
                int NoofBooks = library.getBooks().get(i).getNoOfPieces();
                library.getBooks().get(i).setNoOfPieces(NoofBooks+1);
            }
        }

        // stergem cartea din lista de imprumuturi si din lista de imprumuturi a cititorului
        for(int i = 0; i < library.getBorrows().size(); i++) {
            if (book.equals(library.getBorrows().get(i).getBorrowedBook().toUpperCase())){
                library.getBorrows().remove(i);
            }
        }

        for(int i = 0; i < library.getReaders().size(); i++) {
            if (borrower.equals(library.getReaders().get(i).getUserId())){
                for (int j = 0; j < library.getReaders().get(i).booksBorrowed.size(); j++)
                {
                    if (book.equals(library.getReaders().get(i).booksBorrowed.get(i).getTitle().toUpperCase()))
                        library.getReaders().get(i).booksBorrowed.remove(i);
                }
            }
        }
    }

    private void SearchforNoofBooks() {
        System.out.println("For what book do you want to know the number of available pieces: ");
        String bookName = scanner.nextLine().toUpperCase();
        for (int i = 0 ; i < library.getBooks().size(); i++){
            if (bookName.equals(library.getBooks().get(i).getTitle().toUpperCase())){
                System.out.println("Numarul de bucati din cartea cautata este egal cu: ");
                System.out.println(library.getBooks().get(i).getNoOfPieces());
                return;
            }
        }
    }

    private void SearchforReader() {
        System.out.println("Introduce the name of the reader: ");
        String readerName = scanner.nextLine().toUpperCase();
        for (int i = 0 ; i < library.getReaders().size(); i++){
            if (readerName.equals(library.getReaders().get(i).getName().toUpperCase())){
                System.out.println(library.getReaders().get(i).toString());
                return;
            }
        }
    }

    private void SearchBookbyAuthor() {
        System.out.println("Introduce the name of the writer: ");
        String writerName = scanner.nextLine().toUpperCase();
        for (int i = 0 ; i < library.getBooks().size(); i++){
            if (writerName.equals(library.getBooks().get(i).getAuthor().toUpperCase())){
                System.out.println(library.getBooks().get(i).toString());
                return;
            }
        }
        System.out.println("The book you asked for doesn't exist in our library");
    }
    
    private void SearchBookbyName() {
        System.out.println("Introduce the name of the book you want to search for:");
        String bookName = scanner.nextLine().toUpperCase();
        for (int i = 0 ; i < library.getBooks().size(); i++){
            if (bookName.equals(library.getBooks().get(i).getTitle().toUpperCase())){
                System.out.println(library.getBooks().get(i).toString());
                return;
            }
        }
        System.out.println("In this library doesn't exist the book with the specified name");
    }

    private void DeleteEmployee() {
        System.out.println("What librarian do you want to delete from the database? Type the index:");
        List<Librarian> aux = library.getLibrarian();
        for (int i = 0; i < aux.size(); i++){
            System.out.println(aux.get(i).toString());
        }
        int ind = scanner.nextInt();
        aux.remove(ind);
    }

    private void AddLibrarian() {
        Librarian lib = new Librarian() ;
        lib.readData(this.scanner);
        library.AddLibrariantoList(lib);
        return;
    }

    private void DisplayLibrarians() {
        library.ShowLibrarians();
    }

    private void AddaNewEvent(){
        Event event = new Event() ;
        event.readData(this.scanner);
        library.AddEventtoList(event);
    }
    private void SeeFutureEvents(){
        List<Event> aux = library.getEvents();
        LocalDate today = LocalDate.now();
        int comparisonResault ;
        for (int i = 0; i < aux.size(); i++){
            comparisonResault = today.compareTo(aux.get(i).date);
            if (comparisonResault < 0 )
            {
                System.out.println(aux.toString());
            }
        }

    }
    private void DeleteFutureEvent(){
        System.out.println("What Event do you want to delete? Type the index:");
        List<Event> aux = library.getEvents();
        for (int i = 0; i < aux.size(); i++){
            System.out.println(aux.get(i).toString());
        }
        int ind = scanner.nextInt();
        aux.remove(ind);
        System.out.println("The selected event was deleted");

    }


}
