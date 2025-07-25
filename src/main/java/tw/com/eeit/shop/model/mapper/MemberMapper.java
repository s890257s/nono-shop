package tw.com.eeit.shop.model.mapper;

import org.springframework.beans.BeanUtils;

import tw.com.eeit.shop.model.dto.LoggedInMember;
import tw.com.eeit.shop.model.entity.Member;
import tw.com.eeit.shop.utils.CommonUtil;
import tw.com.eeit.shop.utils.JwtUtil;

public class MemberMapper {

	public static LoggedInMember memberToLoggedInMember(Member entity) {

		LoggedInMember dto = new LoggedInMember();
		BeanUtils.copyProperties(entity, dto);

		String base64Image = CommonUtil.getBase64Image(entity.getMemberPhoto());
		dto.setEmail(base64Image);

		dto.setToken(JwtUtil.generateToken(entity.getMemberId()));

		return dto;
	}
}
