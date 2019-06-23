package com.xdx.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.xdx.dao.BaseDao;
import com.xdx.entity.TAdmin;
import com.xdx.util.IOUtil;
import com.xdx.util.MD5;
import com.xdx.util.ParamModel;
import com.xdx.util.RandomUtil;

@Service
public class AdminService {
	@Resource(name="baseDao")
	private BaseDao<TAdmin,Integer>baseDao;
	public AdminService(){
		System.out.println("实例化amdinService,"+baseDao);
	}
	public TAdmin getAdminById(int adminId){
		return baseDao.getT("TAdminMapper.selectByPrimaryKey", adminId);
	}
	public List<TAdmin>getAdminListByPm(ParamModel pm){
		return baseDao.findTListByParam("TAdminMapper.selectListByPm", pm);
	}
	public List<TAdmin>getAllAdminList(){
		return baseDao.findTList("TAdminMapper.selectAllList");
	}
	public int getAdminSizeByPm(ParamModel pm){
		return (Integer) baseDao.getObject("TAdminMapper.selectSizeByPm", pm);
	}
	public Map<String,Object>getAdminMapByPm(ParamModel pm){
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("data", getAdminListByPm(pm));
		map.put("count", getAdminSizeByPm(pm));
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	public Integer saveAdmin(Integer type,TAdmin admin){
		if(type==1){
			admin.setPwd(MD5.cell32("xdx@1602"));
			return baseDao.addT("TAdminMapper.insertSelectiveRePk", admin);
		}else{
			return baseDao.updateT("TAdminMapper.updateByPrimaryKeySelective", admin);
		}
	}
	public Integer delAdmin(int adminId){
		return baseDao.deleteT("TAdminMapper.deleteByPrimaryKey", adminId);
	}
	public TAdmin getAdminByAdminName(String adminName){
		return baseDao.getT("TAdminMapper.selectAdminByAdminName", adminName);
	}
	public Integer savePwdChange(TAdmin admin){
		return baseDao.updateT("TAdminMapper.updatePwd", admin);
	}
	 public Integer saveAdminFaceAndInfo(HttpServletRequest req, MultipartFile upload, String uploadFile, TAdmin admin){
	        Integer result = 0;
	        String originalFileName =  upload.getOriginalFilename();
	        String suffix =  IOUtil.getFileSuffix(originalFileName);
	        String fileName =  RandomUtil.getTimeStampPlusRand() + "." + suffix;
	        String realPath =  req.getServletContext().getRealPath(uploadFile);
	        String fullPath = realPath + "/" + fileName;
	        	if(upload!=null&&upload.getSize()>0){
	        		String oldUserFace=admin.getUserFace();
	        		  try {
	    				upload.transferTo(new File(fullPath));
	    				} catch (IllegalStateException e) {
	    					e.printStackTrace();
	    				} catch (IOException e) {
	    					e.printStackTrace();
	    				}
	        		  admin.setUserFace(uploadFile + "/" + fileName);
	        		  if(baseDao.updateT("TAdminMapper.updateFaceAndInfo", admin)>0){
	        			  try {
	        				  IOUtil.deleteFile(req, oldUserFace);
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}
	        			  
	        		  }
	        		  result=1;
	        	}else{
	        		result=baseDao.updateT("TAdminMapper.updateFaceAndInfo", admin);
	        	}
	            return result;
	    }
}
