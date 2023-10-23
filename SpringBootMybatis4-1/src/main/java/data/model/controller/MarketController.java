package data.model.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import data.model.dto.MarketDto;
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
		
		//select
		List<MarketDto> list=mapper.getAllSangpums();
		
		//저장
		model.addObject("totalCount", totalCount);
		model.addObject("list", list);
		
		//포워드
		model.setViewName("market/marketlist");
		
		return model;
	}
	
	
	//글쓰기->addform 이동
	@GetMapping("/market/writeform")
	public String write()
	{
		return "market/addform";
	}
	
	
	//addform에서 insert하기
	@PostMapping("/market/add")
	public String insertMarket(@ModelAttribute MarketDto dto,
			MultipartFile marketupload,HttpSession session)   //marketupload는 addform에서 적은 name과 같아야 함
	{
		//업로드할 save 위치 구하기
		String path=session.getServletContext().getRealPath("/save");
		
		//업로드한 파일에서 dto 얻기
		dto.setPhotoname(marketupload.getOriginalFilename());
		
		
		//실제 업로드 시키기
		try {
			marketupload.transferTo(new File(path+"\\"+marketupload.getOriginalFilename()));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//mapper > insert에서 dto 담기
		mapper.insertMarket(dto);
		
		return "redirect:list";
	}
	
	@GetMapping("/market/uform")
	public String updateform(Model model,String num)
	{
		
		MarketDto dto=mapper.getData(num);
		
		model.addAttribute("dto", dto);
		
		return "market/updateform";
	}
	
	@PostMapping("/market/update")
	public ModelAndView update(MarketDto dto,MultipartFile marketupload,HttpSession session)
	{
		ModelAndView model = new ModelAndView();
		
		//업로드할 save 위치 구하기
			String path=session.getServletContext().getRealPath("/save");
				
		//업로드한 파일에서 dto 얻기
			dto.setPhotoname(marketupload.getOriginalFilename());
				
				
		//실제 업로드 시키기
				try {
					marketupload.transferTo(new File(path+"\\"+marketupload.getOriginalFilename()));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		mapper.updateMarket(dto);
		
		model.setViewName("redirect:list");
		
		return model;
	}
	
	@GetMapping("/market/delete")
	public String delete(String num)
	{
		mapper.deleteMarket(num);
		System.out.println(num);
		
		return "redirect:list";
	}
}
