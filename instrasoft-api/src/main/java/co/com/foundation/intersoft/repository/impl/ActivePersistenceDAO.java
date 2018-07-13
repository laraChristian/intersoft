package co.com.foundation.intersoft.repository.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import co.com.foundation.intersoft.domain.Active;
import co.com.foundation.intersoft.domain.Area;
import co.com.foundation.intersoft.domain.Assignation;
import co.com.foundation.intersoft.domain.Employee;
import co.com.foundation.intersoft.domain.Status;
import co.com.foundation.intersoft.domain.Type;
import co.com.foundation.intersoft.exceptions.ActiveAssignedException;
import co.com.foundation.intersoft.exceptions.ActiveNotAvailableException;
import co.com.foundation.intersoft.exceptions.DischargeDateException;
import co.com.foundation.intersoft.exceptions.EmptyFieldsException;
import co.com.foundation.intersoft.exceptions.PersistenceException;
import co.com.foundation.intersoft.messages.ActiveRequest;
import co.com.foundation.intersoft.messages.AssignedRequest;
import co.com.foundation.intersoft.repository.PersistenceDAO;
import co.com.foundation.intersoft.utils.ActiveUtils;
import co.com.foundation.intersoft.utils.DateUtils;

@Repository
public class ActivePersistenceDAO implements PersistenceDAO<ActiveRequest, Active> {

	private final Logger LOGGER = LogManager.getLogger(ActivePersistenceDAO.class);

	@Autowired
	private MongoOperations mo;

	@Override
	public void create(final ActiveRequest input)
			throws PersistenceException, EmptyFieldsException, DischargeDateException {
		try {
			LOGGER.info("start create method for active {}");
			input.getActive().set_id(UUID.randomUUID().toString());

			if (!ActiveUtils.hasParametersDefined(input.getActive())) {
				throw new EmptyFieldsException("Error active has empty fields");
			}

			if (DateUtils.isBefore(input.getActive().getDateOfPurchase(), input.getActive().getDischargeDate())) {
				throw new DischargeDateException("Error discharge date no is less than date of purchase");
			}

			mo.insert(input.getActive());
		} catch (EmptyFieldsException e) {
			LOGGER.error(e.getMessage());
			throw e;
		} catch (DischargeDateException e) {
			LOGGER.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.error("Error while creating active object", e);
			throw new PersistenceException("Error while creating active object: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Active> findAll() throws PersistenceException {
		try {
			LOGGER.info("start findAll method for active {}");
			List<Active> actives = mo.findAll(Active.class);
			actives.forEach((active) -> {
				active.setType(
						mo.findOne(Query.query(Criteria.where("_id").is(active.getType().get_id())), Type.class));
				active.setStatus(
						mo.findOne(Query.query(Criteria.where("_id").is(active.getStatus().get_id())), Status.class));

				active.setArea(
						mo.findOne(Query.query(Criteria.where("_id").is(active.getArea().get_id())), Area.class));
			});
			return actives;
		} catch (Exception e) {
			LOGGER.error("Error while querying active objects", e);
			throw new PersistenceException("Error while querying active objects", e);
		}
	}

	@Override
	public void update(final ActiveRequest request) throws PersistenceException, EmptyFieldsException,
			DischargeDateException, NoSuchElementException, ActiveAssignedException {

		try {
			Active active = request.getActive();

			if (!ActiveUtils.hasSerialAndDate(request.getActive())) {
				throw new EmptyFieldsException("Error - fields required not provided");
			}

			String serial = StringUtils.isBlank(request.getOldSerial()) ? request.getActive().getSerial()
					: request.getOldSerial();
			if (!exist(serial)) {
				throw new NoSuchElementException("Error - active doesn't exist");
			}

			Active temporary = mo.findOne(Query.query(Criteria.where("_id").is(request.getActive().get_id())),
					Active.class);
			if (temporary.getStatus().get_id().equals(ActiveUtils.status.ASSIGNED.getId())) {
				throw new ActiveAssignedException("Error - active is assigned, should be unassigned to modify status");
			}

			if (DateUtils.isBefore(getDateOfPurchase(request.getActive().get_id()), active.getDischargeDate())) {
				throw new DischargeDateException("Error discharge date no is less than date of purchase");
			}

			Query query = Query.query(Criteria.where("_id").is(request.getActive().get_id()));
			Update update = new Update();
			update.set("serial", active.getSerial());
			update.set("dischargeDate", active.getDischargeDate());
			update.set("status", active.getStatus());
			mo.updateFirst(query, update, Active.class);

		} catch (EmptyFieldsException e) {
			LOGGER.error(e.getMessage());
			throw e;
		} catch (DischargeDateException e) {
			LOGGER.error(e.getMessage());
			throw e;
		} catch (NoSuchElementException e) {
			LOGGER.error(e.getMessage());
			throw e;
		} catch (ActiveAssignedException e) {
			LOGGER.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.error("Error while merging active object", e);
			throw new PersistenceException("Error while merging active object", e);
		}
	}

	@Override
	public void delete(final String id) throws PersistenceException, NoSuchElementException, ActiveAssignedException {
		try {
			LOGGER.info("start -- delete method for active {}", id);

			Query query = Query.query(Criteria.where("_id").is(id));

			if (!mo.exists(query, Active.class)) {
				throw new NoSuchElementException("Error - active doesn't exist");
			}

			if (mo.findOne(query, Active.class).getStatus().get_id().equals(ActiveUtils.status.ASSIGNED.getId())) {
				throw new ActiveAssignedException("Error - active is assigned");
			}
			mo.remove(query, Active.class);
		} catch (NoSuchElementException e) {
			LOGGER.error(e.getMessage());
			throw e;
		} catch (ActiveAssignedException e) {
			LOGGER.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			LOGGER.error("Error while removing active object", e);
			throw new PersistenceException("Error while removing active object", e);
		}
	}

	public void assignActive(final AssignedRequest request)
			throws PersistenceException, NoSuchElementException, ActiveNotAvailableException {
		try {
			Assignation assignation = request.getAssignation();
			Query getBySerial = Query.query(Criteria.where("serial").is(assignation.getActive().getSerial()));
			Active active = mo.findOne(getBySerial, Active.class);
			active.setStatus(Status.builder()._id("st5").build());
			update(ActiveRequest.builder().active(active).build());

			if (ActiveUtils.toAssignToArea.test(request.getOption())) {
				assignation.setEmployee("");
				Query getById = Query.query(Criteria.where("_id").is(assignation.getArea()));
				Area area = mo.findOne(getById, Area.class);
				if (area.getActives() != null) {
					area.getActives().add(active);
				} else {
					area.setActives(Arrays.asList(active));
				}
				mo.updateFirst(getById, Update.update("actives", area.getActives()), Area.class);
			}

			if (ActiveUtils.toAssignToEmployee.test(request.getOption())) {
				assignation.setArea("");
				Query getById = Query.query(Criteria.where("_id").is(assignation.getEmployee()));
				Employee employee = mo.findOne(getById, Employee.class);
				if (employee.getActives() != null) {
					employee.getActives().add(active);
				} else {
					employee.setActives(Arrays.asList(active));
				}
				mo.updateFirst(getById, Update.update("actives", employee.getActives()), Employee.class);
			}
			assignation.set_id(UUID.randomUUID().toString());
			mo.insert(assignation);
		} catch (Exception e) {
			throw new PersistenceException("Error assigning active object: " + e.getMessage(), e);
		}
	}

	public boolean exist(final String serial) {
		LOGGER.info("start -- exist-active method for id {}", serial);
		Query query = Query.query(Criteria.where("serial").is(serial));
		return mo.exists(query, Active.class);
	}

	private Date getDateOfPurchase(final String id) {
		LOGGER.info("start -- get-date-of-purchase method for active {}", id);
		Query getPurchaseDate = new Query();
		getPurchaseDate.fields().include("dateOfPurchase");
		getPurchaseDate.addCriteria(Criteria.where("_id").is(id));
		return mo.findOne(getPurchaseDate, Active.class).getDateOfPurchase();
	}

}
