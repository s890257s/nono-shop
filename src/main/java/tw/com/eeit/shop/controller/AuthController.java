package tw.com.eeit.shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.com.eeit.shop.model.dto.EmailAndPassword;
import tw.com.eeit.shop.model.dto.LoggedInMember;
import tw.com.eeit.shop.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<LoggedInMember> login(@RequestBody EmailAndPassword emailAndPasswordDto) {

		LoggedInMember loggedInMemberDto = authService.login(emailAndPasswordDto);

		return ResponseEntity.ok().header("Authorization", "Bearer " + loggedInMemberDto.getToken())
				.body(loggedInMemberDto);
	}

}
