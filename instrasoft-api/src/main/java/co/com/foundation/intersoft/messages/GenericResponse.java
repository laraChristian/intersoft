package co.com.foundation.intersoft.messages;

import java.util.List;

import co.com.foundation.intersoft.domain.Active;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GenericResponse {

	private String details;
	private List<Active> actives;

}
