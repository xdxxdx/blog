package com.xdx.filter;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.java_websocket.WebSocketImpl;

import com.xdx.websocket.WsServer;


public class StartFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig arg0) throws ServletException {
		startWebsocket();

	}
	public void startWebsocket() {
		System.out.println("开始启动websocket");
		WebSocketImpl.DEBUG = false;
		WsServer s;
		try {
			s = new WsServer(8888);
			s.start();
			System.out.println("websocket启动成功了");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
