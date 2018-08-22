package test.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import test.test.Employee.Gender;
import lombok.Getter;
@Getter
public class EmployeeDao implements EmployeeOperations {
	List<Employee> empList=null;
	@Override
	public void createEmployee(Employee employee) throws InvalidSalaryException {
		try {
			Connection con = DbClass.connectDB();
			Statement st = con.createStatement();
			if (employee.getSalary() < 500)
				throw new InvalidSalaryException("Salary Must be Greater than 500");
			String sql = "INSERT INTO EmpTable values(" + employee.getId() + "," + "'" + employee.getFirstName() + "'"
					+ "," + "'" + employee.getLastName() + "'" + "," + employee.getSalary() + "," + "'"
					+ employee.getGender() + "'" + "," + employee.getDeptNo() + ")";
			st.executeUpdate(sql);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

	}

	@Override
	public Employee findEmployee(int empId) {
		PreparedStatement pmSt = null;
		try {
			Connection con = DbClass.connectDB();
			Statement st = con.createStatement();

			String sql = "SELECT * FROM EmpTable WHERE ID=?";
			pmSt = con.prepareStatement(sql);
			pmSt.setInt(1, empId);
			ResultSet rs = pmSt.executeQuery();
			int id = 0;
			String firstName = null;
			String lastName = null;
			double salary = 0.0;
			int deptId = 0;
			String gender = null;
			while (rs.next()) {
				id = rs.getInt(1);
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				salary = rs.getDouble(4);
				deptId = rs.getInt(6);
				gender = rs.getString(5);
				// System.out.println(rs.getInt(1) + rs.getString(2) + rs.getString(3) +
				// rs.getDouble(4) + rs.getInt(6));
			}
			Employee e = null;
			if (gender != null) {
				if (gender.equals("MALE"))
					e = new Employee(id, firstName, lastName, salary, 1, deptId);
				else
					e = new Employee(id, firstName, lastName, salary, 2, deptId);
			}
			return e;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		PreparedStatement pmSt = null;
		 empList=new ArrayList<>();
		Employee e = null;
		try {
			Connection con = DbClass.connectDB();
			Statement st = con.createStatement();

			String sql = "SELECT * FROM EmpTable";
			pmSt = con.prepareStatement(sql);
			ResultSet rs = pmSt.executeQuery();
			int id = 0;
			String firstName = null;
			String lastName = null;
			double salary = 0.0;
			int deptId = 0;
			String gender = null;
			while (rs.next()) {
				id = rs.getInt(1);
				firstName = rs.getString(2);
				lastName = rs.getString(3);
				salary = rs.getDouble(4);
				deptId = rs.getInt(6);
				gender = rs.getString(5);
				if (gender != null) {
					if (gender.equals("MALE")) {
						e = new Employee(id, firstName, lastName, salary, 1, deptId);
						empList.add(e);
						}
					else {
						e = new Employee(id, firstName, lastName, salary, 2, deptId);
						empList.add(e);}
				}
				
			}
			
			
			return empList;

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		return null;
	}

	@Override
	public boolean deleteEmployee(int empId) throws EmployeeNotFoundException {
		// TODO Auto-generated method stub
		PreparedStatement pmSt = null;
		int status = 0;
		try {
			Connection con = DbClass.connectDB();
			Statement st = con.createStatement();
			String sql = "DELETE FROM EmpTable WHERE ID=? ;";
			pmSt = con.prepareStatement(sql);
			pmSt.setInt(1, empId);
			status = pmSt.executeUpdate();
			if (status == 1)
				return true;
			else
				throw new EmployeeNotFoundException("Employee not found");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		if (status == 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean updateEmployee(Employee employee) throws Exception, RuntimeException {

		PreparedStatement pmSt = null;
		int status = 0;
		try {
			Connection con = DbClass.connectDB();
			Statement st = con.createStatement();
			String sql = "UPDATE EmpTable SET FirstName=? ,LastName=? ,Salary=? , Gender=?, DeptNo=? " + "WHERE Id=?;";
			pmSt = con.prepareStatement(sql);
			pmSt.setString(1, employee.getFirstName());
			pmSt.setString(2, employee.getLastName());
			pmSt.setDouble(3, employee.getSalary());
			pmSt.setString(4, employee.getGender().toString());
			pmSt.setInt(5, employee.getDeptNo());
			pmSt.setInt(6, employee.getId());
			if (employee.getSalary() < 500)
				throw new InvalidSalaryException("Salary must be greater than 500");
			status = pmSt.executeUpdate();
			if (status == 1)
				return true;
			else
				throw new EmployeeNotFoundException("Employee not found");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		if (status == 1)
			return true;
		else
			return false;
	}

	@Override
	public double displayHRA(int empId) {
		// TODO Auto-generated method stub
		Employee e=this.findEmployee(empId);
		if(e!=null)
			return e.getSalary()*0.2;
		else
			return 0;

	}

	@Override
	public Double calculateGrossSal(int empId) {
		// TODO Auto-generated method stub
		Employee e=this.findEmployee(empId);
		if(e!=null)
			return e.getSalary()*0.2 + e.getSalary();
		else
			return Double.valueOf(0);
	}

	@Override
	public void groupBy(Gender g) {
		Map<Gender,Long> groupby =empList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
		groupby.forEach((k,v)->System.out.println("Gender is: "+k+" Value is"+v));
		

	}

	@Override
	public void sortByFirstName() {
		empList.sort((e1,e2)->e1.getFirstName().compareTo(e2.getFirstName()));
		System.out.println("Sorting based on FirstName");
		for(Employee e:empList) {
			System.out.print(e);
		}
		

	}

	@Override
	public void sortBySalary() {
		// TODO Auto-generated method stub
		empList.sort((e1,e2)->(int)(e1.getSalary()*100-e2.getSalary()*100));
		System.out.println("Sorting based on Salary");
		for(Employee e:empList) {
			System.out.print(e);
		}

	}

	@Override
	public void sortByid() {
		// TODO Auto-generated method stub
		
		empList.sort((e1,e2)->e1.getId()-e2.getId());
		System.out.println("Sorting based on Ids");
		for(Employee e:empList) {
			System.out.print(e);
		}

	}

	@Override
	public void groupByAverageSalary() {
		// TODO Auto-generated method stub
		double avgSalary=0;
		for(Employee e:empList) {
			avgSalary+=e.getSalary();
		}
       avgSalary/=empList.size();
       Map<String,Integer> salaryMap=new HashMap<>();
       salaryMap.put("LOW", 0);
       salaryMap.put("HIGH", 0);
       for(Employee e:empList) {
     	  if(e.getSalary()>avgSalary)
     		 salaryMap.put("HIGH", salaryMap.get("HIGH")+1);
     	  else
     		 salaryMap.put("LOW", salaryMap.get("LOW")+1);
       }
       salaryMap.forEach((k,v)->System.out.println("Salary Range: "+k+ " Value is "+v));
	}
	
     
      
      
}
