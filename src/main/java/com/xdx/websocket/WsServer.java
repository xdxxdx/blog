package com.xdx.websocket;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.xdx.service.ImService;


public class WsServer extends WebSocketServer{
	private ImService imService;
	private WsPoolRedis wsPoolRedis;
	public WsServer(int port) {
		super(new InetSocketAddress(port));
		WebApplicationContext webApplicationContext = ContextLoader
                .getCurrentWebApplicationContext();
		imService = (ImService) webApplicationContext.getBean("imService");
		wsPoolRedis=(WsPoolRedis)webApplicationContext.getBean("wsPoolRedis");
	}
	public WsServer(InetSocketAddress address) {
		super(address);
		WebApplicationContext webApplicationContext = ContextLoader
                .getCurrentWebApplicationContext();
		imService = (ImService) webApplicationContext.getBean("imService");
		wsPoolRedis=(WsPoolRedis)webApplicationContext.getBean("wsPoolRedis");
	}

	@Override
	public void onClose(WebSocket conn, int code, String reason, boolean remote) {
		String userId=WsPool.getUserIdByWs(conn);
		WsPool.removeElement(conn);	
		//reids上离线
		imService.offline(Integer.parseInt(userId));
		//通知客户端他下线了
		JSONObject json=new JSONObject();
		json.put("type", "offline");
		json.put("content", userId);
		WsPool.sendMsg2All(json.toString());
		System.out.println(conn+" is close");
	}

	@Override
	public void onError(WebSocket conn, Exception e) {
		// 连接出错的时候触发的代码
		System.out.println(conn+" is onerror");
		e.printStackTrace();
		
	}

	@Override
	public void onMessage(WebSocket conn, String message) {
		JSONObject msg=JSONObject.fromObject(message);
		String type=msg.getString("type");
		if (type.equals("onopen")) {
			String data=msg.getString("data");
			String userId = data.substring(0, data.indexOf("连接成功"));
			WsPool.addElement(conn, userId); // 向连接池添加当前的连接对象
			wsPoolRedis.addElement(conn, userId);
			//redis上上线
			imService.online(Integer.parseInt(userId));//上线
			//通知客户端大家他上线了
			JSONObject json=new JSONObject();
			json.put("type", "online");
			json.put("content", userId);
			WsPool.sendMsg2All(json.toString());
			return;
		}else if(type.equals("chat")){
			JSONObject data=msg.getJSONObject("data");
			System.out.println(data);
			JSONObject mine=data.getJSONObject("mine");
			JSONObject to=data.getJSONObject("to");
			JSONObject json=new JSONObject();
			json.put("type", "chat");
			JSONObject content=new JSONObject();
			content.put("username", mine.get("username"));
			content.put("avatar", mine.get("avatar"));
			content.put("type", to.get("type"));
			content.put("content", mine.get("content"));
			content.put("fromid", mine.get("id"));
			content.put("timestamp", new Date().getTime());
			if(to.get("type").equals("friend")){
				content.put("id", mine.get("id"));
				json.put("content", content);
				WsPool.sendMsg2UserId(to.getString("id"), json.toString());
			}else{
				content.put("id", to.get("id"));
				json.put("content", content);
				WsPool.sendMsg2AllExSelf(json.toString(),mine.get("id").toString());
			}
		}else if (type.equals("heartbeat")) {
			JSONObject json=new JSONObject();
			json.put("type", "pong");
			json.put("content", "pong");
			conn.send(json.toString());// 心跳信息
		}
	}

	@Override
	public void onOpen(WebSocket conn, ClientHandshake hand) {
		// ws连接的时候触发的代码，onOpen中我们不做任何操作
		System.out.println(conn+" is open "+hand);
		
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		
	}
	public static void main(String args[]){
		 WebSocketImpl.DEBUG = false;
		 int port = 8888; // 端口
		 WsServer s = new WsServer(port);
		 s.start();
		 System.out.println( "服务器的端口" + s.getPort() );
	}

}
