package com.boost.rentcar.service;

import com.boost.rentcar.repository.CarRepository;
import com.boost.rentcar.repository.entity.Car;
import com.boost.rentcar.utility.MyFactoryService;

public class CarService extends MyFactoryService<CarRepository, Car, Long> {

	public CarService() {
		super(new CarRepository());
	}

}
