package test.test;

import java.util.Comparator;

public class Employee implements Comparable<Employee> {

	public enum Gender {
		FEMALE, MALE;
	}

	private int id;
	private String firstName;
	private String lastName;
	private double salary;
	private Employee manager;
	private Gender gender;
	private int deptNo;

	static class EmployeeDepartmentComparator implements Comparator<Employee> {

		@Override
		public int compare(Employee o1, Employee o2) {
			// TODO Auto-generated method stub
			return o1.getDeptNo()-o2.getDeptNo();
		}

	}

	public Employee getHighSalaryEmployee(Employee e) {
		return this.salary > e.salary ? this : e;
	}

	public Employee(int id, String firstName, String lastName, double salary, int gender, int deptNo) {
		super();

		/*
		 * this.address = address; this.manager = manager; this.period = period;
		 * this.gender = gender;
		 */
		if (gender == 1) {
			Gender male = Gender.MALE;
			Employee(id, firstName, lastName, salary, male, deptNo);

		} else if (gender == 2) {
			Gender female = Gender.FEMALE;
			Employee(id, firstName, lastName, salary, female, deptNo);
		}
	}

	private void Employee(int id, String firstName, String lastName, double salary, Gender gender, int deptNo) {
		// TODO Auto-generated method stub
		this.id = id;

		this.firstName = firstName;

		this.lastName = lastName;

		this.salary = salary;

		this.gender = gender;

		this.deptNo = deptNo;

	}

	public double calculateHRA() {
		// System.out.println(salary);
		double hra = (salary) * (20.00 / 100.00);
		// System.out.println(hra);
		return hra;

	}

	public double calculateGrossSal() {

		double grossSal = this.salary + calculateHRA();
		return grossSal;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	@Override
	public String toString() {
		return id + "," + firstName + "," + lastName + "," + salary + "," + gender + "," + this.deptNo + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((manager == null) ? 0 : manager.hashCode());
		long temp;
		temp = Double.doubleToLongBits(salary);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender != other.gender)
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (manager == null) {
			if (other.manager != null)
				return false;
		} else if (!manager.equals(other.manager))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		return true;
	}

	@Override
	public int compareTo(Employee o) {
		// TODO Auto-generated method stub
		if (this.lastName.equals(o.lastName))
			return (int) (this.salary - o.salary);
		else
			return this.lastName.compareTo(o.lastName);
	}

}
