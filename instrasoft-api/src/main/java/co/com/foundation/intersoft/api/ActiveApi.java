package co.com.foundation.intersoft.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.com.foundation.intersoft.messages.ActiveRequest;
import co.com.foundation.intersoft.messages.GenericResponse;
import co.com.foundation.intersoft.repository.impl.ActiveCRUD;

@RestController
@RequestMapping(value = "/test-api", produces = "application/json")
public class ApiTest {

	private final Logger LOGGER = LogManager.getLogger(ApiTest.class);

	@Autowired
	private ActiveCRUD crud;

	@RequestMapping(value = "/create-active", method = RequestMethod.POST)
	public ResponseEntity<GenericResponse> createActive(final ActiveRequest request) {
		LOGGER.info("start -- create-active method");
		return ResponseEntity.ok().body(crud.create(request));
	}

	@RequestMapping(value = "/list-actives", method = RequestMethod.GET)
	public ResponseEntity<GenericResponse> listActives() {
		LOGGER.info("start -- list-actives method");
		return ResponseEntity.ok().body(crud.read(null));
	}

	@RequestMapping(value = "/say-hello", method = RequestMethod.GET)
	public ResponseEntity<String> sayHello() {
		LOGGER.info("start -- create-active method");
		return ResponseEntity.ok().body("Hello");
	}
}
