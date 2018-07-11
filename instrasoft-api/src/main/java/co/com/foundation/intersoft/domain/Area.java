package co.com.foundation.intersoft.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "area")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Area {

	@Id
	private long _id;
	private String name;
	private City city;
	private List<Employee> employees;
	private List<Active> actives;
}
