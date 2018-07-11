package co.com.foundation.intersoft.repository.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import co.com.foundation.intersoft.domain.Active;
import co.com.foundation.intersoft.messages.ActiveRequest;
import co.com.foundation.intersoft.messages.GenericResponse;
import co.com.foundation.intersoft.repository.CRUD;

@Repository
public class ActiveCRUD implements CRUD<ActiveRequest, GenericResponse> {

	private final Logger LOGGER = LogManager.getLogger(ActiveCRUD.class);

	@Autowired
	private MongoOperations mo;

	public ActiveCRUD() {
		super();
	}

	@Override
	public GenericResponse create(ActiveRequest input) {
		LOGGER.info("start create method for active {}", input.getActive().get_id());
		LOGGER.info("MongoOperations: {}", mo);
		return GenericResponse.builder().details("ok").build();
	}

	@Override
	public GenericResponse read(ActiveRequest input) {
		LOGGER.info("start read method for active {}");
		List<Active> actives = mo.findAll(Active.class);
		return GenericResponse.builder().actives(actives).build();
	}

	@Override
	public GenericResponse update(ActiveRequest input) {
		return null;
	}

	@Override
	public GenericResponse delete(Long id) {
		return null;
	}

}
