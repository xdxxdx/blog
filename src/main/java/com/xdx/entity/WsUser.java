package com.xdx.entity;

import org.java_websocket.WebSocket;

public class WsUser {
	private WebSocket webSocket;
	private String userId;
	public WebSocket getWebSocket() {
		return webSocket;
	}
	public void setWebSocket(WebSocket webSocket) {
		this.webSocket = webSocket;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((webSocket == null) ? 0 : webSocket.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WsUser other = (WsUser) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (webSocket == null) {
			if (other.webSocket != null)
				return false;
		} else if (!webSocket.equals(other.webSocket))
			return false;
		return true;
	}
	
}
