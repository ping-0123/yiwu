package com.yinzhiwu.yiwu.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.yinzhiwu.yiwu.context.Constants;
import com.yinzhiwu.yiwu.context.JJWTConfig;
import com.yinzhiwu.yiwu.entity.Distributer;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

/**
* @author 作者 ping
* @Date 创建时间：2017年11月5日 下午11:30:58
*
*/

@Service
public class JJWTService {
	
	/**
	 * 
	 * @param distributer
	 * @return
	 */
	public String createDistributerIdToken(Distributer distributer){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.SECOND, (int) JJWTConfig.LIFE_CYCLE_IN_SECONDS);
		return Jwts.builder().setExpiration(calendar.getTime())
			.setSubject(distributer.getName())
			.setId(distributer.getId().toString())
			.claim(Constants.CURRENT_DISTRIBUTER_ID, distributer.getId())
			.signWith(JJWTConfig.SIGNATURE_ALGORITHM,JJWTConfig.SECRET_KEY)
			.compact();
	}
	
	/**
	 * 
	 * @param token
	 * @return distributerId
	 */
	public Integer parseDistributerIdToken(String token){
		Claims claims =  Jwts.parser().setSigningKey(JJWTConfig.SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		
		if(claims.getExpiration().before(new Date()))
			throw new ExpiredJwtException(null, claims, "token 已过期");
		
		return claims.get(Constants.CURRENT_DISTRIBUTER_ID,Integer.class);
	}
}
