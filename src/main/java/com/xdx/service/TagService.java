package com.xdx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xdx.dao.BaseDao;
import com.xdx.dao.RedisGenericDao;
import com.xdx.entity.TTag;
import com.xdx.util.ParamModel;

@Service
public class TagService {
	@Resource(name="baseDao")
	private BaseDao<TTag,Integer>baseDao;
	@Resource(name="redisGenericDao")
	private RedisGenericDao<String,TTag> redisGenericDao;
	public TTag getTagById(int tagId){
		return baseDao.getT("TTagMapper.selectByPrimaryKey", tagId);
	}
	public List<TTag>getTagList(){
		return baseDao.findTList("TTagMapper.selectList");
	}
	public List<TTag>getTagListByPm(ParamModel pm){
		return baseDao.findTListByParam("TTagMapper.selectListByPm", pm);
	}
	public int getTagSizeByPm(ParamModel pm){
		return (Integer) baseDao.getObject("TTagMapper.selectSizeByPm", pm);
	}
	public Map<String,Object>getTagMapByPm(ParamModel pm){
		pm=ParamModel.getPm(pm);
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("data", getTagListByPm(pm));
		map.put("count", getTagSizeByPm(pm));
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
	public Integer saveTag(int type,TTag tag){
		Integer result=0;
		if(type==1){
			result=baseDao.addT("TTagMapper.insertSelective", tag);
		}else{
			result=baseDao.updateT("TTagMapper.updateByPrimaryKeySelective", tag);
		}
		return result;
	}
	/**
	 * 隐式删除
	 * @param tagId
	 * @return
	 */
	public Integer hiddenTag(int tagId){
		return baseDao.updateBySql("TTagMapper.hiddenTagById", tagId);
	}
	public TTag getTagByTagName(String tagName){
		return baseDao.getT("TTagMapper.selectByTagName", tagName);
	}
	public Integer updateTagBlogNum(TTag tag){
		return baseDao.updateBySql("TTagMapper.updateBlogNum", tag);
	}
	//*************************redis操作*****************************************//
	public Integer setTagList2Redis(String key,List<TTag>tagList){
		return redisGenericDao.lCleanAndSet(key, tagList)?1:0;
	}
	public List<TTag>getTagListFromRedis(String key){
		List<TTag>tagList=redisGenericDao.lGet("tagList", 0, -1);
		if(tagList==null||tagList.size()==0){
			tagList=getTagList();
			setTagList2Redis("tagList",tagList);
		}
		return tagList;
	}
}
