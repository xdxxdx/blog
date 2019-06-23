package com.xdx.controller.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VoteController {
	@RequestMapping("vote/info")
	public ModelAndView voteInfo(){
		ModelAndView mv=new ModelAndView("back/vote/VoteInfo");
		return mv;
	}
	@RequestMapping("vote/option")
	public ModelAndView voteOption(){
		ModelAndView mv=new ModelAndView("back/vote/VoteOption");
		return mv;
	}
}
