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

import co.com.foundation.intersoft.domain.Area;
import co.com.foundation.intersoft.exceptions.DischargeDateException;
import co.com.foundation.intersoft.exceptions.EmptyFieldsException;
import co.com.foundation.intersoft.exceptions.PersistenceException;
import co.com.foundation.intersoft.messages.AreaRequest;
import co.com.foundation.intersoft.repository.PersistenceDAO;

@Repository
public class AreaPersistenceDAO implements PersistenceDAO<AreaRequest, Area> {

	private final Logger LOGGER = LogManager.getLogger(ActivePersistenceDAO.class);

	@Autowired
	private MongoOperations mo;

	@Override
	public void create(AreaRequest input) throws PersistenceException, EmptyFieldsException, DischargeDateException {
	}

	@Override
	public List<Area> findAll() throws PersistenceException {
		LOGGER.info("start -- find-all method");
		return mo.findAll(Area.class);
	}

	@Override
	public void update(AreaRequest input)
			throws PersistenceException, EmptyFieldsException, DischargeDateException, NoSuchElementException {
	}

	@Override
	public void delete(final String id) throws PersistenceException, NoSuchElementException {
	}

	public boolean exist(final String id) {
		LOGGER.info("start -- exist-area method for id {}", id);
		Query query = Query.query(Criteria.where("_id").is(id));
		return mo.exists(query, Area.class);
	}
}
