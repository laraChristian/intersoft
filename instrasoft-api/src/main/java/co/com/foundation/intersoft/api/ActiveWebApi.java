package co.com.foundation.intersoft.api;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.foundation.intersoft.business.ActiveBoundary;
import co.com.foundation.intersoft.exceptions.ActiveAssignedException;
import co.com.foundation.intersoft.exceptions.ActiveNotAvailableException;
import co.com.foundation.intersoft.exceptions.DischargeDateException;
import co.com.foundation.intersoft.exceptions.EmptyFieldsException;
import co.com.foundation.intersoft.exceptions.PersistenceException;
import co.com.foundation.intersoft.messages.ActiveRequest;
import co.com.foundation.intersoft.messages.AssignedRequest;
import co.com.foundation.intersoft.messages.GenericResponse;

@RestController
@RequestMapping(value = "/intrasoft-api", produces = "application/json")
public class ActiveWebApi {

	private final Logger LOGGER = LogManager.getLogger(ActiveWebApi.class);

	@Autowired
	private ActiveBoundary boundary;

	@RequestMapping(value = "/create-active", method = RequestMethod.POST)
	public ResponseEntity<GenericResponse> createActive(@RequestBody final ActiveRequest request) {

		LOGGER.info("start -- create-active method");
		try {
			boundary.createActive(request);
			return ResponseEntity.status(HttpStatus.OK).body(GenericResponse.builder().build());
		} catch (PersistenceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} catch (EmptyFieldsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} catch (DischargeDateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} finally {
			LOGGER.info("end -- create-active method -- ");
		}
	}

	@RequestMapping(value = "/list-actives", method = RequestMethod.GET)
	public ResponseEntity<GenericResponse> listActives() {
		try {
			LOGGER.info("start -- list-actives method");
			return ResponseEntity.status(HttpStatus.OK)
					.body(GenericResponse.builder().actives(boundary.listAll()).build());
		} catch (PersistenceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} finally {
			LOGGER.info("end -- list-actives method");
		}
	}

	@RequestMapping(value = "/update-actives", method = RequestMethod.PUT)
	public ResponseEntity<GenericResponse> updateActive(@RequestBody final ActiveRequest request) {
		LOGGER.info("start -- update-actives method");
		try {
			boundary.updateActive(request);
			return ResponseEntity.status(HttpStatus.OK).body(GenericResponse.builder().build());
		} catch (PersistenceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} catch (EmptyFieldsException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} catch (DischargeDateException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} catch (ActiveAssignedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} finally {
			LOGGER.info("end -- update-active method -- ");
		}
	}

	@RequestMapping(value = "/delete-active", method = RequestMethod.DELETE)
	public ResponseEntity<GenericResponse> deleteActive(@RequestBody final ActiveRequest request) {
		try {
			LOGGER.info("start -- list-actives method");
			boundary.deleteActive(request.getId());
			return ResponseEntity.status(HttpStatus.OK).body(GenericResponse.builder().build());
		} catch (PersistenceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} catch (ActiveAssignedException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} finally {
			LOGGER.info("end -- delete-active method -- status: {}");
		}
	}

	@RequestMapping(value = "/assign-active", method = RequestMethod.POST)
	public ResponseEntity<GenericResponse> assignActive(@RequestBody final AssignedRequest request) {
		try {
			LOGGER.info("start -- assign-active method");
			boundary.assignActive(request);
			return ResponseEntity.status(HttpStatus.OK).body(GenericResponse.builder().build());
		} catch (PersistenceException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} catch (ActiveNotAvailableException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(GenericResponse.builder().message(e.getMessage()).build());
		} finally {
			LOGGER.info("end -- delete-active method -- status: {}");
		}
	}

}
