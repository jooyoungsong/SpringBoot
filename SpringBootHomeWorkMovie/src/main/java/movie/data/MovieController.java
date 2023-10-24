package movie.data;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	MovieDao dao;
	
	@GetMapping("/")
	public String start()
	{
		return "movielist";
	}
	
	@GetMapping("/writeform")
	public String formMovie()
	{
		return "addform";
	}
	
	@PostMapping("/insert")
	public String insertMovie(@ModelAttribute MovieDto dto,
			MultipartFile photo,HttpSession session)
	{
		String path=session.getServletContext().getRealPath("/moviephoto");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHss");
		
		dto.setPoster(photo.getOriginalFilename()+"_"+sdf.format(new Date()));
		
		try {
			photo.transferTo(new File(path+"\\"+photo.getOriginalFilename()+"_"+sdf.format(new Date())));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao.insertMovie(dto);
		
		return "redirect:list";
	}
	
	@GetMapping("/list")
	public String listMovie(Model model)
	{
		List<MovieDto> list = dao.getAllData();
		//인터페이스는 객체가 아니라 new 생성 못해
		//list(인터페이스) = new로 객체 생성하면, new arraylist<>나 new vector<>로 써준다
		
		model.addAttribute("list", list);
		
		return "movielist";
	}
	
	@GetMapping("/detail")
	public String detailMovie(Model model,long num)
	{
		
		MovieDto dto=dao.getData(num);
		
		model.addAttribute("dto", dto);
		
		return "detail";
	}
	
}
