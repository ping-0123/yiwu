package com.yinzhiwu.yiwu.controller.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.enums.TweetType;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.TweetAbbrApiView;
import com.yinzhiwu.yiwu.model.view.TweetAbbrApiView.TweetAbbrApiViewConverter;
import com.yinzhiwu.yiwu.model.view.TweetApiView;
import com.yinzhiwu.yiwu.model.view.TweetApiView.TweetApiViewConverter;
import com.yinzhiwu.yiwu.service.TweetService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/api/tweets")
public class TweetApiController extends BaseController {

	@Autowired
	private TweetService tweetService;

	@PostMapping
	public YiwuJson<TweetApiView> save(@Valid TweetApiView tweetVO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			if(logger.isDebugEnabled())
				logger.debug(super.getErrorsMessage(bindingResult));
			return new YiwuJson<>(fieldError.getField() + ": " + fieldError.getDefaultMessage());
		}
		
		Tweet tweet = TweetApiViewConverter.INSTANCE.toPO(tweetVO);
		tweetService.save(tweet);
		
		return YiwuJson.createBySuccess(TweetApiViewConverter.INSTANCE.fromPO(tweet));
	}
	
	@GetMapping
	@ApiOperation(value="获取推文列表")	
	public YiwuJson<List<TweetAbbrApiView>> findAbbrlist(
			@ApiParam(name="type", value=
				"推文类型, 取值范围({PRODUCT,MARKET_ACTIVITY,PROMOTION,PERFORMACE,NEWS,CHILDREN,ADULT,OTHER})不传则搜索所有类型", 
			allowableValues="{PRODUCT,MARKET_ACTIVITY,PROMOTION,PERFORMACE,NEWS,CHILDREN,ADULT,OTHER}") 
			@RequestParam(name="type", required=false) TweetType type,
			@ApiParam(name="title", required=false) 
			@RequestParam(name="title", required=false) String title)
	{
		List<Tweet> tweets = tweetService.findByTypeAndFunzzyTitle(type, title);
		
		List<TweetAbbrApiView> vos = new ArrayList<>();
		for(Tweet tweet:tweets){
			vos.add(TweetAbbrApiViewConverter.INSTANCE.fromPO(tweet));
		}
		
		return YiwuJson.createBySuccess(vos);
	}


	@GetMapping("/{id}")
	@ApiOperation(value="获取指定推文内容")
	public YiwuJson<TweetApiView> doGet(@PathVariable(name="id") Integer id) throws DataNotFoundException {
		return YiwuJson.createBySuccess(TweetApiViewConverter.INSTANCE.fromPO(
				tweetService.get(id)));
	}
}
