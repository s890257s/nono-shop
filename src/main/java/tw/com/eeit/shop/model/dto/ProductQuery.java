package tw.com.eeit.shop.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQuery {

	private String name;
	private Integer min;
	private Integer max;
	private String colors;

}
