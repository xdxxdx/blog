package com.xdx.websocket;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.java_websocket.WebSocket;
import org.springframework.stereotype.Component;

import com.xdx.dao.RedisGenericDao;

@Component
public class WsPoolRedis {
	@Resource(name="redisGenericDao")
	private RedisGenericDao<String, WebSocket>redisWsSetDao;
	@Resource(name="redisGenericDao")
	private RedisGenericDao<WebSocket,String>redisWsUserDao;
	public  String getUserIdByWs(WebSocket conn){
		return (String) redisWsUserDao.get(conn);
	}
	/**
	 * 通过userId获取第一个被查到的Websocket(一个userId可能对应多个websocket，比如打开多个页面，将生成多个websocket)
	 * 
	 * @param user
	 */
	public  WebSocket getWebSocketByUserId(String userId) {
		Set<WebSocket>userConnections=redisWsSetDao.sGet("wsConnection");
		synchronized (userConnections) {
			for (WebSocket conn : userConnections) {
				String cuser = getUserIdByWs(conn);
				if (cuser.equals(userId)) {
					return conn;
				}
			}
		}
		return null;
	}
	/**
	 * 通过userId获取Websocket的set集合
	 * @param userId
	 * @return
	 */
	public  Set<WebSocket> getWebSocketSetByUserId(String userId){
		Set<WebSocket>userSet=new HashSet<WebSocket>();
		Set<WebSocket>wsSet=redisWsSetDao.sGet("wsConnection");
		synchronized (wsSet) {
			for (WebSocket conn : wsSet) {
				String cuser = getUserIdByWs(conn);
				if (cuser.equals(userId)) {
					userSet.add( conn);
				}
			}
		}
		return wsSet;
	}
	/**
	 * 添加元素
	 * @param conn
	 * @param userId
	 */
	public  void addElement(WebSocket conn,String userId){
		if(redisWsUserDao.set(conn, userId)){
			redisWsSetDao.sSet("wsConnection", conn);
		}
		
	}
	/**
	 * 移除元素
	 * @param conn
	 * @return
	 */
	public  boolean removeElement(WebSocket conn) {
		redisWsUserDao.del(conn);
		if(redisWsSetDao.setRemove("wsConnection", conn)>0){
			return true;
		}else{
			return false;
		}
	}
}
