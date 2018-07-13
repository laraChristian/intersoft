package co.com.foundation.intersoft.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "assignation")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Assignation {

	private String _id;
	private String employee;
	private String area;
	private Active active;
	private Date date;

	public Assignation() {
		super();
	}

}
