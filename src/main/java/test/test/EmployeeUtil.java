package test.test;

import java.util.Scanner;

public class EmployeeUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static int chooseOperation() {
        System.out.println();
        System.out.println("   ||| MENU |||   ");
        System.out.println("1. Create Employee");
        System.out.println("2. Read(View) Employee");
        System.out.println("3. View all Employees");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Calculate HRA");
        System.out.println("7. Calculate Gross Salary");   
        System.out.println("8. For Collection Operations, Sorting using LastName and Salary");
        System.out.println("9. For Collection Operations, Group by Gender");
        System.out.println("10. For Collection Operations, Sorting using id");
        System.out.println("11. For Collection Operations, Displaying high Salaried Employees");
        System.out.println("Select any other number to Quit!");
        System.out.print("Select a choice :");
        return scanner.nextInt();
    }

    public static Employee readEmployee() {
        System.out.print("Enter trng.Employee id:");
        int id = scanner.nextInt();

        System.out.print("Enter trng.Employee firstname:");
        String firstName = scanner.next();

        System.out.print("Enter trng.Employee lastname:");
        String lastName = scanner.next();

        System.out.print("Enter trng.Employee Salary:");
        double salary = scanner.nextDouble();

        System.out.println("Select the gender");
        System.out.println("	1.Male");
        System.out.println("	2.Female");
        int gender = scanner.nextInt();
        System.out.println("Enter the DeptNo:");
        int deptNo=scanner.nextInt();
        return new Employee(id, firstName, lastName, salary, gender,deptNo);
    }
}
