package com.xdx.controller.back;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xdx.constant.Const;
import com.xdx.entity.TAdmin;
import com.xdx.service.AdminService;
import com.xdx.util.IOUtil;
import com.xdx.util.JsonUtil;
import com.xdx.util.MD5;
import com.xdx.util.RandomUtil;

@Controller
public class MainController {
	@Resource(name = "adminService")
	private AdminService adminService;

	@RequestMapping("head")
	public ModelAndView head() {
		return new ModelAndView("back/common/Head");
	}

	@RequestMapping("side")
	public ModelAndView side() {
		return new ModelAndView("back/common/Side");
	}

	@RequestMapping("admin")
	public ModelAndView admin(String adminName, String password,
			HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if (adminName != null && password != null) {
			TAdmin admin = adminService.getAdminByAdminName(adminName);
			if (admin != null && admin.getPwd().equals(MD5.cell32(password))) {
				session.setAttribute("adminName", adminName);
				session.setAttribute("adminId", admin.getAdminId());
				session.setAttribute("roleId", admin.getRoleId());
				session.setAttribute("userFace", admin.getUserFace()==null?"/static/img/xdx.jpg":admin.getUserFace());
				mv.setViewName("redirect:home");
			} else {
				mv.addObject("msg", "账号或密码错误");
				mv.setViewName("back/login/Login");
			}
		} else {
			mv.setViewName("back/login/Login");
		}
		return mv;
	}

	@RequestMapping("home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("back/main/Home");
		return mv;
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();// 清除session
		return "redirect:/login";
	}

	@ResponseBody
	@RequestMapping("wangUpload")
	public JSONObject wangUpload(HttpServletRequest req) {
		MultipartFile upload = null;
		if (req instanceof MultipartHttpServletRequest) {
			upload = ((MultipartHttpServletRequest) req)
					.getFile("wangUploadName");
		}
		Map<String, Object> maps = IOUtil.upload(req, upload,
				Const.WANGEDITOR_UPLOAD);
		JSONObject jsonObject=JsonUtil.mapToJsonObject(maps);
		return jsonObject;
	}
	@RequestMapping("mdUpload")
	@ResponseBody
	public JSONObject editormdPic (@RequestParam(value = "editormd-image-file", required = true) MultipartFile file, HttpServletRequest request,HttpServletResponse response) throws Exception{

	        String trueFileName = file.getOriginalFilename();  

	        String suffix = trueFileName.substring(trueFileName.lastIndexOf("."));

	        String fileName = RandomUtil.getTimeStampPlusRand()+suffix;  

	        String path = request.getServletContext().getRealPath(Const.MD_UPLOAD);
	        File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	           targetFile.mkdirs();  
	        }  
	       //保存  
	        try {  
	           file.transferTo(targetFile);  
	        } catch (Exception e) {  
	           e.printStackTrace();  
	        }  


	        JSONObject res = new JSONObject();
	        res.put("url", Const.MD_UPLOAD+"/"+fileName);
	        res.put("success", 1);
	        res.put("message", "upload success!");
	        return res;

	    }
}
