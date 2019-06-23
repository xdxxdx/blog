package com.xdx.websocket;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.java_websocket.WebSocket;

public class WsPool {
	public static final Map<WebSocket,String>userConnections=new HashMap<WebSocket, String>();
	public static String getUserIdByWs(WebSocket conn){
		return userConnections.get(conn);
	}
	/**
	 * 通过userId获取第一个被查到的Websocket(一个userId可能对应多个websocket，比如打开多个页面，将生成多个websocket)
	 * 
	 * @param user
	 */
	public static WebSocket getWebSocketByUserId(String userId) {
		Set<WebSocket> keySet = userConnections.keySet();
		synchronized (keySet) {
			for (WebSocket conn : keySet) {
				String cuser = userConnections.get(conn);
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
	public static Set<WebSocket> getWebSocketSetByUserId(String userId){
		Set<WebSocket>wsSet=new HashSet<WebSocket>();
		Set<WebSocket> keySet = userConnections.keySet();
		synchronized (keySet) {
			for (WebSocket conn : keySet) {
				String cuser = userConnections.get(conn);
				if (cuser.equals(userId)) {
					wsSet.add(conn);
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
	public static void addElement(WebSocket conn,String userId){
		userConnections.put(conn, userId);
	}
	/**
	 * 移除元素
	 * @param conn
	 * @return
	 */
	public static boolean removeElement(WebSocket conn) {
		if (userConnections.containsKey(conn)) {
			userConnections.remove(conn); // 移除连接
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 向指定的userId发送信息
	 * @param userId
	 * @param message
	 */
	public static void sendMsg2UserId(String userId,String message){
		Set<WebSocket> keySet = userConnections.keySet();
		synchronized (keySet) {
			for (WebSocket conn : keySet) {
				if (userConnections.get(conn).equals(userId)) {
					conn.send(message);
				}
			}
		}
	}
	/**
	 * 向所有用户发送消息
	 * @param message
	 */
	public static void sendMsg2All(String message){
		Set<WebSocket> keySet = userConnections.keySet();
		synchronized (keySet) {
			for (WebSocket conn : keySet) {
				conn.send(message);
			}
		}
	}
	/**
	 * 向所有除了自己以外的用户发送消息
	 * @param message
	 * @param userId
	 */
	public static void sendMsg2AllExSelf(String message,String userId){
		Set<WebSocket> keySet = userConnections.keySet();
		synchronized (keySet) {
			for (WebSocket conn : keySet) {
				if (!userConnections.get(conn).equals(userId)) {
					conn.send(message);
				}
			}
		}
	}
	/**
	 * 判断用户是否在线
	 * @param userId
	 * @return
	 */
	public static Boolean isOnline(String userId) {
		Set<WebSocket> keySet = userConnections.keySet();
		synchronized (keySet) {
			for (WebSocket conn : keySet) {
				String id = userConnections.get(conn);
				if (userId.equals(id)) {
					return true;
				}
			}
			return false;
		}
	}

}
