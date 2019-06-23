package com.xdx.quartz;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xdx.entity.TCategory;
import com.xdx.entity.TTag;
import com.xdx.service.BlogService;
import com.xdx.service.BlogTagService;
import com.xdx.service.CategoryService;
import com.xdx.service.TagService;

@Component("quartz")
public class Quartz {
	@Resource(name="tagService")
	private TagService tagService;
	@Resource(name="blogTagService")
	private BlogTagService blogTagService;
	@Resource(name="blogService")
	private BlogService blogService;
	@Resource(name="categoryService")
	private CategoryService categoryService;
	public void quartz(){
		System.out.println("quartz is working");
		//改变标签对应的文章数目
		List<Map<String,Object>>tagList=blogTagService.getBlogNumGroupByTagId();
		if(tagList!=null&&tagList.size()>0){
			for(Map<String,Object>map:tagList){
				TTag tag=new TTag();
				tag.setTagId((Integer) map.get("tagId"));
				tag.setNum(Integer.parseInt(map.get("num").toString()));
				tagService.updateTagBlogNum(tag);
			}
		}
		//改变类别对应的文章数目
		List<Map<String,Object>>categoryList=blogService.getBlogNumGroupByCategoryId();
		if(categoryList!=null&&categoryList.size()>0){
			for(Map<String,Object>map:categoryList){
				TCategory category=new TCategory();
				category.setCategoryId((Integer) map.get("categoryId"));
				category.setNum(Integer.parseInt(map.get("num").toString()));
				categoryService.updateCategoryBlogNum(category);
			}
		}
	}

}
