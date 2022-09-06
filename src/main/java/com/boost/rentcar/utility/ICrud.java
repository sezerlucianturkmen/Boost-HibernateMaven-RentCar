package com.boost.rentcar.utility;

import java.util.List;
import java.util.Optional;

public interface ICrud<T, ID> extends IRepository<T, ID> {

	<S extends T> S save(S entity);

	<S extends T> Iterable<S> save(Iterable<S> entities);

	void deleteById(ID id);

	void delete(T entity);

	Optional<T> findById(ID id);

	boolean existById(ID id);

	List<T> findAll();

	List<T> findByColumnAndValue(String column, Object value);

	List<T> findByEntity(T entity);

}
