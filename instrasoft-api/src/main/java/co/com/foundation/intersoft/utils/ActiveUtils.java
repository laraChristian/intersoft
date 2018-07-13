package co.com.foundation.intersoft.utils;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.com.foundation.intersoft.domain.Active;
import co.com.foundation.intersoft.exceptions.EmptyFieldsException;

public class ActiveUtils {

	private static final Logger LOGGER = LogManager.getLogger(ActiveUtils.class);

	public enum status {
		ACTIVE("st1"), WRITTENOFF("st2"), REPARATION("st3"), AVAILABLE("st4"), ASSIGNED("st5");

		private status(String id) {
			this.id = id;
		}

		String id;

		public String getId() {
			return id;
		}
	}

	private enum assigendOptions {
		AREA, EMPLOYEE
	}

	public static Predicate<Active> isActiveOrAvailable = (Active active) -> {
		LOGGER.info("id -> {}", active.getStatus().get_id());
		return active.getStatus().get_id().equals("st1") || active.getStatus().get_id().equals("st4");
	};

	public static Predicate<String> toAssignToArea = (option) -> option.toUpperCase()
			.equals(assigendOptions.AREA.toString());

	public static Predicate<String> toAssignToEmployee = (option) -> option.toUpperCase()
			.equals(assigendOptions.EMPLOYEE.toString());

	public static Predicate<Active> hasArea = (Active active) -> active.getArea() != null
			&& StringUtils.isNotBlank(active.getArea().get_id());

	public static Predicate<Active> hasStatus = (Active active) -> active.getStatus() != null
			&& StringUtils.isNoneBlank(active.getStatus().get_id());

	public static Predicate<Active> hasInventory = (Active active) -> active.getInventory() != null
			&& StringUtils.isNotBlank(active.get_id());

	public static Predicate<Active> hasType = (Active active) -> active.getType() != null
			&& StringUtils.isNotBlank(active.getType().get_id());

	public static boolean hasParametersDefined(final Active active) throws EmptyFieldsException {
		return !StringUtils.isBlank(active.getDescription()) && !StringUtils.isBlank(active.getName())
				&& !StringUtils.isBlank(active.getSerial()) && !StringUtils.isBlank(active.getColor())
				&& active.getDischargeDate() != null && active.getDateOfPurchase() != null && active.getHeight() > 0
				&& active.getWidth() > 0 && active.getLength() > 0 && active.getPrice() > 0 && hasStatus.test(active)
				&& hasArea.test(active) && hasInventory.test(active) && hasType.test(active);
	}

	public static boolean hasSerialAndDate(final Active active) {
		return StringUtils.isNotBlank(active.getSerial()) && active.getDischargeDate() != null;
	}

}
