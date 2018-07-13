package co.com.foundation.intersoft.validators;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.foundation.intersoft.domain.Active;
import co.com.foundation.intersoft.domain.Assignation;
import co.com.foundation.intersoft.exceptions.ActiveNotAvailableException;
import co.com.foundation.intersoft.exceptions.PersistenceException;
import co.com.foundation.intersoft.messages.AssignedRequest;
import co.com.foundation.intersoft.repository.impl.ActivePersistenceDAO;
import co.com.foundation.intersoft.repository.impl.AreaPersistenceDAO;
import co.com.foundation.intersoft.repository.impl.EmployeePersistenceDAO;
import co.com.foundation.intersoft.utils.ActiveUtils;

@Service
public class AssignedValidator {

	private final Logger LOGGER = LogManager.getLogger(AssignedValidator.class);

	@Autowired
	private ActivePersistenceDAO activeDAO;

	@Autowired
	private AreaPersistenceDAO areaDAO;

	@Autowired
	private EmployeePersistenceDAO employeeDAO;

	public boolean validate(final AssignedRequest assignedRequest)
			throws ActiveNotAvailableException, PersistenceException {
		LOGGER.info("start -- validate method");
		Assignation assignation = assignedRequest.getAssignation();

		try {
			if (!activeDAO.exist(assignation.getActive().getSerial())) {
				throw new NoSuchElementException("Error - active doesn't exist");
			}

			if (!isAvailable(activeDAO.findAll(), assignation.getActive().getSerial())) {
				throw new ActiveNotAvailableException("Error - active doesn't available");
			}

			if (assignedRequest.getOption().equals("AREA")) {
				if (!areaDAO.exist(assignation.getArea()))
					throw new NoSuchElementException("Error area doesn't exist");
			}

			if (assignedRequest.getOption().equals("EMPLOYEE")) {
				if (!employeeDAO.exist(assignation.getEmployee()))
					throw new NoSuchElementException("Error employee doesn't exist");
			}
		} catch (NoSuchElementException e) {
			LOGGER.info(e.getMessage());
			throw e;
		} catch (ActiveNotAvailableException e) {
			LOGGER.info(e.getMessage());
			throw e;
		} catch (PersistenceException e) {
			LOGGER.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			throw new PersistenceException("Error - while validating assignation", e);
		}
		LOGGER.info("end -- validate method");
		return true;
	}

	private boolean isAvailable(final List<Active> actives, final String serial) {
		LOGGER.info("start -- is.available method");
		Optional<Active> activeAvaliable = actives.stream().filter((active) -> active.getSerial().equals(serial))
				.peek(LOGGER::info).filter(ActiveUtils.isActiveOrAvailable).findFirst();
		return activeAvaliable.isPresent();
	}

}
