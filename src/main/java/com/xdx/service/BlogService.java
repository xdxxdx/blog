package com.xdx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import com.xdx.constant.ECommonStatus;
import com.xdx.dao.BaseDao;
import com.xdx.dao.RedisGenericDao;
import com.xdx.entity.TBlog;
import com.xdx.entity.TCategory;
import com.xdx.util.ParamModel;

@Service
public class BlogService {
	@Resource(name="baseDao")
	private BaseDao<TBlog,Integer>baseDao;
	@Resource(name="blogTagService")
	private BlogTagService blogTagService;
	@Resource(name="categoryService")
	private CategoryService categoryService;
	@Resource(name="redisGenericDao")
	private RedisGenericDao<String, Map<String,Object>>redisGenericDao;
	public TBlog getBlogById(int blogId){
		TBlog blog=baseDao.getT("TBlogMapper.selectByPrimaryKey", blogId);
		blog.setBlogContent(HtmlUtils.htmlUnescape(blog.getBlogContent()));
		return blog;
	}
	public List<TBlog>getBlogListByPm(ParamModel pm){
		return baseDao.findTListByParam("TBlogMapper.selectListByPm", pm);
	}
	public List<Map<String,Object>>getBlogListMByPm(ParamModel pm){
		return baseDao.findMapListByPm("TBlogMapper.selectListMByPm", pm);
	}
	public List<TBlog>selectListWithContentByPm(ParamModel pm){
		return baseDao.findTListByParam("TBlogMapper.selectListWithContentByPm", pm);
	}
	public int getBlogSizeByPm(ParamModel pm){
		return (Integer) baseDao.getObject("TBlogMapper.selectSizeByPm", pm);
	}
	public List<TBlog>getPriBlogListByPm(ParamModel pm){
		return baseDao.findTListByParam("TBlogMapper.selectPriListByPm", pm);
	}
	public List<TBlog>selectPriListWithContentByPm(ParamModel pm){
		return baseDao.findTListByParam("TBlogMapper.selectPriListWithContentByPm", pm);
	}
	public int getPriBlogSizeByPm(ParamModel pm){
		return (Integer) baseDao.getObject("TBlogMapper.selectPriSizeByPm", pm);
	}
	
	public Map<String,Object> getBlogMapByPm(ParamModel pm){
		pm=ParamModel.getPm(pm);
		Map<String,Object>maps=new HashMap<String, Object>();
		maps.put("data", getBlogListByPm(pm));
		maps.put("count", getBlogSizeByPm(pm));
		maps.put("code", 0);
		maps.put("msg", "");
		return maps;
	}
	public Map<String,Object> getPriBlogMapByPm(ParamModel pm){
		pm=ParamModel.getPm(pm);
		Map<String,Object>maps=new HashMap<String, Object>();
		maps.put("data", getPriBlogListByPm(pm));
		maps.put("count", getPriBlogSizeByPm(pm));
		maps.put("code", 0);
		maps.put("msg", "");
		return maps;
	}
	public Integer saveBlog(int type,TBlog blog){
		Integer result=0;
		String tags=blog.getTags();
		String blogContent=HtmlUtils.htmlEscapeHex(blog.getBlogContent());
		blog.setBlogContent(blogContent);
		TCategory category=categoryService.getCategoryById(blog.getCategoryId());
		blog.setCategoryName(category.getCategoryName());
		if(type==1){
			result=baseDao.addT("TBlogMapper.insertSelectiveRePk", blog);//插入，并且返回主键
		}else{
			result=baseDao.updateT("TBlogMapper.updateByPrimaryKeySelective", blog);
		}
		if(result>0){
			blogTagService.saveBlogTagByBlogId(blog.getBlogId(), tags);
		}
		return result;
	}
	public Integer hiddenBlog(int blogId){
		return baseDao.updateBySql("TBlogMapper.hiddenBlogById", blogId);
	}
	public Integer updateStatus(TBlog blog){
		return baseDao.updateBySql("TBlogMapper.updateStatus", blog);
	}
	public List<Map<String,Object>>getBlogNumGroupByCategoryId(){
		return baseDao.findMapList("TBlogMapper.countCategoryBlogNum");
	}
	/**
	 * 修改blog的categoryName
	 * @param category
	 * @return
	 */
	public int updateCategoryNameByCategoryId(TCategory category){
		return baseDao.updateBySql("TBlogMapper.updateCategoryNameByCategoryId", category);
	}
	public List<Map<String,Object>>getBlogMListByTagId(Integer tagId,Integer page){
		ParamModel pm=ParamModel.getPm();
		pm.setPage(page);
		pm.setTagId(tagId);
		pm=ParamModel.getPm(pm);
		pm.setStatus(ECommonStatus.pass.getValue());
		List<Map<String,Object>>blogList=getBlogListMByPm(pm);
		return blogList;
	}
	/**
	 * 获取指定tag下的文章列表
	 * @param tagId
	 * @param page
	 * @return
	 */
	public Map<String,Object>getBlogMListMapByTagId(Integer tagId,Integer page){
		ParamModel pm=ParamModel.getPm();
		pm.setPage(page);
		pm.setTagId(tagId);
		pm=ParamModel.getPm(pm);
		pm.setStatus(ECommonStatus.pass.getValue());
		List<Map<String,Object>>blogList=getBlogListMByPm(pm);
		int size=getBlogSizeByPm(pm);
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("blogList", blogList);
		map.put("size", size);
		return map;
	}
	/**
	 * 获取指定categoryId下的文章列表
	 * @param categoryId
	 * @param page
	 * @return
	 */
	public Map<String,Object>getBlogMListMapByCategoryId(Integer categoryId,Integer page){
		ParamModel pm=ParamModel.getPm();
		pm.setPage(page);
		pm.setCategoryId(categoryId);
		pm=ParamModel.getPm(pm);
		pm.setStatus(ECommonStatus.pass.getValue());
		List<Map<String,Object>>blogList=getBlogListMByPm(pm);
		int size=getBlogSizeByPm(pm);
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("blogList", blogList);
		map.put("size", size);
		return map;
	}
	//*******************redis的操作************************************************//
	public Integer setBlogList2Redis(String key,List<Map<String,Object>>blogList){
		return redisGenericDao.lCleanAndSet(key, blogList)?1:0;
	}
	public List<Map<String,Object>>getListFromRedis(String key){
		List<Map<String,Object>>blogList=redisGenericDao.lGet(key, 0, -1);//若缓存中找不到，则直接到数据库中去寻找
		if(blogList==null||blogList.size()==0){
			ParamModel pm=new ParamModel();
			pm.setStatus(ECommonStatus.pass.getValue());
			blogList=getBlogListMByPm(pm);
			setBlogList2Redis("indexArticleList",blogList);
		}
		return blogList;
	}
}
