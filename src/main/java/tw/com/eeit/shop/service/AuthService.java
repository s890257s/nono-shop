package tw.com.eeit.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import tw.com.eeit.shop.model.dto.LoggedInMember;
import tw.com.eeit.shop.model.entity.Member;
import tw.com.eeit.shop.model.mapper.MemberMapper;
import tw.com.eeit.shop.repository.MemberRepository;

@Service
public class AuthService {

	@Autowired
	private MemberRepository memberRepository;

	/**
	 * 根據 ID 查找登入使用者資訊
	 */
	public LoggedInMember getMemberById(Integer id) {
		Member member = memberRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("找不到使用者 id: " + id));
		LoggedInMember loggedInMember = MemberMapper.memberToLoggedInMember(member);
		return loggedInMember;
	}

	/**
	 * 根據 ID 查找登入使用者資訊，多載方法，讓呼叫者無須自己實作轉型
	 */
	public LoggedInMember getMemberById(String id) {
		return getMemberById(Integer.parseInt(id));
	}

	/**
	 * 取得當前登入的使用者
	 */
	public LoggedInMember getLoggedInMember() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal == null) {
			throw new RuntimeException("使用者尚未登入");
		}

		LoggedInMember loggedInMember = (LoggedInMember) principal;

		return loggedInMember;
	}

}
