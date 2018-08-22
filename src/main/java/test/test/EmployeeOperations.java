package test.test;

import java.util.List;

import test.test.Employee.Gender;
/**
 * Created by Manohar on 4/21/2017.
 */
public interface EmployeeOperations {

    void createEmployee(Employee employee) throws RuntimeException;

    Employee findEmployee(int empId);

    List<Employee> findAll();

    boolean deleteEmployee(int empId) throws Exception;

    boolean updateEmployee(Employee employee) throws Exception,RuntimeException;

    double displayHRA(int empId);

    Double calculateGrossSal(int empId);
    void groupBy(Gender g);
    void sortByFirstName();
    void sortBySalary();
    void sortByid();
    void groupByAverageSalary();
    
    
}
