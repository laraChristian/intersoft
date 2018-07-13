package co.com.foundation.intersoft.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "inventory")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Inventory {

	@Id
	private String _id;
	private Long number;
	private Date creationDate;
	private Area area;
	private List<Inventory> inventories;

	public Inventory() {
		super();
	}
}
