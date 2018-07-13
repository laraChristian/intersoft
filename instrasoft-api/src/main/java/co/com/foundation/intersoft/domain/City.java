package co.com.foundation.intersoft.domain;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "city")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class City {

	@Id
	private String _id;
	private String name;
	private List<Area> areas;
}
