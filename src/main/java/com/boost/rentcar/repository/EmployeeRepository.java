package com.boost.rentcar.repository;

import com.boost.rentcar.repository.entity.Employee;
import com.boost.rentcar.utility.MyFactoryRepository;

public class EmployeeRepository extends MyFactoryRepository<Employee, Long> {

	public EmployeeRepository() {
		super(new Employee());
	}

}
