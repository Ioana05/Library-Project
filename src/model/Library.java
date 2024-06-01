package model;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Library {
    private int libraryID;
    private String name;
    private String address;
    private String city;
    private String phoneNumber;
    private String program;

    private List<Book> books;
    private List<Employee> employees;
    private List<Librarian> librarian;
    private List<Reader> readers;
    private List<Borrow> borrows;
    private List<Event> events;
    private LibraryDirector director;

    public Library(int libraryID, String name, String address, String city, String phoneNumber,String program) {
        this.libraryID = libraryID;
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber;
        this.program = program;

        this.books = new ArrayList<>();
        this.employees = new ArrayList<>();

        this.borrows = new ArrayList<>();
        this.events = new ArrayList<>();
        this.librarian = new ArrayList<>();
        this.readers = new ArrayList<>();
        this.director = new LibraryDirector();

    }

    public Library() {
        this.libraryID = -1;
        this.name = "";
        this.address = "";
        this.city = "";
        this.phoneNumber = "";
        this.program = "";

        this.books = new ArrayList<>();
        this.employees = new ArrayList<>();


        this.borrows = new ArrayList<>();
        this.events = new ArrayList<>();
        this.librarian = new ArrayList<>();
        this.readers = new ArrayList<>();

        this.director = new LibraryDirector();
    }

    ////////// GETTERI ////////
    public int getLibraryID() {
        return libraryID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProgram() {
        return program;
    }


    ///////// SETTERI////////


    public void setLibraryID(int libraryID) {
        this.libraryID = libraryID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setDirector(LibraryDirector director) {
        this.director = director;

        // verificam daca exista deja un director in lista
        // de angajati, si daca exista, il eliminam si adaugam
        // noul director
        for (int i = 0; i < this.employees.size(); i++) {
            if (this.employees.get(i) instanceof LibraryDirector) {
                this.employees.remove(i);
                break;
            }
        }

        this.employees.add(director);
    }
    /////// GETTERI /////////
    public List<Event> getEvents() {
        return events;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Reader> getReaders() {
        return readers;
    }

    public List<Borrow> getBorrows() {
        return borrows;
    }

    public List<Librarian> getLibrarian() {
        return librarian;
    }

    public void setLibrarian(List<Librarian> librarian) {
        this.librarian = librarian;
    }

    /////////  Methods from the menu /////////////
    public void AddLibrariantoList(Librarian lib)
    {
        librarian.add(lib);
        employees.add(lib);
    }

    public void AddEventtoList(Event ev)
    {
        events.add(ev);
    }

    public void AddBooktoList(Book bk){ books.add(bk); }
    public void AddReadertoList(Reader rd) { readers.add(rd);}

    public void AddBorrowtoList(Borrow bw) { borrows.add(bw);}
    public void ShowLibrarians(){
        for (int i = 0; i < librarian.size(); i++) {
            System.out.println(librarian.get(i).toString());
        }
    }

    @Override
    public String toString() {
        return "LibraryBranch{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", program='" + program + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return libraryID == library.libraryID && Objects.equals(name, library.name) && Objects.equals(address, library.address) && Objects.equals(city, library.city) && Objects.equals(phoneNumber, library.phoneNumber) && Objects.equals(program, library.program) && Objects.equals(books, library.books) && Objects.equals(employees, library.employees) && Objects.equals(librarian, library.librarian) && Objects.equals(readers, library.readers) && Objects.equals(borrows, library.borrows) && Objects.equals(events, library.events) && Objects.equals(director, library.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryID, name, address, city, phoneNumber, program, books, employees, librarian, readers, borrows, events, director);
    }
}

