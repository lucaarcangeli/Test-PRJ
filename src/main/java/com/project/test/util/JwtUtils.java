package com.project.test.util;

import java.util.Date;
import java.util.UUID;

import com.project.test.model.TUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;

/**
 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
 */
public class JwtUtils {

	private final static String JWT_AUTH_KEY = "GrdzP7b23frkwEZscGJ5KmNRHARRyH6YCcgV4JRertSqeM4Jg";


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param user
	 *            Unique existent TUser object entity wrapper
	 * @return User related authentication JWT
	 * @throws Exception
	 */
	public static String getAuthToken(TUser user) {

		JwtBuilder jwToken = Jwts.builder();
		jwToken.setSubject(user.getUsername());
		jwToken.setIssuer("escgroup.com");
		jwToken.setAudience("WEB");
		jwToken.setIssuedAt(new Date());
		jwToken.setId(UUID.randomUUID().toString());
		jwToken.claim("userId", user.getId());

		String token = jwToken.compressWith(new GzipCompressionCodec()).signWith(SignatureAlgorithm.HS512, JWT_AUTH_KEY).compact();

		return token;
	}


	/**
	 * @author Luca Arcangeli (luca.arcangeli@gmail.com)
	 * @param token
	 *            User generated auth JWT
	 * @return JWT setted claims
	 */
	public static Claims parseToken(String token) throws Exception {
		return Jwts.parser().setSigningKey(JWT_AUTH_KEY).parseClaimsJws(token).getBody();
	}
}
