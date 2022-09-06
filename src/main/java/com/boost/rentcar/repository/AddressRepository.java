package com.boost.rentcar.repository;

import com.boost.rentcar.repository.entity.Address;
import com.boost.rentcar.utility.MyFactoryRepository;

public class AddressRepository extends MyFactoryRepository<Address, Long> {

	public AddressRepository() {
		super(new Address());
	}

}
