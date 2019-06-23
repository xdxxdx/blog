package com.xdx.service;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Service;

import com.xdx.constant.EMenuType;
import com.xdx.dao.BaseDao;
import com.xdx.dao.RedisGenericDao;
import com.xdx.entity.TMenu;
import com.xdx.util.ParamModel;

@Service
public class MenuService {
	@Resource(name="baseDao")
	private BaseDao<TMenu,Integer>baseDao;
	@Resource(name="redisGenericDao")
	private RedisGenericDao<String,String> redisGenericDao;
	public TMenu getMenuById(int menuId){
		return baseDao.getT("TMenuMapper.selectByPrimaryKey", menuId);
	}
	public TMenu getMenuByMenuName(String menuName){
		return baseDao.getT("TMenuMapper.selectMenuByMenuName", menuName);
	}
	public List<TMenu>getMenuListByPm(ParamModel pm){
		return baseDao.findTListByParam("TMenuMapper.selectListByPm", pm);
	}
	public int saveMenu(int type,TMenu menu){
		int result=0;
		if(type==1){
			//新增
			if(menu.getMenuType()==EMenuType.rootMenu.getValue()){
				//主菜单
				menu.setMenuLevel(1);
				result=baseDao.addT("TMenuMapper.insertSelectiveRePk", menu);
				if(result>0){
					menu.setPriority1(menu.getMenuId());
					baseDao.updateBySql("TMenuMapper.updateByPriority", menu);
				}
			}else{
				menu.setrMenuId(menu.getpMenuId());
				menu.setMenuLevel(2);
				result=baseDao.addT("TMenuMapper.insertSelectiveRePk", menu);
				if(result>0){
					TMenu pMenu=baseDao.getT("TMenuMapper.selectByPrimaryKey", menu.getpMenuId());
					menu.setPriority1(pMenu.getPriority1());
					menu.setPriority2(menu.getMenuId());
					baseDao.updateBySql("TMenuMapper.updateByPriority", menu);
				}
			}
		}else{
			//修改
			if(menu.getMenuType()==EMenuType.rootMenu.getValue()){
				result=baseDao.updateBySql("TMenuMapper.updateByPrimaryKeySelective", menu);
			}else{
				menu.setrMenuId(menu.getpMenuId());
				TMenu pMenu=baseDao.getT("TMenuMapper.selectByPrimaryKey", menu.getpMenuId());
				menu.setPriority1(pMenu.getPriority1());
				result=baseDao.updateBySql("TMenuMapper.updateByPrimaryKeySelective", menu);
			}
		}
		return result;
	}
	public int deleteMenu(TMenu menu){
		int result=0;
		if(baseDao.findTListByParam("TMenuMapper.selectChildMenuListByMenuId", menu.getMenuId()).isEmpty()){
			result=baseDao.deleteT("TMenuMapper.deleteByPrimaryKey", menu.getMenuId());
		}else{
			result=-1;
		}
		return result;
	}
	public List<TMenu>getRootMenuList(){
		return baseDao.findTList("TMenuMapper.selectRootMenu");
	}
	/**
	 * 递归获取菜单列表
	 * @return
	 */
	public List<TMenu>getAllMenuListRecursion(){
		return baseDao.findTList("TMenuMapper.selectRecursionAll");
	}
	/**
	 * 根据roleId获取菜单的递归
	 * @param roleId
	 * @return
	 */
	public List<TMenu>getRootMenuListByRoleId(int roleId){
		return baseDao.findTListByParam("TMenuMapper.selectRootByRoleId", roleId);
	}
	/**
	 * 根据父级菜单和所属的角色，获取其下级菜单
	 * @param pMenuId
	 * @param roleId
	 * @return
	 */
	public List<TMenu>getChildMenuListByPMenuIdAndRoleId(int pMenuId,int roleId){
		ParamModel pm=new ParamModel();
		pm.setpMenuId(pMenuId);
		pm.setRoleId(roleId);
		return baseDao.findTListByParam("TMenuMapper.selectByPMenuIdAndRoleId", pm);
	}
	public void saveMenu2Redis(Integer roleId){
		List<TMenu>menuTree=getRootMenuListByRoleId(roleId);
		for(TMenu menu:menuTree){
			List<TMenu>child=getChildMenuListByPMenuIdAndRoleId(menu.getMenuId(),roleId);
			menu.setChild(child);
		}
		JSONArray jsonArray=JSONArray.fromObject(menuTree);
//		redisDao.del_add(roleId.toString(), jsonArray.toString());
		redisGenericDao.set(roleId.toString(), jsonArray.toString());
	}
	public JSONArray getMenuTreeByRoleId(Integer roleId){
		JSONArray jsonArray=JSONArray.fromObject(redisGenericDao.get(roleId.toString()));
		return jsonArray;
	}
	/**
	 * 修改优先级
	 * @param menuId
	 * @param priority
	 * @return
	 */
	public Integer updatePriority(int menuId,int priority){
		Integer result=0;
		TMenu menu=getMenuById(menuId);
		if(menu.getMenuLevel()==1){
			menu.setPriority1(priority);
			//先更改自己的优先级
			if(baseDao.updateBySql("TMenuMapper.updateByPriority", menu)>0){
				result=baseDao.updateBySql("TMenuMapper.updateByPriority", menu);
			}
			//然后再更改其下级菜单的priority1
			baseDao.updateBySql("TMenuMapper.updateByPriority1ByPMenuId", menu);
		}else if(menu.getMenuLevel()==2){
			menu.setPriority2(priority);
			result=baseDao.updateBySql("TMenuMapper.updateByPriority", menu);
		}
		return result;
	}

}
