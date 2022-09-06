package com.boost.rentcar.service;

import com.boost.rentcar.repository.EmployeeRepository;
import com.boost.rentcar.repository.entity.Employee;
import com.boost.rentcar.utility.MyFactoryService;

public class EmployeeService extends MyFactoryService<EmployeeRepository, Employee, Long> {

	public EmployeeService() {
		super(new EmployeeRepository());
	}

}
