package com.boost.rentcar.utility;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class MyFactoryRepository<T, ID> implements ICrud<T, ID> {

	private Session session;
	private EntityManager entityManager;
	private CriteriaBuilder criteriaBuilder;
	private Transaction transaction;
	private T t;

	public MyFactoryRepository(T t) {
		entityManager = HibernateUtils.getFactory().createEntityManager();
		criteriaBuilder = entityManager.getCriteriaBuilder();
		this.t = t;
	}

	public void openSession() {
		session = HibernateUtils.getFactory().openSession();
		transaction = session.beginTransaction();
	}

	public void closeSuccess() {
		transaction.commit();
		session.close();
	}

	public void closeError() {
		transaction.rollback();
		session.close();
	}

	@Override
	public <S extends T> S save(S entity) {
		try {
			openSession();
			session.save(entity);
			closeSuccess();
			return entity;
		} catch (Exception e) {
			closeError();
			throw e;
		}
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		try {
			openSession();
			entities.forEach(entity -> {
				session.save(entity);
			});
			closeSuccess();
			return entities;
		} catch (Exception e) {
			closeError();
			throw e;
		}
	}

	@Override
	public void deleteById(ID id) {
		T willBeDeletedEntity = null;

		CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
		Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
		List<T> resultList = entityManager.createQuery(criteriaQuery).getResultList();

		if (!resultList.isEmpty()) {
			willBeDeletedEntity = (T) resultList.get(0);
			try {
				openSession();
				session.delete(willBeDeletedEntity);
				closeSuccess();
			} catch (Exception e) {
				closeError();
			}
		}
	}

	@Override
	public void delete(T entity) {
		try {
			openSession();
			session.delete(entity);
			closeSuccess();
		} catch (Exception e) {
			closeError();
			throw e;
		}

	}

	@Override
	public Optional<T> findById(ID id) {

		CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
		Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
		List<T> resultList = entityManager.createQuery(criteriaQuery).getResultList();

		if (!resultList.isEmpty()) {
			return Optional.of(resultList.get(0));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public boolean existById(ID id) {

		try {
			CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
			Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
			List<T> resultList = entityManager.createQuery(criteriaQuery).getResultList();
			return !resultList.isEmpty();

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<T> findAll() {

		CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
		Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
		criteriaQuery.select(root);

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<T> findByColumnAndValue(String column, Object value) {

		CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
		Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
		criteriaQuery.select(root);

		criteriaQuery.where(criteriaBuilder.equal(root.get(column), value));

		return entityManager.createQuery(criteriaQuery).getResultList();
	}

	@Override
	public List<T> findByEntity(T entity) {

		List<T> resultList = null;
		Class myClass = entity.getClass();
		Field[] fields = myClass.getDeclaredFields();

		try {
			CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>) criteriaBuilder.createQuery(t.getClass());
			Root<T> root = (Root<T>) criteriaQuery.from(t.getClass());
			criteriaQuery.select(root);

			List<Predicate> list = new ArrayList<Predicate>();

			for (int i = 0; i < fields.length; i++) {

				fields[i].setAccessible(true);
				if (fields[i].get(t) != null && !fields[i].getName().equals("id")) {
					if (fields[i].getType().isAssignableFrom(String.class))
						list.add(criteriaBuilder.like(root.get(fields[i].getName()), "%" + fields[i].get(t) + "%"));
					else
						list.add(criteriaBuilder.equal(root.get(fields[i].getName()), fields[i].get(t)));
				}
			}
			criteriaQuery.where(list.toArray(new Predicate[] {}));
			resultList = entityManager.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			System.out.println("findByEntiy Error.....: " + e.getLocalizedMessage());
		}

		return resultList;
	}

}
