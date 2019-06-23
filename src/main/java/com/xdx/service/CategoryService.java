package com.xdx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xdx.dao.BaseDao;
import com.xdx.entity.TCategory;
import com.xdx.entity.TTag;
import com.xdx.util.ParamModel;

@Service
public class CategoryService {
	@Resource(name="baseDao")
	private BaseDao<TCategory,Integer>baseDao;
	public TCategory getCategoryById(int categoryId){
		return baseDao.getT("TCategoryMapper.selectByPrimaryKey", categoryId);
	}
	public List<TCategory>getCategoryList(){
		return baseDao.findTList("TCategoryMapper.selectList");
	}
	public List<TCategory>getCategoryListByPm(ParamModel pm){
		return baseDao.findTListByParam("TCategoryMapper.selectListByPm", pm);
	}
	public int getCategorySizeByPm(ParamModel pm){
		return (Integer) baseDao.getObject("TCategoryMapper.selectSizeByPm", pm);
	}
	public Map<String,Object>getCategoryMapByPm(ParamModel pm){
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("data", getCategoryListByPm(pm));
		map.put("count", getCategorySizeByPm(pm));
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	/**
	 * 保存
	 * @param type
	 * @param tag
	 * @return
	 */
	public Integer saveCategory(int type,TCategory category){
		Integer result=0;
		if(type==1){
			result=baseDao.addT("TCategoryMapper.insertSelective", category);
		}else{
			result=baseDao.updateT("TCategoryMapper.updateByPrimaryKeySelective", category);
		}
		return result;
	}
	/**
	 * 隐式删除
	 * @param tagId
	 * @return
	 */
	public Integer hiddenCategory(int categoryId){
		return baseDao.updateBySql("TCategoryMapper.hiddenCategoryById", categoryId);
	}
	public TCategory getCategoryByCategoryName(String tagName){
		return baseDao.getT("TCategoryMapper.selectByCategoryName", tagName);
	}
	public Integer updateCategoryBlogNum(TCategory category){
		return baseDao.updateBySql("TCategoryMapper.updateBlogNum", category);
	}
}
