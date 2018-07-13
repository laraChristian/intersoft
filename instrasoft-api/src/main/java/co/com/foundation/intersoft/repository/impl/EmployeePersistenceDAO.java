package co.com.foundation.intersoft.repository.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import co.com.foundation.intersoft.domain.Employee;
import co.com.foundation.intersoft.exceptions.DischargeDateException;
import co.com.foundation.intersoft.exceptions.EmptyFieldsException;
import co.com.foundation.intersoft.exceptions.PersistenceException;
import co.com.foundation.intersoft.messages.EmployeeRequest;
import co.com.foundation.intersoft.repository.PersistenceDAO;

@Repository
public class EmployeePersistenceDAO implements PersistenceDAO<EmployeeRequest, Employee> {

	private final Logger LOGGER = LogManager.getLogger(EmployeePersistenceDAO.class);

	@Autowired
	private MongoOperations mo;

	@Override
	public void create(EmployeeRequest input)
			throws PersistenceException, EmptyFieldsException, DischargeDateException {
	}

	@Override
	public List<Employee> findAll() throws PersistenceException {
		return null;
	}

	@Override
	public void update(EmployeeRequest input)
			throws PersistenceException, EmptyFieldsException, DischargeDateException, NoSuchElementException {
	}

	@Override
	public void delete(final String id) throws PersistenceException, NoSuchElementException {

	}

	public boolean exist(Long id) {
		LOGGER.info("start -- exist method to id: {}", id);
		Query query = Query.query(Criteria.where("_id").is(id));
		return mo.exists(query, Employee.class);
	}

}
