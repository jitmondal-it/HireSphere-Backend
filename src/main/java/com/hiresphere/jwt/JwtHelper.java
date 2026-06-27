package com.hiresphere.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtHelper {
	 private static final Key SECRET_KEY =
	            Keys.secretKeyFor(SignatureAlgorithm.HS256);

	    // Token expiration time (1 hour)
	    public static final long JWT_TOKEN_VALIDITY = 3600000;

	    // Retrieve username from JWT token
	    public String getUsernameFromToken(String token) {
	        return getClaimFromToken(token, Claims::getSubject);
	    }

	    // Retrieve expiration date from JWT token
	    public Date getExpirationDateFromToken(String token) {
	        return getClaimFromToken(token, Claims::getExpiration);
	    }

	    // Retrieve any claim from token
	    public <T> T getClaimFromToken(
	            String token,
	            Function<Claims, T> claimsResolver) {

	        final Claims claims = getAllClaimsFromToken(token);
	        return claimsResolver.apply(claims);
	    }

	    // Get all claims from token
	    private Claims getAllClaimsFromToken(String token) {

	        return Jwts.parserBuilder()
	                .setSigningKey(SECRET_KEY)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
	    }

	    // Check if token expired
	    private Boolean isTokenExpired(String token) {

	        final Date expiration =
	                getExpirationDateFromToken(token);

	        return expiration.before(new Date());
	    }

	    // Generate token for user
	    public String generateToken(UserDetails userDetails) {

	        Map<String, Object> claims = new HashMap<>();
	        CustomUserDetails customUser = (CustomUserDetails)userDetails;
	        claims.put("id", customUser.getId());
	        claims.put("name", customUser.getName());
	        claims.put("accountType", customUser.getAccountType());
	        claims.put("profileId", customUser.getProfileId());
	        
	        return doGenerateToken(claims, userDetails.getUsername());
	    }

	    // Create token
	    private String doGenerateToken(
	            Map<String, Object> claims,
	            String subject) {

	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(subject)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(
	                        new Date(System.currentTimeMillis()
	                                + JWT_TOKEN_VALIDITY))
	                .signWith(SECRET_KEY)
	                .compact();
	    }

	    // Validate token
	    public Boolean validateToken(
	            String token,
	            String username) {

	        final String tokenUsername =
	                getUsernameFromToken(token);

	        return (tokenUsername.equals(username)
	                && !isTokenExpired(token));
	    }
}
