package com.xdx.controller.back;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xdx.service.AdminService;
import com.xdx.service.ImService;

@Controller
public class ImController {
	@Resource(name="imService")
	private ImService imService;
	@Resource(name="adminService")
	private AdminService adminService;
	/**
	 * 初始化admin的redis数据
	 * @return
	 */
	@ResponseBody
	@RequestMapping("im/initAdmin2Redis")
	public Integer initAdmin(){
		return imService.setInitAdmin2Redis();
	}
	@ResponseBody
	@RequestMapping("im/init")
	public Map<String,Object>imInit(int adminId){
		return imService.getImInit(adminId);
	}
	@ResponseBody
	@RequestMapping("im/member")
	public Map<String,Object>memberInit(int id,HttpSession session){
		int adminId=(Integer) session.getAttribute("adminId");
		Map<String,Object>map=imService.getMemberInit(id, adminId);
		return map;
	}
}
