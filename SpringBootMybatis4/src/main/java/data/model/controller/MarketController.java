package data.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import data.model.mapper.MarketMapperInter;

@Controller
public class MarketController {

	@Autowired
	MarketMapperInter mapper;
	
	@GetMapping("/market/list")
	public ModelAndView list()
	{
		ModelAndView model=new ModelAndView();
		
		//db로부터 총개수 얻기
		int totalCount=mapper.getTotalCount();
		
		//저장
		model.addObject("totalCount", totalCount);
		//포워드
		model.setViewName("market/marketlist");
		
		return model;
	}
}
