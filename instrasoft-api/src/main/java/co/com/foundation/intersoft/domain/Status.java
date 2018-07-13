package co.com.foundation.intersoft.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "status")
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Status {

	@Id
	private String _id;
	private String name;

	public Status() {
		super();
	}

}
