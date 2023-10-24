package data.model.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import data.model.dto.MarketDto;
import data.model.mapper.MarketMapperInter;
import data.model.service.MarketService;

@Controller
public class MarketController {

	/*
	 * @Autowired MarketMapperInter mapper;
	 */
	
	@Autowired
	MarketService service;
	
	//시작페이지를 바로 이동하게 하려면
	@GetMapping("/")
	public String start()
	{
		return "redirect:market/list";
	}
	
	@GetMapping("/market/list")
	public ModelAndView list()
	{
		ModelAndView model=new ModelAndView();
		
		//db로부터 총개수 얻기
		int totalCount=service.getTotalCount();
		
		//select
		List<MarketDto> list=service.getAllSangpums();
		
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
		
		//사진 안 넣으면 null로 표현(noimage),아니면 사진 넣기
		if(marketupload.getOriginalFilename().equals(""))
			dto.setPhotoname(null);
		else
		{
			
			//사진명 구해서 넣기
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			String photoname=sdf.format(new Date())+marketupload.getOriginalFilename();
			dto.setPhotoname(photoname);
			
			
			//실제 업로드 시키기
			try {
				marketupload.transferTo(new File(path+"\\"+photoname));
				//marketupload.transferTo(new File(path+"\\"+marketupload.getOriginalFilename()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		//mapper > insert에서 dto 담기
		service.insertMarket(dto);
		
		return "redirect:list";
	}
	
	@GetMapping("/market/uform")
	public String updateform(Model model,String num)
	{
		
		MarketDto dto=service.getData(num);
		
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
				
			if(marketupload.getOriginalFilename().equals(""))
				dto.setPhotoname("no"); //null로 하게 되면 equals가 못 받는다
			else
			{
				
				//사진명 구해서 넣기
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
				String photoname=sdf.format(new Date())+marketupload.getOriginalFilename();
				dto.setPhotoname(photoname);
				
				
				//실제 업로드 시키기
				try {
					marketupload.transferTo(new File(path+"\\"+photoname));
					//marketupload.transferTo(new File(path+"\\"+marketupload.getOriginalFilename()));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}	
			
		service.updateMarket(dto);
		
		model.setViewName("redirect:list");
		
		return model;
	}
	
	@GetMapping("/market/delete")
	public String delete(String num, HttpServletRequest request)  //@requestparam은 생략 가능  // HttpServletRequest나 HttpSession 써도 가능!(대신 쓰는 법 다름)
	{
		String photo=service.getData(num).getPhotoname(); //num에 해당하는 photo 가져오기
		
		//사진 없으면 파일 삭제할 게 없을 수도 있어서 조건 주기 + 사진 있으면 파일에서 삭제하기
		if(!photo.equals("no"))  //null로 하게 되면 equals가 못 받는다
		{
			String path = request.getServletContext().getRealPath("/save");
			
			File file=new File(path+"\\"+photo);
			file.delete();
		}
		
		service.deleteMarket(num);   //기존에는 mapper.deleteMarket(num);인데 day1024 오늘 service 배워서 바꿔 봄!
		System.out.println(num);     //num값 넘어갔는 지 확인해봄(생략 가능)!
		
		return "redirect:list";
	}
}
