package com.yinzhiwu.yiwu.qiniu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.yinzhiwu.yiwu.qiniu.Qiniu;
import com.yinzhiwu.yiwu.qiniu.Token;


@RestController
@RequestMapping("/qiniu/token")
public class QiniuTokenController {

	@GetMapping("")
	public String  getToken(Token token, int distributerId){
		Auth auth = Auth.create(Qiniu.ACCESS_KEY,Qiniu.SECRET_KEY);
		String t;
		switch (token) {
		case DEFAULT_UPLOAD_TOKEN:
			t = auth.uploadToken(Qiniu.BUCKET);
		case UPLOAD_USER_HEAD_ICON_TOKEN:
			t = auth.uploadToken(Qiniu.BUCKET, null, Qiniu.EXPIRED_TIME, new StringMap()
					.put("callbackUrl", "")
					.put("callbackBody",""));
			break;
		case UPLOAD_TWEET_COVER_ICON_TOKEN:
			t = auth.uploadToken(Qiniu.BUCKET);
			break;
		default:
			t = auth.uploadToken(Qiniu.BUCKET);
			break;
		}
		return t;
	}
}
