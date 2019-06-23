package com.xdx.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xdx.constant.Const;

public class SpringInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();
		System.err.println(path);
		Pattern r = Pattern.compile(Const.NO_INTERCEPTOR_PATH);
		Matcher m = r.matcher(path);
		System.out.println("xdxx"+m.matches());
		if(path.matches(Const.NO_INTERCEPTOR_PATH)){
			return true;//匹配这些的将不受拦截
		}else{
			HttpSession session=request.getSession();
			if(session.getAttribute("adminId")!=null){
				return true;
			}else{
				System.out.println(request.getContextPath());
				response.sendRedirect(request.getContextPath()+Const.LOGIN_URL);
				return false;
			}
			
		}
	}

}
