package com.xdx.controller.back;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
	private Map<String,Integer> field;

	public static void main(String args[]) throws NoSuchFieldException, SecurityException {
		Type t = TestController.class.getDeclaredField("field").getGenericType();
		if (ParameterizedType.class.isAssignableFrom(t.getClass())) {
			for (Type t1 : ((ParameterizedType) t).getActualTypeArguments()) {
				System.out.print(t1 + ",");
			}
			System.out.println();
		}
	}

}
