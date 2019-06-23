package com.xdx.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xdx.dao.BaseDao;
import com.xdx.entity.TBlogTag;
import com.xdx.entity.TTag;

@Service
public class BlogTagService {
	@Resource(name="baseDao")
	private BaseDao<TBlogTag,Integer>baseDao;
	public List<Map<String,Object>>getTagListByBlogId(int blogId){
		return baseDao.findMapListByPm("TBlogTagMapper.selectTagByblogId", blogId);
	}
	public int deleteTagsByBlogId(int blogId){
		return baseDao.deleteTBySql("TBlogTagMapper.deleteByBlogId", blogId);
	}
	public void saveBlogTagByBlogId(int blogId,String tags){
		deleteTagsByBlogId(blogId);
		if(!tags.equals("")){
			String tagArr[]=tags.split(",");
			for(int i=0;i<tagArr.length;i++){
				int tagId=Integer.parseInt(tagArr[i]);
				TBlogTag blogTag=new TBlogTag();
				blogTag.setBlogId(blogId);
				blogTag.setTagId(tagId);
				baseDao.addT("TBlogTagMapper.insertSelective", blogTag);
			}
		}
	}
	public List<Map<String,Object>>getBlogNumGroupByTagId(){
		List<Map<String,Object>>list=baseDao.findMapList("TBlogTagMapper.countBlogNum");
		return list;
	}
}
