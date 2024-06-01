package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

abstract public class Employee implements ReadWrite {

    protected int CNP;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected LocalDate dateOfHire;
    protected  float baseSalary;

    protected int libraryID;


    public Employee(String firstName, String lastName,  int CNP, String phoneNumber, LocalDate dateOfHire, float baseSalary, int libraryID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.CNP = CNP;
        this.phoneNumber = phoneNumber;
        this.dateOfHire = dateOfHire;
        this.baseSalary = baseSalary;
        this.libraryID = libraryID;

    }
     public Employee ()
     {
         firstName = "";
         lastName = "";
         CNP = 0;
         phoneNumber = "";
         dateOfHire = LocalDate.now();
         baseSalary = 0f;
         libraryID = 0;
     }

    public Employee(ResultSet result) throws SQLException {
        this.CNP = result.getInt("CNP");
        this.firstName = result.getString("firstName");
        this.lastName = result.getString("lastName");
        this.phoneNumber = result.getString("phoneNumber");
        this.dateOfHire = result.getDate("dateofHire").toLocalDate();
        this.baseSalary = result.getFloat("baseSalary");

    }

    public void setCNP(int CNP) {
        this.CNP = CNP;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /////// GETTERI ///////////////

    public int getCNP() {
        return CNP;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfHire() {
        return dateOfHire;
    }

    public float getBaseSalary() {
        return baseSalary;
    }

    public int getLibraryID() {
        return libraryID;
    }

    public void readData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first name: ");
        this.firstName = scanner.nextLine();

        System.out.print("Enter first name: ");
        this.lastName = scanner.nextLine();

        System.out.print("Enter CNP: ");
        this.CNP = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter phone number: ");
        this.phoneNumber = scanner.nextLine();

        System.out.print("Enter date of hire: ");
        this.dateOfHire = LocalDate.parse(scanner.nextLine());

        scanner.nextLine();

        System.out.print("Enter base salary: ");
        this.baseSalary = scanner.nextFloat();

        System.out.print("Enter library id: ");
        this.libraryID = scanner.nextInt();

        scanner.close();
    }
    protected abstract double calculateSalary();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return CNP == employee.CNP && Float.compare(baseSalary, employee.baseSalary) == 0 && libraryID == employee.libraryID && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(phoneNumber, employee.phoneNumber) && Objects.equals(dateOfHire, employee.dateOfHire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CNP, firstName, lastName, phoneNumber, dateOfHire, baseSalary, libraryID);
    }

    @Override
    public String toString() {
        return "model.Employee {" +
                "firstName ='" + firstName + '\'' +
                "lastName ='" + lastName + '\'' +
                ", CNP ='" + CNP + '\'' +
                "phoneNumber ='" + phoneNumber + '\'' +
                "baseSalary ='" + baseSalary + '\'' +
                ", DateofHire =" + dateOfHire +
                " libraryId ='" + libraryID + '\'' +
                '}';
    }

}
