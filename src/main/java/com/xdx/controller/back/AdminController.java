package com.xdx.controller.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xdx.dao.RedisWebsocketDao;
import com.xdx.entity.TAdmin;
import com.xdx.entity.TRole;
import com.xdx.service.AdminService;
import com.xdx.service.ImService;
import com.xdx.service.RoleService;
import com.xdx.util.JsonUtil;
import com.xdx.util.MD5;
import com.xdx.util.ParamModel;

@Controller
public class AdminController {
	private static final String uploadFile = "/uploadFile/userFace";
	@Resource(name = "adminService")
	private AdminService adminService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "imService")
	private ImService imService;
	@Resource(name = "redisWebsocketDao")
	private RedisWebsocketDao redisWebsocketDao;

	@RequestMapping("treeData")
	public ModelAndView treeData() {
		ModelAndView mv = new ModelAndView("back/admin/TreeData");
		return mv;
	}

	@RequestMapping("admin/home")
	public ModelAndView adminHome() {
		ModelAndView mv = new ModelAndView("back/admin/AdminList");
		List<TRole> roleList = roleService.getAllRoleList();
		mv.addObject("roleList", roleList);
		return mv;
	}

	@RequestMapping("admin/add")
	public ModelAndView adminAdd() {
		ModelAndView mv = new ModelAndView("back/admin/AdminAdd");
		List<TRole> roleList = roleService.getAllRoleList();
		mv.addObject("roleList", roleList);
		return mv;
	}

	@RequestMapping("admin/edit")
	public ModelAndView adminEdit(int adminId) {
		TAdmin admin = adminService.getAdminById(adminId);
		ModelAndView mv = new ModelAndView("back/admin/AdminEdit");
		mv.addObject("admin", admin);
		List<TRole> roleList = roleService.getAllRoleList();
		mv.addObject("roleList", roleList);
		return mv;
	}

	@ResponseBody
	@RequestMapping("admin/list")
	public JSONObject adminList(ParamModel pm) {
		pm = ParamModel.getPm(pm);
		Map<String, Object> map = adminService.getAdminMapByPm(pm);
		JSONObject jsonObject = JsonUtil.mapToJsonObject(map);
		return jsonObject;
	}

	@ResponseBody
	@RequestMapping("admin/save")
	public String adminSave(int type, TAdmin admin) {
		Integer result = adminService.saveAdmin(type, admin);
		if(result>0){
			//更改或者新增redis的admin数据
			imService.setAdmin2Redis(admin.getAdminId());
		}
		return result.toString();
	}

	@ResponseBody
	@RequestMapping("admin/del")
	public String adminDel(int adminId) {
		Integer result = adminService.delAdmin(adminId);
		return result.toString();
	}

	// 判定用户名是否已使用
	@ResponseBody
	@RequestMapping("admin/adminByAdminName")
	public Map<String, Object> adminByAdminName(String adminName) {
		TAdmin admin = adminService.getAdminByAdminName(adminName);
		Map<String, Object> maps = new HashMap<String, Object>();
		if (admin == null) {
			maps.put("valid", true);
		} else {
			maps.put("valid", false);
		}
		return maps;
	}

	@RequestMapping("admin/pwd")
	public ModelAndView adminPwd() {
		ModelAndView mv = new ModelAndView("back/admin/AdminPwd");
		return mv;
	}

	@ResponseBody
	@RequestMapping("admin/pwdChange")
	public Map<String, Object> adminPwdChange(TAdmin admin) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = 0;
		String msg = "";
		if (!admin.getPwd().equals(admin.getConfirmPwd())) {
			msg = "两次新密码不相同";
		} else {
			TAdmin adminTmp = adminService.getAdminById(admin.getAdminId());
			if (adminTmp == null
					|| !adminTmp.getPwd().equals(MD5.cell32(admin.getOldPwd()))) {
				msg = "原密码输入错误";
			} else {
				admin.setPwd(MD5.cell32(admin.getPwd()));
				if (adminService.savePwdChange(admin) > 0) {
					msg = "修改密码成功";
					result = 1;
				} else {
					msg = "修改密码失败";
				}
			}
		}
		map.put("result", result);
		map.put("msg", msg);
		return map;
	}

	@RequestMapping("admin/adminFaceAndInfo")
	public ModelAndView adminFaceAndInfo(HttpSession session) {
		Integer adminId = (Integer) session.getAttribute("adminId");
		ModelAndView mv = new ModelAndView("back/admin/AdminUserFace");
		TAdmin admin = adminService.getAdminById(adminId);
		mv.addObject("admin", admin);
		return mv;
	}
	@ResponseBody
	@RequestMapping("admin/adminFaceAndInfoSave")
	public String adminFaceAndInfoSave(HttpServletRequest req, TAdmin admin) {
		MultipartFile upload = null;
		if (req instanceof MultipartHttpServletRequest) {
			upload = ((MultipartHttpServletRequest) req).getFile("upload");
		}
		Integer result = adminService.saveAdminFaceAndInfo(req, upload,
				uploadFile, admin);
		if(result>0){
			//更改或者新增redis的admin数据
			imService.setAdmin2Redis(admin.getAdminId());
		}
		return result.toString();
	}
	@ResponseBody
	@RequestMapping("admin/redisTest")
	public String redisTest(){
		redisWebsocketDao.set("xxxxxx", "1");
		return "1";
	}
}
