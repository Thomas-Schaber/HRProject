package edu.waketech.csc251.hr.mgmt;

import edu.waketech.csc251.hr.person.Employee;
import edu.waketech.csc251.tools.Screener;

public class ExecutiveScreen implements Screener<Employee> {

	@Override
	public boolean test(Employee emp) {
		if(emp instanceof Executive) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
