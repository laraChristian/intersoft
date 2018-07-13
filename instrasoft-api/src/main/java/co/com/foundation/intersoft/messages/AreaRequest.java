package co.com.foundation.intersoft.messages;

import co.com.foundation.intersoft.domain.Area;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AreaRequest {

	private Long id;
	private Area area;
}
