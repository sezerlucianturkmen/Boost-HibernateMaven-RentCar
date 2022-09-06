package com.boost.rentcar.repository;

import com.boost.rentcar.repository.entity.Car;
import com.boost.rentcar.utility.MyFactoryRepository;

public class CarRepository extends MyFactoryRepository<Car, Long> {

	public CarRepository() {
		super(new Car());
	}

}
