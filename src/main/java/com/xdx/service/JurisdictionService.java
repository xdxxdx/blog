package com.xdx.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.xdx.dao.BaseDao;
import com.xdx.entity.TJurisdiction;
import com.xdx.entity.TMenu;
@Service
public class JurisdictionService {
	@Resource(name="baseDao")
	private BaseDao<TJurisdiction,Integer>baseDao;
	public List<TJurisdiction>getJurisdictionTListByRoleId(int roleId){
		return baseDao.findTListByParam("TJurisdictionMapper.selectTJurisdictionByRoleId", roleId);
	}
	public List<Map<String,Object>>getJurisdictionMListByRoleId(int roleId){
		return baseDao.findMapListByPm("TJurisdictionMapper.selectJurisdictionByRoleId", roleId);
	}
	public JSONArray getJurisdictionJsonByRoleId(int roleId){
		List<Map<String,Object>>jurisdictionList=getJurisdictionMListByRoleId(roleId);
		for(Map<String,Object>map:jurisdictionList){
			map.put("checked", !map.get("jurisdictionId").toString().equals("0"));
			map.put("open", true);
		}
		JSONArray jsonArray=JSONArray.fromObject(jurisdictionList);
		return jsonArray;
	}
	public int deleteJurisdictionByRoleId(int roleId){
		return baseDao.deleteTBySql("TJurisdictionMapper.deleteByRoleId", roleId);
	}
	/**
	 * 保存权限
	 * @param roleId
	 * @param menuIds
	 * @return
	 */
	public Integer saveJurisdiction(int roleId,String menuIds){
		int result=0;
		try {
			deleteJurisdictionByRoleId(roleId);//首先删除所有的roleId下的权限
			String menuIdArr[]=menuIds.split(",");
			if(menuIdArr.length>1){
				for(int i=0;i<menuIdArr.length;i++){
					int menuId=Integer.parseInt(menuIdArr[i]);
					TJurisdiction jurisdiction=new TJurisdiction();
					jurisdiction.setRoleId(roleId);
					jurisdiction.setMenuId(menuId);
					baseDao.addT("TJurisdictionMapper.insertSelective", jurisdiction);
				}
			}
			result=1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public static void main(String args[]){
		  ApplicationContext ctx = new ClassPathXmlApplicationContext(
	                "applicationContext.xml");
		JurisdictionService jurisdictionService=(JurisdictionService) ctx.getBean("jurisdictionService");
		System.out.println(jurisdictionService.getJurisdictionMListByRoleId(1));
	}
}
