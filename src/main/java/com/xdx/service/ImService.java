package com.xdx.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xdx.dao.RedisGenericDao;
import com.xdx.entity.TAdmin;

@Service
public class ImService {
	@Resource(name = "redisGenericDao")
	private RedisGenericDao<String, Map<String, Object>> redisGenericDao;
	@Resource(name = "adminService")
	private AdminService adminService;

	/**
	 * 初始化整个admin的信息到redis中
	 * 
	 * @return
	 */
	public int setInitAdmin2Redis() {
		List<TAdmin> adminList = adminService.getAllAdminList();
		int result = 0;
		try {
			for (TAdmin admin : adminList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("username", admin.getTrueName());
				map.put("id", admin.getAdminId());
				map.put("avatar", admin.getUserFace());
				map.put("sign", admin.getBriefInfo());
				redisGenericDao.hmset("admin" + admin.getAdminId(), map);
			}
			result = 1;
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	/**
	 * 将admin的新增或更新数据放入redis
	 * 
	 * @param adminId
	 * @return
	 */
	public int setAdmin2Redis(int adminId) {
		TAdmin admin = adminService.getAdminById(adminId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", admin.getTrueName());
		map.put("id", admin.getAdminId());
		map.put("avatar", admin.getUserFace());
		map.put("sign", admin.getBriefInfo());
		return redisGenericDao.hmset("admin" + admin.getAdminId(), map) ? 1 : 0;
	}

	/**
	 * 封装im面板菜单数据
	 * 
	 * @param adminId
	 * @return
	 */
	public Map<String, Object> getImInit(Integer adminId) {
		List<TAdmin> adminList = adminService.getAllAdminList();
		Map<String, Object> initMap = new HashMap<String, Object>();
		initMap.put("code", 0);
		initMap.put("msg", "");
		Map<String, Object> data = new HashMap<String, Object>();
		Map<Object, Object> mine = redisGenericDao.hmget("admin"
				+ adminId.toString());
		mine.put("status", "online");
		data.put("mine", mine);
		// friend
		List<Map<String, Object>> friend = new ArrayList<Map<String, Object>>();
		Map<String, Object> friendGroup = new HashMap<String, Object>();
		friendGroup.put("id", 1);
		friendGroup.put("groupname", "我的好友");
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		for (TAdmin admin : adminList) {
			if (!admin.getAdminId().equals(adminId)) {
				Map<Object, Object> groupItem = redisGenericDao.hmget("admin"
						+ admin.getAdminId().toString());
				list.add(groupItem);
			}
		}
		friendGroup.put("list", list);
		friend.add(friendGroup);
		data.put("friend", friend);
		// group
		List<Map<String, Object>> group = new ArrayList<Map<String, Object>>();
		Map<String, Object> groupMap = new HashMap<String, Object>();
		groupMap.put("id", "101");
		groupMap.put("groupname", "聚贤庄");
		groupMap.put("avatar",
				"//tva1.sinaimg.cn/crop.0.0.200.200.50/006q8Q6bjw8f20zsdem2mj305k05kdfw.jpg");
		group.add(groupMap);
		data.put("group", group);
		initMap.put("data", data);
		return initMap;
	}

	/**
	 * 封装im群组成员菜单,这边groupId是用于查询特定群组的人员的 ， 因为我的项目只有一个群组，所以这个参数不重要，但留着来扩展
	 * 
	 * @param adminId
	 * @return
	 */
	public Map<String, Object> getMemberInit(int groupId, int adminId) {
		List<TAdmin> adminList = adminService.getAllAdminList();
		Map<String, Object> initMap = new HashMap<String, Object>();
		initMap.put("code", 0);
		initMap.put("msg", "");
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<Object, Object>> list = new ArrayList<Map<Object, Object>>();
		for (TAdmin admin : adminList) {
			if (!admin.getAdminId().equals(adminId)) {
				Map<Object, Object> memberItem = redisGenericDao.hmget("admin"
						+ admin.getAdminId().toString());
				list.add(memberItem);
			}
		}
		data.put("list", list);
		initMap.put("data", data);
		return initMap;
	}

	/**
	 * 用户上线
	 * 
	 * @param amdinId
	 * @return
	 */
	public int online(Integer adminId) {
		return redisGenericDao.hset("admin" + adminId.toString(), "status",
				"online") ? 1 : 0;
	}

	/**
	 * 用户下线
	 * 
	 * @param adminId
	 */
	public void offline(Integer adminId) {
		redisGenericDao.hdel("admin" + adminId.toString(), "status");
	}
}
