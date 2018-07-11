package co.com.foundation.intersoft.repository;

import org.springframework.data.repository.Repository;

import co.com.foundation.intersoft.exceptions.SystemException;

public interface CRUD<I, O> extends Repository<I, Long> {

	O create(final I input) throws SystemException;

	O read(final I input) throws SystemException;

	O update(final I input) throws SystemException;

	O delete(final Long id) throws SystemException;

}
