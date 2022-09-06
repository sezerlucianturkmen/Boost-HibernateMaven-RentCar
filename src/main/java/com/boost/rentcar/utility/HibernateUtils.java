package com.boost.rentcar.utility;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.boost.rentcar.repository.entity.Address;
import com.boost.rentcar.repository.entity.Car;
import com.boost.rentcar.repository.entity.Customer;
import com.boost.rentcar.repository.entity.Employee;
import com.boost.rentcar.repository.entity.Rent;

public class HibernateUtils {

	private static final SessionFactory FACTORY;

	static {

		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(Address.class);
			configuration.addAnnotatedClass(Car.class);
			configuration.addAnnotatedClass(Customer.class);
			configuration.addAnnotatedClass(Employee.class);
			configuration.addAnnotatedClass(Rent.class);
			FACTORY = configuration.configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Exception e) {
			throw e;
		}
	}

	public static SessionFactory getFactory() {
		return FACTORY;
	}

}
