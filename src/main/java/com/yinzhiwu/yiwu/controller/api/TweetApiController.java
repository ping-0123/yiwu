package com.yinzhiwu.yiwu.controller.api;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.model.TweetModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.TweetAbbrApiView;
import com.yinzhiwu.yiwu.model.view.TweetApiView;
import com.yinzhiwu.yiwu.service.TweetService;
import com.yinzhiwu.yiwu.util.UrlUtil;

@RestController
@RequestMapping(value="/api/tweet")
public class TweetApiController extends BaseController {
	

	@Autowired
	private TweetService tweetService;	
	
	@PostMapping("/save")
	public YiwuJson<Boolean> save(HttpServletRequest request, @Valid TweetModel m, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			FieldError fieldError = bindingResult.getFieldError();
			logger.info(super.getErrorsMessage(bindingResult));
			return new YiwuJson<>(fieldError.getField() + ": " + fieldError.getDefaultMessage());
		}
		File file = new File(request.getServletContext().getRealPath(UrlUtil.TWEET_COVER_ICON_PATH), 
					System.currentTimeMillis() + ".jpg");
		try {
			m.getCoverIcon().transferTo(file);
		} catch (IllegalStateException | IOException e) {
			logger.info("connot save file.....");
			return new YiwuJson<>(e.getMessage());
		}
		m.setCoverIconUrl(UrlUtil.toTweetCoverIconUrl(file.getName()));
		try{
			tweetService.save(m);
		}catch (Exception e) {
			return new YiwuJson<>("富文本内容请控制在16M内");
		}
		
		return new YiwuJson<>(new Boolean(true));
	}
	
	@GetMapping("/list")
	public YiwuJson<List<TweetAbbrApiView>> findList(int tweetTypeId, String title){
		return tweetService.findByTypeByFuzzyTitle(tweetTypeId, title);
	}
	
	@Deprecated
	@GetMapping("/id/{id}")
	public YiwuJson<TweetApiView> findById(@PathVariable int id){
		return tweetService.findById(id);
	}
	
	@GetMapping("/{id}")
	public YiwuJson<TweetApiView> doGet(@PathVariable int id){
		return tweetService.findById(id);
	}
}
