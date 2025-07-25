package tw.com.eeit.shop.model.mapper;

import org.springframework.beans.BeanUtils;

import tw.com.eeit.shop.model.dto.LoggedInMember;
import tw.com.eeit.shop.model.entity.Member;

public class MemberMapper {

	public static LoggedInMember memberToLoggedInMember(Member entity) {

		LoggedInMember dto = new LoggedInMember();
		BeanUtils.copyProperties(entity, dto);

		return dto;
	}
}
