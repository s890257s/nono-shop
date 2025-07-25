package tw.com.eeit.shop.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggedInMember {

	private Integer memberId;
	private String memberName;
	private Boolean isAdmin;
	private String email;
	private byte[] memberPhoto;
	private String token;

}
