package com.zjyx.vote.admin.controller.authority;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {

	@RequestMapping("/helloworld")
	public ModelAndView helloworld(String page){
		ModelAndView mv = new ModelAndView("views/"+page);
		return mv;
	}
	
	@RequestMapping("/helloworld1")
	@ResponseBody
	public String helloworld1(){
		return "hello world!!!";
	}
}
