import java.util.Scanner;

abstract class Employee implements ReadWrite {
    protected String name;
    protected String CNP;
    protected String phoneNumber;

    protected String dateOfHire;
    protected  float baseSalary;


    public Employee(String name, String CNP, String phoneNumber, String dateOfHire, float baseSalary) {
        this.name = name;
        this.CNP = CNP;
        this.phoneNumber = phoneNumber;
        this.dateOfHire = dateOfHire;
        this.baseSalary = baseSalary;
    }
     public Employee ()
     {
         name = "";
         CNP = "";
         phoneNumber = "";
         dateOfHire = "";
         baseSalary = 0f;
     }


    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void readData() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        this.name = scanner.nextLine();

        System.out.print("Enter CNP: ");
        this.CNP = scanner.nextLine();

        System.out.print("Enter phone number: ");
        this.phoneNumber = scanner.nextLine();

        System.out.print("Enter date of hire: ");
        this.dateOfHire = scanner.nextLine();

        System.out.print("Enter base salary: ");
        this.baseSalary = scanner.nextFloat();

        scanner.close();
    }
    protected abstract double CalculateSalary();

    @Override
    public String toString() {
        return "Employee {" +
                "name ='" + name + '\'' +
                ", CNP ='" + phoneNumber + '\'' +
                ", Date of Hire =" + dateOfHire +
                '}';
    }

}
