package co.com.foundation.intersoft.utils;

import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.com.foundation.intersoft.domain.Active;
import co.com.foundation.intersoft.exceptions.EmptyFieldsException;

public class ActiveUtils {

	private static final Logger LOGGER = LogManager.getLogger(ActiveUtils.class);

	private enum assigendOptions {
		AREA, EMPLOYEE
	}

	public static Predicate<Active> isActiveOrAvailable = (Active active) -> {
		LOGGER.info("id -> {}", active.getStatus().get_id());
		return active.getStatus().get_id() == 1 || active.getStatus().get_id() == 4;
	};

	public static Predicate<String> toAssignToArea = (option) -> option.toUpperCase()
			.equals(assigendOptions.AREA.toString());

	public static Predicate<String> toAssignToEmployee = (option) -> option.toUpperCase()
			.equals(assigendOptions.EMPLOYEE.toString());

	public static Predicate<Active> hasArea = (Active active) -> active.getArea() != null
			&& active.getArea().get_id() > 0;

	public static Predicate<Active> hasStatus = (Active active) -> active.getStatus() != null
			&& active.getStatus().get_id() > 0;

	public static Predicate<Active> hasInventory = (Active active) -> active.getInventory() != null
			&& active.getInventory().get_id() > 0;

	public static Predicate<Active> hasType = (Active active) -> active.getType() != null
			&& active.getType().get_id() > 0;

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
