package com.xdx.controller.back;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdx.entity.TCategory;
import com.xdx.service.BlogService;
import com.xdx.service.CategoryService;
import com.xdx.util.JsonUtil;
import com.xdx.util.ParamModel;

@Controller
public class CategoryController {
	@Resource(name="categoryService")
	private CategoryService categoryService;
	@Resource(name="blogService")
	private BlogService blogService;
	@RequestMapping("category/home")
	public ModelAndView categoryHome(){
		ModelAndView mv=new ModelAndView("back/category/CategoryList");
		return mv;
	}
	@RequestMapping("category/add")
	public ModelAndView categoryAdd(){
		ModelAndView mv=new ModelAndView("back/category/CategoryAdd");
		return mv;
	}
	@RequestMapping("category/edit")
	public ModelAndView categoryEdit(int categoryId){
		ModelAndView mv=new ModelAndView("back/category/CategoryEdit");
		TCategory category=categoryService.getCategoryById(categoryId);
		mv.addObject("category", category);
		return mv;
	}
	@ResponseBody
	@RequestMapping("category/list")
	public JSONObject categoryList(ParamModel pm){
		Map<String,Object>maps=categoryService.getCategoryMapByPm(pm);
		JSONObject jsonObject=JsonUtil.mapToJsonObject(maps);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("category/save")
	public String categorySave(int type,TCategory category){
		Integer result=categoryService.saveCategory(type, category);
		if(type==2&&result>0){
			blogService.updateCategoryNameByCategoryId(category);
		}
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("category/hidden")
	public String categoryHidden(int categoryId){
		Integer result=categoryService.hiddenCategory(categoryId);
		return result.toString();
	}
	//判定用户名是否已使用
	@ResponseBody
	@RequestMapping("category/categoryByCategoryName")
	public Map<String, Object> categoryByCategoryName(String categoryName) {
		TCategory category=categoryService.getCategoryByCategoryName(categoryName);
		Map<String, Object> maps = new HashMap<String, Object>();
		if (category == null) {
			maps.put("valid", true);
		} else {
			maps.put("valid", false);
		}
		return maps;
	}
}
