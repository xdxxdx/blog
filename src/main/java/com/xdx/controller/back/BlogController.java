package com.xdx.controller.back;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdx.constant.ECommonStatus;
import com.xdx.entity.TBlog;
import com.xdx.entity.TCategory;
import com.xdx.entity.TTag;
import com.xdx.service.BlogService;
import com.xdx.service.BlogTagService;
import com.xdx.service.CategoryService;
import com.xdx.service.TagService;
import com.xdx.util.ParamModel;

@Controller
public class BlogController {
	@Resource(name="blogService")
	private BlogService blogService;
	@Resource(name="tagService")
	private TagService tagService;
	@Resource(name="categoryService")
	private CategoryService categoryService;
	@Resource(name="blogTagService")
	private BlogTagService blogTagService;
	
	@RequestMapping("blog/home")
	public ModelAndView blogHome(ParamModel pm){
		ModelAndView mv=new ModelAndView("back/blog/BlogList");
		List<TCategory>categoryList=categoryService.getCategoryList();
		mv.addObject("categoryList", categoryList);
		List<TTag>tagList=tagService.getTagList();
		mv.addObject("tagList", tagList);
		mv.addObject("pm", pm);
		return mv;
	}
	@RequestMapping("blog/edit")
	public ModelAndView blogEdit(int blogId){
		ModelAndView mv=new ModelAndView("back/blog/BlogEdit");
		TBlog blog=blogService.getBlogById(blogId);
		blog.setBlogContent(blog.getBlogContent());
		mv.addObject("blog", blog);
		List<Map<String,Object>>tagList=blogTagService.getTagListByBlogId(blogId);
		mv.addObject("tagList", tagList);
		List<TCategory>categoryList=categoryService.getCategoryList();
		mv.addObject("categoryList", categoryList);
		return mv;
	}
	@ResponseBody
	@RequestMapping("blog/list")
	public Map<String,Object>blogList(ParamModel pm){
		Map<String,Object>maps=blogService.getBlogMapByPm(pm);
		return maps;
	}
	@RequestMapping("blog/add")
	public ModelAndView blogAdd(){
		List<TCategory>categoryList=categoryService.getCategoryList();
		List<TTag>tagList=tagService.getTagList();
		ModelAndView mv=new ModelAndView("back/blog/BlogAdd");
		mv.addObject("categoryList", categoryList);
		mv.addObject("tagList", tagList);
		return mv;
	}
	@ResponseBody
	@RequestMapping("blog/save")
	public String blogSave(int type,TBlog blog,HttpSession session){
		Integer adminId=(Integer) session.getAttribute("adminId");
		blog.setAuthorId(adminId);
		Integer result=blogService.saveBlog(type, blog);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("blog/publish")
	public String blogPublish(int type,TBlog blog){
		blog.setStatus(1);
		Integer result=blogService.saveBlog(type, blog);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("blog/status")
	public String blogStatus(TBlog blog){
		Integer result=blogService.updateStatus(blog);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("blog/hidden")
	public String blogHidden(int blogId){
		Integer result=blogService.hiddenBlog(blogId);
		return result.toString();
	}
	@RequestMapping("blog/pri")
	public ModelAndView blogPri(ParamModel pm){
		ModelAndView mv=new ModelAndView("back/blog/PriList");
		List<TCategory>categoryList=categoryService.getCategoryList();
		mv.addObject("categoryList", categoryList);
		List<TTag>tagList=tagService.getTagList();
		mv.addObject("tagList", tagList);
		mv.addObject("pm", pm);
		return mv;
	}
	@ResponseBody
	@RequestMapping("blog/priList")
	public Map<String,Object>blogPriList(ParamModel pm){
		Map<String,Object>maps=blogService.getPriBlogMapByPm(pm);
		return maps;
	}
	@ResponseBody
	@RequestMapping("blog/indexList2Redis")
	public String indexBlogToRedis(){
		ParamModel pm=new ParamModel();
		pm.setStatus(ECommonStatus.pass.getValue());
		List<Map<String,Object>>blogList=blogService.getBlogListMByPm(pm);
		return blogService.setBlogList2Redis("indexArticleList", blogList).toString();
	}

	
}
