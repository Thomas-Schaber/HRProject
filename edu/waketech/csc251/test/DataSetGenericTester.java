package edu.waketech.csc251.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.*;

import edu.waketech.csc251.hr.mgmt.Executive;
import edu.waketech.csc251.hr.mgmt.ExecutiveScreen;
import edu.waketech.csc251.hr.mgmt.Manager;
import edu.waketech.csc251.hr.mgmt.ManagerOnlyScreen;
import edu.waketech.csc251.hr.person.*;
import edu.waketech.csc251.collection.*;

public class DataSetGenericTester {
	private	Employee emp1 = new Employee("Emp1", 30000);
	private Employee man1 = new Manager("Man1", 60000, "HR");
	private Employee exe1 = new Executive("Exe1", 300000, "Sales", 0.05);
	
	
	
	@Test
	public void genericTestgetList() {
		DataSetGeneric<Employee> set = new DataSetGeneric<>();
		set.add(emp1);
		set.add(man1);
		set.add(exe1);
		
		ArrayList<Employee> list = set.getList();
		
		assertTrue(list.contains(emp1));
		assertTrue(list.contains(man1));
		assertTrue(list.contains(exe1));
	}
	
	@Test
	public void genericTestgetListEmployeeSrn() {
		DataSetGeneric<Employee> set = new DataSetGeneric<>();
		set.add(emp1);
		set.add(man1);
		set.add(exe1);
		
		ArrayList<Employee> list = set.getList(new EmployeeOnlyScreen());
		
		assertTrue(list.contains(emp1));
		assertFalse(list.contains(man1));
		assertFalse(list.contains(exe1));
	}
	
	@Test
	public void genericTestgetListManagerSrn() {
		DataSetGeneric<Employee> set = new DataSetGeneric<>();
		set.add(emp1);
		set.add(man1);
		set.add(exe1);
		
		ArrayList<Employee> list = set.getList(new ManagerOnlyScreen());
		
		assertFalse(list.contains(emp1));
		assertTrue(list.contains(man1));
		assertFalse(list.contains(exe1));
	}
	
	@Test
	public void genericTestgetListExecutiveSrn() {
		DataSetGeneric<Employee> set = new DataSetGeneric<>();
		set.add(emp1);
		set.add(man1);
		set.add(exe1);
		
		ArrayList<Employee> list = set.getList(new ExecutiveScreen());
		
		assertFalse(list.contains(emp1));
		assertFalse(list.contains(man1));
		assertTrue(list.contains(exe1));
	}
}
