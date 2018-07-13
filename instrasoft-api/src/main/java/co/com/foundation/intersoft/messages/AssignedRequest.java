package co.com.foundation.intersoft.messages;

import co.com.foundation.intersoft.domain.Assignation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AssignedRequest {

	private Assignation assignation;
	private String option;

	public AssignedRequest() {
		super();
	}

}
