package com.boost.rentcar.service;

import com.boost.rentcar.repository.AddressRepository;
import com.boost.rentcar.repository.entity.Address;
import com.boost.rentcar.utility.MyFactoryService;

public class AddressService extends MyFactoryService<AddressRepository, Address, Long> {

	public AddressService() {
		super(new AddressRepository());
	}

}
