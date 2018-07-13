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
	private String _id;
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
	private Type type;
	private Area area;
	private Inventory inventory;

	public Active() {
		super();
	}

	@Override
	public String toString() {
		return "Active [_id=" + _id + ", name=" + name + ", description=" + description + ", serial=" + serial
				+ ", weight=" + weight + ", width=" + width + ", height=" + height + ", length=" + length + ", price="
				+ price + ", dateOfPurchase=" + dateOfPurchase + ", dischargeDate=" + dischargeDate + ", status="
				+ status + ", color=" + color + ", type=" + type + ", area=" + area + ", inventory=" + inventory + "]";
	}

}
