package com.weat.weat.util;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.weat.weat.common.exception.CoreException;
import com.weat.weat.common.utils.Constants;
import com.weat.weat.data.model.SystemParameter;
import com.weat.weat.repository.SystemParameterRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	@Autowired
	private SystemParameterRepository sysParamRepo;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		SystemParameter param = sysParamRepo.findByParamName(Constants.JWT_SECRET)
				.orElseThrow(() -> new CoreException("Secret key is not present"));
		return Jwts.parser().setSigningKey(param.getParamValue()).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		Set<String> userRoles = new HashSet<>();
		userDetails.getAuthorities().forEach(action -> {
			userRoles.add(action.getAuthority());
		});
		claims.put("Roles", userRoles.toArray());
		return doGenerateToken(claims, userDetails.getUsername());
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		SystemParameter param = sysParamRepo.findByParamName(Constants.JWT_SECRET)
				.orElseThrow(() -> new CoreException("Secret key is not present"));

		SystemParameter paramForValidity = sysParamRepo.findByParamName(Constants.JWT_TOKEN_VALIDITY)
				.orElseThrow(() -> new CoreException("Token validity key is not present"));

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(
						System.currentTimeMillis() + Integer.parseInt(paramForValidity.getParamValue()) * 1000))
				.signWith(SignatureAlgorithm.HS512, param.getParamValue()).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
