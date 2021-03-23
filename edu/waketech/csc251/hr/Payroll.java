package edu.waketech.csc251.hr;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import edu.waketech.csc251.collection.DataSetGeneric;
import edu.waketech.csc251.hr.mgmt.Executive;
import edu.waketech.csc251.hr.mgmt.ExecutiveScreen;
import edu.waketech.csc251.hr.mgmt.Manager;
import edu.waketech.csc251.hr.mgmt.ManagerOnlyScreen;
import edu.waketech.csc251.hr.person.Employee;
import edu.waketech.csc251.hr.person.EmployeeOnlyScreen;
import edu.waketech.csc251.tools.Utils;

public class Payroll {

	// Possible User Actions.
	public static final String MENU_EXIT = "Exit";
	public static final String MENU_ADD_EMPLOYEE = "Add Employee";
	public static final String MENU_ADD_MANAGER = "Add Manager";
	public static final String MENU_ADD_EXECUTIVE = "Add Executive";
	public static final String MENU_LIST_ALL = "List All";
	public static final String MENU_LIST_EMPLOYEE = "List (Regular) Employees";
	public static final String MENU_LIST_MANAGER = "List Managers";
	public static final String MENU_LIST_EXECUTIVE = "List Executives";
	public static final String MENU_SHOW_HIGHEST_SALARY_PERSON = "Show Highest Salary Person";
	public static final String MENU_GENERATE_PAYROLL = "Generate Payroll";
	public static final String MENU_SORT_BY_NAME_THEN_SALARY = "Display Sorted List by Name";
	public static final String MENU_SORT_BY_SALARY_THEN_NAME = "Display Sorted List By Salary";

	// Menu for user actions
	public static final String[] MAIN_MENU = { MENU_EXIT, MENU_ADD_EMPLOYEE, MENU_ADD_MANAGER, MENU_ADD_EXECUTIVE,
			MENU_LIST_ALL, MENU_LIST_EMPLOYEE, MENU_LIST_MANAGER, MENU_LIST_EXECUTIVE, MENU_SHOW_HIGHEST_SALARY_PERSON,
			MENU_GENERATE_PAYROLL, MENU_SORT_BY_NAME_THEN_SALARY, MENU_SORT_BY_SALARY_THEN_NAME, };

	public static void main(String[] args) {
		Scanner kybd = new Scanner(System.in);
		DataSetGeneric<Employee> hrdb = new DataSetGeneric<>();

		String userChoice = Utils.userChoose(kybd, MAIN_MENU);
		while (!MENU_EXIT.equals(userChoice)) {
			if (MENU_ADD_EMPLOYEE.equals(userChoice)) {
				addEmployee(kybd, hrdb);
			} else if (MENU_ADD_MANAGER.equals(userChoice)) {
				addManager(kybd, hrdb);
			} else if (MENU_ADD_EXECUTIVE.equals(userChoice)) {
				addExecutive(kybd, hrdb);
			} else if (MENU_LIST_ALL.equals(userChoice)) {
				displayEverybody(kybd, hrdb);
			} else if (MENU_LIST_EMPLOYEE.equals(userChoice)) {
				displayOnlyEmployees(kybd, hrdb);
			} else if (MENU_LIST_MANAGER.equals(userChoice)) {
				displayOnlyManagers(kybd, hrdb);
			} else if (MENU_LIST_EXECUTIVE.equals(userChoice)) {
				displayOnlyExecutives(kybd, hrdb);
			} else if (MENU_SHOW_HIGHEST_SALARY_PERSON.equals(userChoice)) {
				displayHighestSalary(kybd, hrdb);
			} else if (MENU_GENERATE_PAYROLL.equals(userChoice)) {
				generatePayroll(kybd, hrdb);
			} else if (MENU_SORT_BY_NAME_THEN_SALARY.equals(userChoice)) {
				displaySortedByName(kybd, hrdb);
			} else if (MENU_SORT_BY_SALARY_THEN_NAME.equals(userChoice)) {
				displaySortedBySalary(kybd, hrdb);
			}

			userChoice = Utils.userChoose(kybd, MAIN_MENU);
		}
		System.out.println("Bye");
	}

	/**
	 * Display the List of Employees sorted first by name, and then by salary
	 * 
	 * @param kybd      incoming data stream
	 * @param dataStore DataSetGeneric to provide sorted Employees
	 */
	private static void displaySortedByName(Scanner kybd, DataSetGeneric<Employee> dataStore) {
		ArrayList<Employee> sorted = (ArrayList<Employee>) dataStore.sortBy(new Comparator<Employee>() {

			@Override
			public int compare(Employee emp1, Employee emp2) {
				if(emp1.getName().equalsIgnoreCase(emp2.getName())) {
					return (int)(emp1.getSalary() - emp2.getSalary());
				} else {
					return emp1.getName().compareToIgnoreCase(emp2.getName());
				}
			}
			
		});
		Utils.userDisplay(kybd, sorted);
	}

	/**
	 * Display the list of Employees sorted first by salary, then by name.
	 * 
	 * @param kybd      incoming data stream
	 * @param dataStore provides the sorted list of Employees
	 */
	private static void displaySortedBySalary(Scanner kybd, DataSetGeneric<Employee> dataStore) {
		ArrayList<Employee> sorted = (ArrayList<Employee>) dataStore.sortBy(new Comparator<Employee>() {
			
			@Override
			public int compare(Employee emp1, Employee emp2) {
				if(emp1.getSalary() == emp2.getSalary()) {
					return emp1.getName().compareToIgnoreCase(emp2.getName());
				} else {
					return (int)(emp1.getSalary() - emp2.getSalary());
				}
				
			}
		});
		
		Utils.userDisplay(kybd, sorted);
		//dataStore.sortBy(comparator)
	}

	/**
	 * Add a regular employee to the data store
	 * 
	 * @param input     incoming data stream
	 * @param dataStore will hold the new employee
	 */
	public static void addEmployee(Scanner input, DataSetGeneric<Employee> dataStore) {
		System.out.print("Name ");
		String name = input.next();
		System.out.println("Enter Salary");
		double sal = input.nextDouble();
		Employee emp = new Employee(name, sal);
		dataStore.add(emp);
	}

	/**
	 * Add an executive to the data store
	 * 
	 * @param input     incoming data stream
	 * @param dataStore will hold the new executive
	 */
	public static void addExecutive(Scanner input, DataSetGeneric<Employee> dataStore) {
		System.out.print("Name ");
		String name = input.next();
		System.out.println("Enter Salary");
		double sal = input.nextDouble();
		System.out.println("Enter Department Managed ");
		String dept = input.next();
		System.out.println("Enter Bonus ");
		double bonus = input.nextDouble();
		Employee emp = new Executive(name, sal, dept, bonus);
		dataStore.add(emp);
	}

	/**
	 * Add a manager to the data store
	 * 
	 * @param input     incoming data stream
	 * @param dataStore will hold the new manager
	 */
	public static void addManager(Scanner input, DataSetGeneric<Employee> dataStore) {
		System.out.print("Name ");
		String name = input.next();
		System.out.println("Enter Salary");
		double sal = input.nextDouble();
		System.out.println("Enter Department ");
		String dept = input.next();
		Employee emp = new Manager(name, sal, dept);
		dataStore.add(emp);
	}

	/**
	 * Display everybody in the data store
	 * 
	 * @param kybd      incoming data stream so the user can verify that the data
	 *                  has been seen
	 * @param dataStore containing the employees to be displayed
	 */
	public static void displayEverybody(Scanner kybd, DataSetGeneric<Employee> dataStore) {
		Utils.userDisplay(kybd, dataStore.getList());
	}

	/**
	 * Display the person with the highest salary
	 * 
	 * @param kybd      incoming data stream so the user can verify that the data
	 *                  has been seen
	 * @param dataStore containing the employees to be displayed
	 */
	public static void displayHighestSalary(Scanner kybd, DataSetGeneric<Employee> dataStore) {
		System.out.println(dataStore.getMax());
	}

	/**
	 * Display only the regular employees
	 * 
	 * @param kybd      incoming data stream so the user can verify that the data
	 *                  has been seen
	 * @param dataStore containing the employees to be displayed
	 */
	public static void displayOnlyEmployees(Scanner kybd, DataSetGeneric<Employee> dataStore) {
		Utils.userDisplay(kybd, dataStore.getList(new EmployeeOnlyScreen()));
		
	}

	/**
	 * Display only the executives
	 * 
	 * @param kybd      incoming data stream so the user can verify that the data
	 *                  has been seen
	 * @param dataStore containing the employees to be displayed
	 */
	public static void displayOnlyExecutives(Scanner kybd, DataSetGeneric<Employee> dataStore) {
		Utils.userDisplay(kybd, dataStore.getList(new ExecutiveScreen()));
	}

	/**
	 * Display on the managers
	 * 
	 * @param kybd      incoming data stream so the user can verify that the data
	 *                  has been seen
	 * @param dataStore containing the employees to be displayed
	 */
	public static void displayOnlyManagers(Scanner kybd, DataSetGeneric<Employee> dataStore) {
		Utils.userDisplay(kybd, dataStore.getList(new ManagerOnlyScreen()));
	}

	/**
	 * Generate the payroll
	 * 
	 * @param kybd      incoming data stream so the user can verify that the data
	 *                  has been seen
	 * @param dataStore containing the employees
	 */
	public static void generatePayroll(Scanner kybd, DataSetGeneric<Employee> dataStore) {
		ArrayList<Employee> emp = dataStore.getList();
		for(int x = 0; x < emp.size(); x++) {
			System.out.printf("\t[" + x + "] Pay " + emp.get(x).getName() + " $%.2f \n", emp.get(x).getSalary());
		}
		System.out.println("Press any key and enter to Continue: ");
	    kybd.next();
	}
}
