package co.com.foundation.intersoft.business;

import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.foundation.intersoft.domain.Active;
import co.com.foundation.intersoft.exceptions.ActiveAssignedException;
import co.com.foundation.intersoft.exceptions.ActiveNotAvailableException;
import co.com.foundation.intersoft.exceptions.DischargeDateException;
import co.com.foundation.intersoft.exceptions.EmptyFieldsException;
import co.com.foundation.intersoft.exceptions.PersistenceException;
import co.com.foundation.intersoft.messages.ActiveRequest;
import co.com.foundation.intersoft.messages.AssignedRequest;
import co.com.foundation.intersoft.repository.impl.ActivePersistenceDAO;
import co.com.foundation.intersoft.validators.AssignedValidator;

@Service
public class ActiveBoundary {

	private final Logger LOGGER = LogManager.getLogger(ActiveBoundary.class);

	@Autowired
	private ActivePersistenceDAO dao;

	@Autowired
	private AssignedValidator assignedValidator;

	public void createActive(final ActiveRequest activeRequest)
			throws PersistenceException, EmptyFieldsException, DischargeDateException {
		LOGGER.info("start -- create active method ");
		dao.create(activeRequest);
		LOGGER.info("end -- create active method ");
	}

	public void updateActive(final ActiveRequest activeRequest)
			throws PersistenceException, EmptyFieldsException, DischargeDateException, NoSuchElementException, ActiveAssignedException {
		LOGGER.info("start -- create active method ");
		dao.update(activeRequest);
		LOGGER.info("end -- create active method ");
	}

	public List<Active> listAll() throws PersistenceException {
		LOGGER.info("start -- list-all method ");
		return dao.findAll();
	}

	public void deleteActive(final String id)
			throws PersistenceException, NoSuchElementException, ActiveAssignedException {
		LOGGER.info("start -- delete-active method ");
		dao.delete(id);
	}

	public void assignActive(final AssignedRequest request)
			throws PersistenceException, ActiveNotAvailableException, NoSuchElementException {
		LOGGER.info("start -- assign-active method ");
		if (assignedValidator.validate(request)) {
			dao.assignActive(request);
		}
		LOGGER.info("end -- assign-active method ");
	}

}
