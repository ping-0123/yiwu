package com.yinzhiwu.yiwu.web.controller;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhiwu.yiwu.controller.BaseController;
import com.yinzhiwu.yiwu.entity.yzw.PostYzw;
import com.yinzhiwu.yiwu.exception.DataNotFoundException;
import com.yinzhiwu.yiwu.model.YiwuJson;
import com.yinzhiwu.yiwu.model.datatable.DataTableBean;
import com.yinzhiwu.yiwu.model.datatable.Order.Direction;
import com.yinzhiwu.yiwu.model.datatable.QueryParameter;
import com.yinzhiwu.yiwu.model.page.PageBean;
import com.yinzhiwu.yiwu.service.PostYzwService;
import com.yinzhiwu.yiwu.util.ReflectUtils;

/**
 * 
 * @author ping
 * @date 2017年9月19日下午8:24:05
 *
 */
@Controller
@RequestMapping(value="/system/posts")
public class PostController extends BaseController {

	@Autowired private PostYzwService postService;
	List<String> list;
	
	
	private QueryParameter parseParameter(HttpServletRequest request) 
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		
		
//		 String regPrimary = "[_a-zA-Z]+[_\\w]*";
//		 String regObject = "[_a-zA-Z]+[_\\w]*\\[[_a-zA-Z]+[_\\w]*\\].*";
//		 String regArray = "[_a-zA-Z]+[_\\w]*\\[[0-9]+\\].*";
		 
		Map<String, String[]> map = request.getParameterMap();
		QueryParameter para = new QueryParameter();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			
			/**
			 * 1. match primary or enum type  eg: start "[_a-zA-Z]+[_\\w]*"
			 * 2. match non primary type eg: search[value] "[_a-zA-Z]+[_\\w]*\\[[_a-zA-Z]+[_\\w]*\\]$"
			 * 3. match array			eg: columns[1]  "[_a-zA-Z]+[_\\w]*\\[[0-9]+\\].*"
			 */
			if(entry.getValue() !=null 
					&& entry.getValue().length>0 
					&& !"".equals(entry.getValue()[0].trim()))
				setComplexFieldValue(entry.getKey(),entry.getValue()[0],para);
			
		}
		return para;
	}
	
	private void setComplexFieldValue(String paraName, String value, Object entity) 
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		final String regPrimary = "[_a-zA-Z]+[_\\w]*";
		final String regObject = "[_a-zA-Z]+[_\\w]*\\[[_a-zA-Z]+[_\\w]*\\].*";
		final String regArray = "[_a-zA-Z]+[_\\w]*\\[[0-9]+\\].*";
		 
		 String fieldname = null;
		 if(paraName.matches(regPrimary)){
			 fieldname = paraName;
			 Field field = entity.getClass().getDeclaredField(fieldname);
			 field.setAccessible(true);
			 Class<?> fieldClass = (Class<?>) field.getGenericType();
			if ("boolean".equals(fieldClass.getSimpleName()) || fieldClass.equals(Boolean.class))
				field.set(entity, Boolean.valueOf(value));
			else if ("int".equals(fieldClass.getName()) || fieldClass.equals(Integer.class))
				field.set(entity, Integer.valueOf(value));
			else if (fieldClass.equals(Long.TYPE.getClass()) || fieldClass.equals(Long.class))
				field.set(entity, Long.valueOf(value));
			else if (fieldClass.equals(Short.TYPE.getClass()) || fieldClass.equals(Short.class))
				field.set(entity, Short.valueOf(value));
			else if (fieldClass.equals(Float.TYPE.getClass()) || fieldClass.equals(Float.class))
				field.set(entity, Float.valueOf(value));
			else if (fieldClass.equals(Double.TYPE.getClass()) || fieldClass.equals(Double.class))
				field.set(entity, Double.valueOf(value));
			else if (fieldClass.equals(Character.TYPE.getClass()) || fieldClass.equals(Character.class))
				field.set(entity, value.getBytes());
			else if (fieldClass.equals(Byte.TYPE.getClass()) || fieldClass.equals(Byte.class))
				field.set(entity, Byte.valueOf(value));
			else if (fieldClass.isEnum()) {
				if (fieldClass.equals(Direction.class))
					field.set(entity, Direction.valueOf(value));
			 }else if(fieldClass.equals(String.class)){
				field.set(entity, value);
			}else {
				throw new UnsupportedOperationException("can not convert string \"" + value + "\" to target type " + fieldClass.getName());
			}
			 
		 }else if(paraName.matches(regObject)){
			 fieldname = paraName.substring(0, paraName.indexOf("["));
			 Field field = entity.getClass().getDeclaredField(fieldname);
			 field.setAccessible(true);
			//迭代
			 Object subEntity = field.get(entity);
			 if(subEntity == null){
				 Class<?> clazz= (Class<?>) field.getGenericType();
				 subEntity = clazz.newInstance();
				 field.set(entity, subEntity);
			 }
			 String subFieldname = paraName.substring( paraName.indexOf("[") + 1);
			 subFieldname  = subFieldname.replaceFirst("]", "");
			 
			 setComplexFieldValue(subFieldname, value, subEntity);
		 }else if(paraName.matches(regArray)){
			 fieldname = paraName.substring(0, paraName.indexOf("["));
			 Field field = entity.getClass().getDeclaredField(fieldname);
			 field.setAccessible(true);
			 
			 int index = Integer.valueOf(paraName.substring(paraName.indexOf("[") + 1, paraName.indexOf("]")));
			 Object[] subs= (Object[]) field.get(entity);
			 if(subs[index] == null)
				 subs[index] = subs.getClass().getComponentType().newInstance();
			 Object sub = subs[index];
			 String subFieldname = paraName.substring( paraName.indexOf("]") +2);
			 subFieldname = subFieldname.replaceFirst("]", "");
			 
			 setComplexFieldValue(subFieldname, value, sub);
		 }
	}
	
	
	@PostMapping(value="/table")
	@ResponseBody
	public DataTableBean index(Integer draw,Integer start,Integer length, HttpServletRequest request){
		try {
			QueryParameter para = parseParameter(request);
			ReflectUtils.showObject(para);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> entry :map.entrySet()) {
			if(entry.getValue().length>1)
				System.out.println("multi value key is " + entry.getKey());
		}
		
		
		Integer pageNo =(int) Math.ceil( ((float)(start+1)/length));
		Integer pageSize = length;
		PageBean<?> page = postService.findPageOfAll(pageNo, pageSize);
		return  new DataTableBean(draw,page.getTotalRecord(),page.getTotalRecord(),page.getData(),null);
	}
	
	@GetMapping(value="/list")
	public String list(Model model){
		List<PostYzw> posts = postService.findAll();
		model.addAttribute("posts", posts);
		return "posts/list";
	}
	
	@PostMapping
	public String createNewPost(@Valid PostYzw post, BindingResult bindingResult){
		if(bindingResult.hasErrors() ){	
			return "/success.jsp";
		}
		postService.save(post);
		return "redirect:posts/list";
	}
	
	@GetMapping("/form")
	public String showEditionForm(Model model){
		PostYzw post = new PostYzw();
		model.addAttribute("post", post);
		return "posts/createForm";
	}
	
	@GetMapping("/{id}/form")
	public String showModifyForm(@PathVariable(name="id") Integer id, Model model){
		PostYzw post = postService.get(id);
		model.addAttribute("post", post);
		return "posts/editForm";
	}
	
	@PutMapping(value ="/{id}")
	public String modify(@PathVariable(name="id") Integer id, PostYzw post){
		try {
			postService.modify(id, post);
		} catch (IllegalArgumentException | IllegalAccessException | DataNotFoundException e) {
			logger.error(e.getMessage(), e);
		}
		
		return "redirect:list";
	}
	
	@ResponseBody
	@DeleteMapping(value="/{id}")
	public YiwuJson<?> modify(@PathVariable(name="id") Integer id){
		postService.delete(id);
		return YiwuJson.createBySuccess();
	}
}
