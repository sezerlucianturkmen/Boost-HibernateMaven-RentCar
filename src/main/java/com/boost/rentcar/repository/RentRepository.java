package com.boost.rentcar.repository;

import com.boost.rentcar.repository.entity.Rent;
import com.boost.rentcar.utility.MyFactoryRepository;

public class RentRepository extends MyFactoryRepository<Rent, Long> {

	public RentRepository() {
		super(new Rent());
	}

}
