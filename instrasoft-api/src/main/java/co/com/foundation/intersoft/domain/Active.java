package co.com.foundation.intersoft.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "active")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Active {

	@Id
	private long _id;
	private String name;
	private String description;
	private String serial;
	private double weight;
	private double width;
	private double height;
	private double length;
	private double price;
	private Date dateOfPurchase;
	private Date dischargeDate;
	private Status status;
	private String color;
	private Inventory inventory;
	private Type type;
	private Area area;

	public Active() {
		super();
	}

}
