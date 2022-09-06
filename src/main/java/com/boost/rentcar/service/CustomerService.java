package com.boost.rentcar.service;

import com.boost.rentcar.repository.CustomerRepository;
import com.boost.rentcar.repository.entity.Customer;
import com.boost.rentcar.utility.MyFactoryService;

public class CustomerService extends MyFactoryService<CustomerRepository, Customer, Long> {

	public CustomerService() {
		super(new CustomerRepository());
	}

}
