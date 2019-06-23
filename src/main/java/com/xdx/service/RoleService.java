package com.xdx.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xdx.dao.BaseDao;
import com.xdx.entity.TAdmin;
import com.xdx.entity.TRole;
import com.xdx.util.ParamModel;
@Service
public class RoleService {
	@Resource(name="baseDao")
	private BaseDao<TRole,Integer>baseDao;
	public TRole getRoleById(int roleId){
		return baseDao.getT("TRoleMapper.selectByPrimaryKey", roleId);
	}
	public List<TRole>getAllRoleList(){
		return baseDao.findTList("TRoleMapper.selectAllList");
	}
	public List<TRole>getRoleListByPm(ParamModel pm){
		return baseDao.findTListByParam("TRoleMapper.selectListByPm", pm);
	}
	public int getRoleSizeByPm(ParamModel pm){
		return (Integer) baseDao.getObject("TRoleMapper.selectSizeByPm", pm);
	}
	public Map<String,Object>getRoleMapByPm(ParamModel pm){
		baseDao.setT(new TRole());
		System.out.println("实例化后roleService,"+baseDao+","+baseDao.getT());
		Map<String,Object>maps=new HashMap<String, Object>();
		maps.put("data", getRoleListByPm(pm));
		maps.put("count", getRoleSizeByPm(pm));
		maps.put("code", 0);
		maps.put("msg", "");
		return maps;
	}
	public Integer saveRole(int type,TRole role){
		if(type==1){
			return baseDao.addT("TRoleMapper.insertSelective", role);
		}else{
			return baseDao.updateT("TRoleMapper.updateByPrimaryKeySelective", role);
		}
	}
	public Integer delRole(int roleId){
		return baseDao.deleteT("TRoleMapper.deleteByPrimaryKey", roleId);
	}
	public Integer hiddenRole(int roleId){
		TRole role=new TRole();
		role.setRoleId(roleId);
		role.setIsDel(1);
		return baseDao.updateT("TRoleMapper.updateByPrimaryKeySelective", role);
	}
	public TRole getRoleByRoleName(String roleName){
		return baseDao.getT("TRoleMapper.selectRoleByRoleName", roleName);
	}

}
