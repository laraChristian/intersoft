package co.com.foundation.intersoft.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "employees")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Employee {

	@Id
	private String _id;
	private String firstName;
	private String lastName;
	private String identification;
	private Area area;
	private List<Active> actives;

	public Employee() {
		super();
	}

}
