package boot.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberController {
	
	@GetMapping("/member/form")
	public String from()
	{
		return "/member/addform";
	}

	@GetMapping("/member/list")
	public ModelAndView form()
	{
		ModelAndView model=new ModelAndView();
		
		model.setViewName("/member/memberlist");
		
		return model;
	}
}