import java.util.Scanner;

class LibraryDirector extends Employee implements ReadWrite{

    private final float salaryDeductions = 0.30f;
    private final float salaryBenefits = 1000f;

    private int experience;

    private float salaryIncreases;
    public LibraryDirector(String name, String CNP, String phoneNumber, String dateOfHire, float baseSalary, int experience) {
        super(name, CNP, phoneNumber, dateOfHire,baseSalary);
        this.experience = experience;
    }

    public  LibraryDirector(){
        super();
        this.experience = 0;
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

        System.out.print("Enter experience: ");
        this.experience = scanner.nextInt();

        System.out.print("Enter salary increases: ");
        this.salaryIncreases = scanner.nextFloat();
    }

    @Override
    public double CalculateSalary() {
        return baseSalary - salaryDeductions * baseSalary  + salaryBenefits;
    }

}