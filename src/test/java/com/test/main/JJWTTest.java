package com.test.main;

import java.security.Key;
import java.util.Date;

import org.junit.Test;

import com.test.BaseBlockJUnitTest;
import com.yinzhiwu.yiwu.entity.Distributer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.crypto.MacProvider;

/**
*@Author ping
*@Time  创建时间:2017年10月19日上午11:50:13
*
*/

public class JJWTTest extends BaseBlockJUnitTest{
	
//	@Autowired private DistributerService distributerService;
	
	@Test
	public void testJJWT(){
		Key key = MacProvider.generateKey();
		System.err.println("key is "  + key);
		System.out.println("key format is " + key.getFormat());
		System.out.println("key algorithm is "  + key.getAlgorithm());
		
		 String jwtString = Jwts.builder()
				 .setSubject("ping")
				 .signWith(SignatureAlgorithm.HS384, key)
				 .compact();
		 System.out.println("jwt is " + jwtString);
		 
		String subject =  Jwts.parser()
		 	.setSigningKey(key)
		 	.parseClaimsJws(jwtString)
		 	.getBody()
		 	.getSubject();
		 
		System.out.println("subject is " + subject);
	}
	
	@Test
	public void testAddDistributer(){
		int distributerId = 3001298;
		Distributer distributer = new Distributer();
		distributer.setId(distributerId);
		distributer.init();
		distributer.setBirthday(new Date());
		Claims claims = new DefaultClaims();
		claims.put("distributer", new Distributer());
		
//		System.out.println("distributer id is "  + claims.get("distributer", Distributer.class).getId());
		
		Key key = MacProvider.generateKey();
		String jwtString = null;
		try{
			jwtString = Jwts.builder()
					.setSubject("ping")
					.claim("distributer", distributer)
					.signWith(SignatureAlgorithm.HS512, key)
					.compact();
		}catch (Exception e) {
			logger.error(e.getMessage(),e);
			throw e;
		}
		System.out.println("jwt string is " + jwtString);
		
		Claims parsedClaims = Jwts.parser()
		.setSigningKey(key)
		.parseClaimsJws(jwtString)
		.getBody();
		
//		Distributer parsedDistributer =  parsedClaims.get("distributer", Distributer.class);
		
//		System.out.println("parsed distributer is "  +parsedDistributer.getId());
		
		Object object = parsedClaims.get("distributer");
		System.out.println("distributer in claims is " + object);
		Object object2 = parsedClaims.get("customer");
		System.out.println("customer is token is " +object2);
	}

}
