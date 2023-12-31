package boot.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import boot.dto.PersonDto;


@Controller
public class FormController {

	/*
	 * @GetMapping("/sist/form1") public String form1() { return "form/form1"; }
	 * 
	 * @GetMapping("/sist/form2") public String form2() { return "form/form2"; }
	 * 
	 * @GetMapping("/sist/form3") public String form3() { return "form/form3"; }
	 */
	
	/*
	 * //값읽기
	 * 
	 * @PostMapping("/sist/read1") public ModelAndView readform1(
	 * 
	 * @RequestParam String irum,
	 * 
	 * @RequestParam int java,
	 * 
	 * @RequestParam int spring) { ModelAndView model=new ModelAndView();
	 * 
	 * //저장 model.addObject("irum", irum); model.addObject("java", java);
	 * model.addObject("spring", spring); model.addObject("tot", java+spring);
	 * model.addObject("avg", (java+spring)/2.0);
	 * 
	 * 
	 * //포워드경로 model.setViewName("result/result1");
	 * 
	 * return model; }
	 */
	
	/*
	 * //dto
	 * 
	 * @PostMapping("/sist/write2") public String formread2(@ModelAttribute("dto")
	 * PersonDto dto) { return "result/result2"; }
	 */
	
	//map
	//map으로 읽을 경우 폼의 name이 key값으로! 입력값은 value로! 
	/*
	 * @PostMapping("/sist/myread") public ModelAndView formread3(@RequestParam
	 * Map<String, String> map) { ModelAndView model=new ModelAndView();
	 * 
	 * model.addObject("name", map.get("name")); model.addObject("blood",
	 * map.get("blood")); model.addObject("age", map.get("age"));
	 * 
	 * model.setViewName("result/result3");
	 * 
	 * return model; }
	 */
	
	@GetMapping("/sist/form1")
	public String form1()
	{
		return "form/form1";
	}
	
	@GetMapping("/sist/form2")
	public String form2()
	{
		return "form/form2";
	}
	
	@GetMapping("/sist/form3")
	public String form3()
	{
		return "form/form3";
	}
	
	@PostMapping("/sist/read1")
	public ModelAndView result1(@RequestParam String irum,
			@RequestParam String  java,
			@RequestParam String spring)
	{
		ModelAndView model= new ModelAndView();
		
		model.addObject("irum",irum);
		model.addObject("java",java);
		model.addObject("spring",spring);
		
		int tot = Integer.parseInt(java)+Integer.parseInt(spring);
		
		model.addObject("tot",tot);
		model.addObject("avg",tot/2.0);
		
		model.setViewName("result/result1");
		
		return model;
	}
	
	@PostMapping("/sist/write2")
	//requestparam은 읽기만 하는 것, modelattribute는 dto에 있는 값들의 
	public String result2(@ModelAttribute PersonDto dto,Model model)
	{
		model.addAttribute("dto", dto);
		
		return "result/result2";
	}
	
	@PostMapping("/sist/myread")
	public ModelAndView result3(@RequestParam Map<String, String> map)
	{
		ModelAndView model=new ModelAndView();
		
		model.addAllObjects(map);  //아래 3개를 한 줄로 표현 가능
		
		/*
		 * model.addObject("name", map.get("name"));
		 * model.addObject("age",map.get("age")); 
		 * model.addObject("blood", map.get("blood"));
		 */
		
		model.setViewName("result/result3");
		
		return model;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
