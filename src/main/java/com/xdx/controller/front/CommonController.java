package com.xdx.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class CommonController {
	@RequestMapping("clientHead")
	public ModelAndView clientHead(){
		return new ModelAndView("front/common/ClientHead");
	}
}
