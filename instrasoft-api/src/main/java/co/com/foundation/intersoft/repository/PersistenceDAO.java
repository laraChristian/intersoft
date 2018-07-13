package co.com.foundation.intersoft.repository;

import java.util.List;
import java.util.NoSuchElementException;

import co.com.foundation.intersoft.exceptions.ActiveAssignedException;
import co.com.foundation.intersoft.exceptions.DischargeDateException;
import co.com.foundation.intersoft.exceptions.EmptyFieldsException;
import co.com.foundation.intersoft.exceptions.PersistenceException;

public interface PersistenceDAO<I, O> {

	void create(final I input) throws PersistenceException, EmptyFieldsException, DischargeDateException;

	List<O> findAll() throws PersistenceException;

	void update(final I input)
			throws PersistenceException, EmptyFieldsException, DischargeDateException, NoSuchElementException, ActiveAssignedException;

	void delete(final String id) throws PersistenceException, NoSuchElementException, ActiveAssignedException;

}
