package co.com.foundation.intersoft.messages;

import co.com.foundation.intersoft.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class EmployeeRequest {

	private long _id;
	private Employee employee;

	public EmployeeRequest() {
		super();
	}

}
