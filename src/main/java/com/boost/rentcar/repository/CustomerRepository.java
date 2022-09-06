package com.boost.rentcar.repository;

import com.boost.rentcar.repository.entity.Customer;
import com.boost.rentcar.utility.MyFactoryRepository;

public class CustomerRepository extends MyFactoryRepository<Customer, Long> {

	public CustomerRepository() {
		super(new Customer());
	}

}
