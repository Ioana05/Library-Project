import java.util.List;
import java.util.Scanner;

class Librarian extends Employee implements ReadWrite {

    private int noOfExtraWorkingHours;
    private int noOfWorkedDays;
    private final float salaryDeductions = 0.30f;
    private final float salaryBenefits = 500f;
    private int bonus;


    public Librarian(String name, String CNP, String phoneNumber, String dateOfHire, float baseSalary, int noOfExtraWorkingHours,int noOfWorkedDays, int bonus) {
        super(name, CNP, phoneNumber, dateOfHire, baseSalary);
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

    @Override
    public void readData(Scanner scanner) {
        System.out.print("Enter name: ");
        this.name = scanner.nextLine();

        System.out.print("Enter CNP: ");
        setCNP(scanner.nextLine());

        System.out.print("Enter phone number: ");
        setPhoneNumber(scanner.nextLine());

        System.out.print("Enter date of hire: ");
        this.dateOfHire = scanner.nextLine();

        System.out.print("Enter base salary: ");
        this.baseSalary = scanner.nextFloat();

        System.out.print("Enter number of extra working hours: ");
        this.noOfExtraWorkingHours = scanner.nextInt();

        System.out.print("Enter number of worked days: ");
        this.noOfWorkedDays = scanner.nextInt();

        System.out.print("Enter bonus: ");
        this.bonus = scanner.nextInt();
    }


    protected int MonthlyWorkingHours(int noOfWorkedDays, int noOfExtraWorkingHours)
    {
        // ca sa calculez numarul total de ore muncite, inmultesc
        // numarul de zile in care angajatul a lucrat*8 ore + extra hours lucrate
        int totalhours = 8 * noOfWorkedDays + noOfExtraWorkingHours;
        return totalhours;
    }



    @Override
    protected double CalculateSalary() {
        // 180 este numarul mediu de ore lucrate pe luna
        if (MonthlyWorkingHours(noOfWorkedDays, noOfExtraWorkingHours) > 180)
            bonus = 100;
        return baseSalary - salaryDeductions * baseSalary + salaryBenefits + bonus;
    }

}

