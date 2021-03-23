package edu.waketech.csc251.hr.test;

import org.junit.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import edu.waketech.csc251.hr.mgmt.*;
import edu.waketech.csc251.hr.person.*;

public class EmployeeTester {

	
	@Test
	public void testEmployeeSrnEmp() {
		Employee emp1 = new Employee("Emp1", 30000);
		assertTrue(new EmployeeOnlyScreen().test(emp1));
	}
	
	@Test
	public void testEmployeeSrnManager() {
		Employee emp1 = new Employee("Emp1", 30000);
		assertFalse(new ManagerOnlyScreen().test(emp1));
	}
	
	@Test
	public void testEmployeeSrnExecutive() {
		Employee emp1 = new Employee("Emp1", 30000);
		assertFalse(new ExecutiveScreen().test(emp1));
	}
	
	@Test
	public void testManagerSrnEmp() {
		Employee man1 = new Manager("Man1", 60000, "HR");
		assertFalse(new EmployeeOnlyScreen().test(man1));
	}
	
	@Test
	public void testManagerSrnManager() {
		Employee man1 = new Manager("Man1", 60000, "HR");
		assertTrue(new ManagerOnlyScreen().test(man1));
	}
	
	@Test
	public void testManagerSrnExecutive() {
		Employee man1 = new Manager("Man1", 60000, "HR");
		assertFalse(new ExecutiveScreen().test(man1));
	}
	
	@Test
	public void testExecutiveSrnEmp() {
		Employee exe1 = new Executive("Exe1", 300000, "Sales", 0.05);
		assertFalse(new EmployeeOnlyScreen().test(exe1));
	}
	
	@Test
	public void testExecutiveSrnManager() {
		Employee exe1 = new Executive("Exe1", 300000, "Sales", 0.05);
		assertFalse(new ManagerOnlyScreen().test(exe1));
	}
	
	@Test
	public void testExecutiveSrnExecutive() {
		Employee exe1 = new Executive("Exe1", 300000, "Sales", 0.05);
		assertTrue(new ExecutiveScreen().test(exe1));
	}
}
