package com.xdx.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OutPutMsg {
	/**
	 * 响应Ajax请求，返回消息
	 * 
	 * @param msg
	 */
	public static void outPutMsg(HttpServletResponse response,HttpServletRequest request,String msg) {
		response.setCharacterEncoding("utf-8");
		String sessionId = request.getSession().getId();
		response.setContentType("text/html; charset=UTF-8"); 
		response.setHeader("Set-Cookie", "JSESSIONID=" + sessionId);
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
			writer.print(msg);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}
	}
}
