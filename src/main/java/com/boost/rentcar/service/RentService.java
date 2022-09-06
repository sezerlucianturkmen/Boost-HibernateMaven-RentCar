package com.boost.rentcar.service;

import com.boost.rentcar.repository.RentRepository;
import com.boost.rentcar.repository.entity.Rent;
import com.boost.rentcar.utility.MyFactoryService;

public class RentService extends MyFactoryService<RentRepository, Rent, Long> {

	public RentService() {
		super(new RentRepository());
	}

}
