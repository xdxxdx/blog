package com.xdx.controller.front;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xdx.entity.TAdmin;
import com.xdx.entity.TBlog;
import com.xdx.entity.TCategory;
import com.xdx.entity.TTag;
import com.xdx.service.AdminService;
import com.xdx.service.BlogService;
import com.xdx.service.CategoryService;
import com.xdx.service.TagService;

@Controller
public class ArticleController {
	@Resource(name="blogService")
	private BlogService blogService;
	@Resource(name="tagService")
	private TagService tagService;
	@Resource(name="adminService")
	private AdminService adminService;
	@Resource(name="categoryService")
	private CategoryService categoryService;
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView mv=new ModelAndView("front/main/Index");
		List<TTag>tagList=tagService.getTagListFromRedis("tagList");
		mv.addObject("tagList", tagList);
//		ParamModel pm=new ParamModel();
//		pm.setStatus(ECommonStatus.pass.getValue());
//		List<Map<String,Object>>blogList=blogService.getBlogListMByPm(pm);
		List<Map<String,Object>>blogList=blogService.getListFromRedis("indexArticleList");
		mv.addObject("blogList", blogList);
		return mv;
	}
	//restful风格的url
	@RequestMapping(value="/article/{blogId}",method=RequestMethod.GET)
	public ModelAndView article(@PathVariable("blogId") Integer blogId){
		ModelAndView mv=new ModelAndView("front/main/Article");
		TBlog blog=blogService.getBlogById(blogId);
		mv.addObject("blog", blog);
		TAdmin admin=adminService.getAdminById(blog.getAuthorId());
		mv.addObject("author", admin);
		List<TTag>tagList=tagService.getTagListFromRedis("tagList");
		mv.addObject("tagList", tagList);
		return mv;
	}
	@RequestMapping(value="/pri/{blogId}",method=RequestMethod.GET)
	public ModelAndView pri(@PathVariable("blogId") Integer blogId){
		ModelAndView mv=new ModelAndView("front/main/Pri");
		TBlog blog=blogService.getBlogById(blogId);
		mv.addObject("blog", blog);
		return mv;
	}
	@RequestMapping("/tagArticle/{tagId}/{page}")
	public ModelAndView tagArticle(@PathVariable("tagId")Integer tagId,@PathVariable("page")String page){
		ModelAndView mv=new ModelAndView("front/main/TagArticle");
		List<TTag>tagList=tagService.getTagListFromRedis("tagList");
		mv.addObject("tagList", tagList);
		TTag tag=tagService.getTagById(tagId);
		mv.addObject("tag", tag);
		mv.addObject("page", Integer.parseInt(page.substring(1)));
		Map<String,Object>blogMap=blogService.getBlogMListMapByTagId(tagId, Integer.parseInt(page.substring(1)));
		mv.addObject("blogMap", blogMap);
//		System.out.println(tagId+","+page);
		return mv;
	}
	@RequestMapping("/tagArticle/{page}")
	public ModelAndView tagArticleNoPage(@PathVariable("page")String page){
		ModelAndView mv=new ModelAndView("front/main/TagArticle");
		List<TTag>tagList=tagService.getTagListFromRedis("tagList");
		mv.addObject("tagList", tagList);
		mv.addObject("page", Integer.parseInt(page.substring(1)));
		Map<String,Object>blogMap=blogService.getBlogMListMapByTagId(null, Integer.parseInt(page.substring(1)));
		mv.addObject("blogMap", blogMap);
//		System.out.println(tagId+","+page);
		return mv;
	}
	@RequestMapping("/categoryArticle/{categoryId}/{page}")
	public ModelAndView categoryArticle(@PathVariable("categoryId")Integer categoryId,@PathVariable("page")String page){
		ModelAndView mv=new ModelAndView("front/main/CategoryArticle");
		List<TCategory>categoryList=categoryService.getCategoryList();
		mv.addObject("categoryList", categoryList);
		TCategory category=categoryService.getCategoryById(categoryId);
		mv.addObject("category", category);
		mv.addObject("page", Integer.parseInt(page.substring(1)));
		Map<String,Object>blogMap=blogService.getBlogMListMapByCategoryId(categoryId, Integer.parseInt(page.substring(1)));
		mv.addObject("blogMap", blogMap);
//		System.out.println(tagId+","+page);
		return mv;
	}
	@RequestMapping("/categoryArticle/{page}")
	public ModelAndView categoryArticle(@PathVariable("page")String page){
		ModelAndView mv=new ModelAndView("front/main/CategoryArticle");
		List<TCategory>categoryList=categoryService.getCategoryList();
		mv.addObject("categoryList", categoryList);
		mv.addObject("page", Integer.parseInt(page.substring(1)));
		Map<String,Object>blogMap=blogService.getBlogMListMapByCategoryId(null, Integer.parseInt(page.substring(1)));
		mv.addObject("blogMap", blogMap);
		return mv;
	}
}
