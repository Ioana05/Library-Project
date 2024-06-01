package model;

import model.Employee;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class Librarian extends Employee implements ReadWrite {

    private int noOfExtraWorkingHours;
    private int noOfWorkedDays;
    private final float salaryDeductions = 0.30f;
    private final float salaryBenefits = 500f;
    private int bonus;


    public Librarian(String firstName, String lastName,  int CNP, String phoneNumber, LocalDate dateOfHire, float baseSalary, int noOfExtraWorkingHours, int noOfWorkedDays, int bonus, int libraryID) {
        super(firstName, lastName, CNP, phoneNumber, dateOfHire, baseSalary, libraryID);
        this.noOfExtraWorkingHours = noOfExtraWorkingHours;
        this.noOfWorkedDays = noOfWorkedDays;
        this.bonus = bonus;

    }

    public Librarian(){
        super();
        this.noOfExtraWorkingHours = 0;
        this.noOfWorkedDays = 0;
        this.bonus = 0;
    }

    ///// GETTERI /////


    public int getNoOfExtraWorkingHours() {
        return noOfExtraWorkingHours;
    }

    public int getNoOfWorkedDays() {
        return noOfWorkedDays;
    }

    public float getSalaryDeductions() {
        return salaryDeductions;
    }

    public int getBonus() {
        return bonus;
    }

    @Override
    public void readData(Scanner scanner) {
        System.out.print("Enter first name: ");
        this.firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        this.lastName = scanner.nextLine();

        System.out.print("Enter CNP: ");
        setCNP(scanner.nextInt());

        System.out.print("Enter phone number: ");
        setPhoneNumber(scanner.nextLine());

        System.out.print("Enter date of hire: ");
        this.dateOfHire = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter base salary: ");
        this.baseSalary = scanner.nextFloat();

        System.out.print("Enter number of extra working hours: ");
        this.noOfExtraWorkingHours = scanner.nextInt();

        System.out.print("Enter number of worked days: ");
        this.noOfWorkedDays = scanner.nextInt();

        System.out.print("Enter bonus: ");
        this.bonus = scanner.nextInt();

        System.out.print("Enter library ID: ");
        this.libraryID = scanner.nextInt();
    }


    protected int MonthlyWorkingHours(int noOfWorkedDays, int noOfExtraWorkingHours)
    {
        // ca sa calculez numarul total de ore muncite, inmultesc
        // numarul de zile in care angajatul a lucrat*8 ore + extra hours lucrate
        int totalhours = 8 * noOfWorkedDays + noOfExtraWorkingHours;
        return totalhours;
    }



    @Override
    protected double calculateSalary() {
        // 180 este numarul mediu de ore lucrate pe luna
        if (MonthlyWorkingHours(noOfWorkedDays, noOfExtraWorkingHours) > 180)
            bonus = 100;
        return baseSalary - salaryDeductions * baseSalary + salaryBenefits + bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Librarian librarian = (Librarian) o;
        return noOfExtraWorkingHours == librarian.noOfExtraWorkingHours && noOfWorkedDays == librarian.noOfWorkedDays && Float.compare(salaryDeductions, librarian.salaryDeductions) == 0 && Float.compare(salaryBenefits, librarian.salaryBenefits) == 0 && bonus == librarian.bonus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), noOfExtraWorkingHours, noOfWorkedDays, salaryDeductions, salaryBenefits, bonus);
    }
}

