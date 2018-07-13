package co.com.foundation.intersoft.messages;

import co.com.foundation.intersoft.domain.Active;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ActiveRequest {

	private String id;
	private String oldSerial;
	private Active active;

	public ActiveRequest() {
		super();
	}
}
