package com.yinzhiwu.yiwu.controller.api;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.Tweet;
import com.yinzhiwu.yiwu.model.TweetModel;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.view.TweetAbbrApiView;
import com.yinzhiwu.yiwu.model.view.TweetApiView;
import com.yinzhiwu.yiwu.service.FileService;
import com.yinzhiwu.yiwu.service.TweetService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api/tweet")
public class TweetApiController extends BaseController {

	@Autowired
	private TweetService tweetService;
	@Qualifier("fileServiceImpl")
	@Autowired private FileService fileService;

	@PostMapping("/save")
	public YiwuJson<TweetModel> save(@Valid TweetModel m, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			FieldError fieldError = bindingResult.getFieldError();
			if(logger.isDebugEnabled())
				logger.debug(super.getErrorsMessage(bindingResult));
			return new YiwuJson<>(fieldError.getField() + ": " + fieldError.getDefaultMessage());
		}
		
		if(m.getCoverIcon() != null && m.getCoverIcon().getSize()> 0){
			try {
				String fileName = fileService.upload(m.getCoverIcon());
				m.setCoverImageName(fileName);
				m.setCoverIconUrl(fileService.getFileUrl(fileName));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Tweet tweet = m.toTweet();
		tweetService.save(tweet);
		
		m.setContent(null);
		m.setCoverIcon(null);
		return new YiwuJson<>(m);
	}

	@GetMapping("/list")
	public YiwuJson<List<TweetAbbrApiView>> findList(int tweetTypeId, String title) {
		return tweetService.findByTypeByFuzzyTitle(tweetTypeId, title);
	}

	/**
	 * @deprecated {@link TweetApiController#doGet(int)}
	 * @param id
	 * @return
	 */
	@Deprecated
	@GetMapping("/id/{id}")
	@ApiOperation(value="使用 /api/tweet/{id} 接口")
	public YiwuJson<TweetApiView> findById(@PathVariable int id) {
		return tweetService.findById(id);
	}

	@GetMapping("/{id}")
	public YiwuJson<TweetApiView> doGet(@PathVariable int id) {
		return tweetService.findById(id);
	}
}
