package tw.com.eeit.shop.utils;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {

	private static final SecretKey SECRET_KEY = Keys
			.hmacShaKeyFor("ThisIsMySuperSecretAndRidiculouslyLongUltraConfidentialToken".getBytes());
	private static final int EXPIRATION_IN_SECONDS = 60 * 60;

	/**
	 * 產生 JWT Token。
	 *
	 * @param memberId 使用者的唯一識別碼，將作為 JWT Token 的 subject。
	 * @return 生成的 JWT Token 字串。
	 */
	public static String generateToken(String memberId) {
		return Jwts.builder() // 使用 builder 模式設定 token
				.subject(memberId) // 設定 token 主體(Subject)，通常存放使用者的 ID
				.issuedAt(new Date()) // 設定 token 發行時間
				.expiration(new Date(System.currentTimeMillis() + EXPIRATION_IN_SECONDS * 1000)) // 設定 token 到期日期
				.signWith(SECRET_KEY) // 使用密鑰對 token 進行簽名
				.compact(); // 生成 JWT token
	}

	/**
	 * 產生 JWT Token。
	 *
	 * @param memberId 使用者的唯一識別碼，將作為 JWT Token 的 subject。
	 * @return 生成的 JWT Token 字串。
	 */
	public static String generateToken(Integer memberId) {
		return generateToken(memberId.toString());
	}

	/**
	 * 內部私有方法，使用密鑰驗證 token 後，解密取得 Claims
	 * 
	 */
	private static Claims getClaims(String token) {
		return Jwts.parser() // 使用 parser() 取得解析器
				.verifyWith(SECRET_KEY) // 設定解密用密鑰
				.build() // 建立解析器
				.parseSignedClaims(token) // 解析 token
				.getPayload(); // 取得解析後結果
	}

	/**
	 * 取得 token 的主體。
	 * 
	 */
	public static String getSubject(String token) {
		return getClaims(token).getSubject();
	}

	/**
	 * 從 token 中取得自定義的 payload
	 */
	public static String getValue(String token, String key) {
		return (String) getClaims(token).get(key);
	}

	/**
	 * 檢驗 token 簽名(signature)是否正確。
	 */
	public static Boolean isTokenValid(String token) {
		getClaims(token); // 若 token 有任何異常，則由 jjwt 套件直接拋出錯誤。

		return true; // 能走到回傳表示驗證通過，token 合法
	}

}
