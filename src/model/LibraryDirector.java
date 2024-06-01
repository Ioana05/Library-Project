package model;

import model.Employee;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Scanner;

public class LibraryDirector extends Employee implements ReadWrite {

    private final float salaryDeductions = 0.30f;
    private final float salaryBenefits = 1000f;

    private int experience;

    private float salaryIncreases;
    public LibraryDirector(String firstName, String lastName,  int CNP, String phoneNumber, LocalDate dateOfHire, float baseSalary,int libraryID, int experience) {
        super(firstName, lastName, CNP, phoneNumber, dateOfHire,baseSalary,libraryID);
        this.experience = experience;
    }

    public float getSalaryDeductions() {
        return salaryDeductions;
    }

    public float getSalaryBenefits() {
        return salaryBenefits;
    }

    public int getExperience() {
        return experience;
    }

    public float getSalaryIncreases() {
        return salaryIncreases;
    }

    public  LibraryDirector(){
        super();
        this.experience = 0;
    }

    @Override
    public void readData(Scanner scanner) {
        System.out.println("Hello! You must be the director. Introduce the required data:");
        System.out.print("Enter first name: ");
        this.firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        this.lastName = scanner.nextLine();

        System.out.print("Enter CNP: ");
        setCNP(scanner.nextInt());

        System.out.print("Enter phone number: ");
        setPhoneNumber(scanner.nextLine());

        System.out.print("Enter date of hire(yyyy-mm-dd): ");
        this.dateOfHire = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter base salary: ");
        this.baseSalary = scanner.nextFloat();

        System.out.print("Enter libraryID: ");
        this.libraryID = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Enter experience: ");
        this.experience = scanner.nextInt();

        System.out.print("Enter salary increases: ");
        this.salaryIncreases = scanner.nextFloat();
    }

    @Override
    public double calculateSalary() {
        return baseSalary - salaryDeductions * baseSalary  + salaryBenefits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        LibraryDirector that = (LibraryDirector) o;
        return Float.compare(salaryDeductions, that.salaryDeductions) == 0 && Float.compare(salaryBenefits, that.salaryBenefits) == 0 && experience == that.experience && Float.compare(salaryIncreases, that.salaryIncreases) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salaryDeductions, salaryBenefits, experience, salaryIncreases);
    }
}