package com.xdx.controller.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdx.constant.EMenuType;
import com.xdx.entity.TMenu;
import com.xdx.entity.TRole;
import com.xdx.service.MenuService;
import com.xdx.service.RoleService;
import com.xdx.util.ParamModel;

@Controller
public class MenuController {
	@Resource(name="menuService")
	private MenuService menuService;
	@Resource(name="roleService")
	private RoleService roleService;
	@RequestMapping("menu/home")
	public ModelAndView menuHome(ParamModel pm){
		ModelAndView mv=new ModelAndView("back/menu/MenuList");
		mv.addObject("pm", pm);
		return mv;
	}
	@ResponseBody
	@RequestMapping("menu/list")
	public List<TMenu>menuList(ParamModel pm){
		List<TMenu>menuList=menuService.getMenuListByPm(pm);
		return menuList;
	}
	@RequestMapping("menu/add")
	public ModelAndView menuAdd(){
		ModelAndView mv=new ModelAndView("back/menu/MenuAdd");
//		List<TMenu>parentMenuList=menuService.getRootMenuList();
//		mv.addObject("parentMenuList", parentMenuList);
		return mv;
	}
	@RequestMapping("menu/edit")
	public ModelAndView menuEdit(int menuId){
		TMenu menu=menuService.getMenuById(menuId);
		ModelAndView mv=new ModelAndView("back/menu/MenuEdit");
		mv.addObject("menu", menu);
//		List<TMenu>parentMenuList=menuService.getRootMenuList();
//		mv.addObject("parentMenuList", parentMenuList);
		return mv;
	}
	@ResponseBody
	@RequestMapping("menu/save")
	public String menuSave(int type,TMenu menu){
		Integer result=menuService.saveMenu(type, menu);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("menu/del")
	public String menuDel(TMenu menu){
		Integer result=menuService.deleteMenu(menu);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("menu/menuListByMenuType")
	public JSONObject menuListByMenuType(int menuType,int pMenuId){
//		Map<String,Object>maps=new HashMap<String, Object>();
		JSONObject jsonObject=new JSONObject();
		if(menuType==EMenuType.childMenu.getValue()){
			//主菜单
			List<TMenu>parentMenuList=menuService.getRootMenuList();
			if(!parentMenuList.isEmpty()){
				if(pMenuId!=0){
					TMenu pMenu=menuService.getMenuById(pMenuId);
					jsonObject.put("xdx0"+pMenu.getMenuId().toString(), pMenu.getMenuName());
					for(TMenu menu:parentMenuList){
						if(menu.getMenuId()!=pMenuId){
							jsonObject.put("xdx1"+menu.getMenuId().toString(), menu.getMenuName());
						}
					}
				}else{
					for(TMenu menu:parentMenuList){
						jsonObject.put("xdx0"+menu.getMenuId().toString(), menu.getMenuName());
					}
				}
				
			}
		}else{
			jsonObject.put("", "无");
		}
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("menu/menuNameOnly")
	public Map<String,Object>menuNameOnly(String menuName){
		Map<String,Object>map=new HashMap<String, Object>();
		TMenu menu=menuService.getMenuByMenuName(menuName);
		if(menu==null){
			map.put("valid", true);
		}else{
			map.put("valid", false);
		}
		return map;
	}
	@ResponseBody
	@RequestMapping("menu/tree")
	public JSONArray menuTree(Integer roleId){
//		List<TMenu>menuTree1=menuService.getAllMenuListRecursion();
		JSONArray menuTree=menuService.getMenuTreeByRoleId(roleId);
//		JSONArray menuTree=JSONArray.fromObject(menuTree1);
		return menuTree;
	}
	@ResponseBody
	@RequestMapping("menu/priority")
	public String menuPriority(int menuId,int priority){
		Integer result=menuService.updatePriority(menuId, priority);
		if(result>0){
			List<TRole>roleList=roleService.getAllRoleList();
			for(TRole role:roleList){
				menuService.saveMenu2Redis(role.getRoleId());
			}
		}
		return result.toString();
	}

}
