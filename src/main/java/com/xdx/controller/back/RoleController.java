package com.xdx.controller.back;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xdx.entity.TRole;
import com.xdx.service.JurisdictionService;
import com.xdx.service.MenuService;
import com.xdx.service.RoleService;
import com.xdx.util.JsonUtil;
import com.xdx.util.ParamModel;

@Controller
public class RoleController {
	@Resource(name="roleService")
	private RoleService roleService;
	@Resource(name="jurisdictionService")
	private JurisdictionService jurisdictionService;
	@Resource(name="menuService")
	private MenuService menuService;
	@RequestMapping("role/home")
	public ModelAndView roleHome(){
		return new ModelAndView("back/role/RoleList");
	}
	@RequestMapping("role/add")
	public ModelAndView roleAdd(){
		return new ModelAndView("back/role/RoleAdd");
	}
	@RequestMapping("role/edit")
	public ModelAndView roleEdit(int roleId){
		TRole role=roleService.getRoleById(roleId);
		ModelAndView mv=new ModelAndView("back/role/RoleEdit");
		mv.addObject("role", role);
		return mv; 
	}
	@ResponseBody
	@RequestMapping("role/list")
	public JSONObject roleList(ParamModel pm){
		Map<String,Object>maps=roleService.getRoleMapByPm(pm);
		JSONObject jsonObject=JsonUtil.mapToJsonObject(maps);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("role/save")
	public String roleSave(int type,TRole role){
		Integer result=roleService.saveRole(type, role);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("role/del")
	public String roleHidden(int roleId){
		Integer result=roleService.hiddenRole(roleId);
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("role/roleByRoleName")
	public Map<String,Object>roleByRoleName(String roleName){
		TRole role=roleService.getRoleByRoleName(roleName);
		Map<String, Object> maps = new HashMap<String, Object>();
		if (role == null) {
			maps.put("valid", true);
		} else {
			maps.put("valid", false);
		}
		return maps;
	}
	@RequestMapping("role/jurisdiction")
	public ModelAndView jurisdiction(int roleId){
		ModelAndView mv=new ModelAndView("back/jurisdiction/Jurisdiction");
		mv.addObject("roleId", roleId);
		return mv;
	}
	@ResponseBody
	@RequestMapping("role/jurisdictionTree")
	public JSONArray jurisdictionTree(int roleId){
		return jurisdictionService.getJurisdictionJsonByRoleId(roleId);
	}
	@ResponseBody
	@RequestMapping("role/jurisdictionSave")
	public String jurisdictionSave(int roleId,String menuIds){
		Integer result=jurisdictionService.saveJurisdiction(roleId, menuIds);
		if(result>0){
			//将角色与菜单的数据存入到redis中
			menuService.saveMenu2Redis(roleId);
		}
		return result.toString();
	}

}
