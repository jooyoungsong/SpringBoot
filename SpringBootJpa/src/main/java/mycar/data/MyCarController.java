package mycar.data;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/car")
public class MyCarController {

	@Autowired
	MyCarDao dao;
	
	@GetMapping("/carlist")  //시작...
	public ModelAndView list()
	{
		ModelAndView model=new ModelAndView();
		
		List<MyCarDto> list=dao.getAllDatas();
		
		model.addObject("list", list);
		model.addObject("totalCount", list.size());
		
		model.setViewName("carlist");
		
		return model;
	}
	
	@GetMapping("/carform")
	public String form()
	{
		return "addform";
	}
	
	//insert
	@PostMapping("/insert")
	public String insertMycar(@ModelAttribute MyCarDto dto, 
			MultipartFile carupload, HttpSession session)   //사진 추가 시, 이곳에 멀티파트 추가하거나 httpsession등 추가하면 된다.
	//파일 업로드할 때 , multparfile은 파일명을 저장하기 위해 필요하고, session은 사진 경로를 저장하기 위해 필요함.
	//httpSession과 httpRequestServlet은 동일한 개념(=getRealPath로 가는 것)인데, 차이는 한번 더 들어가냐 아니냐. 즉, httpSession이 간단함.
	//httpRequestServelet을 사용하면 request.getSession.getServletContext().getRealPath("/파일명");
	
	{
		//업로드할 save 위치 구하기
		String path=session.getServletContext().getRealPath("/save");
		//System.out.println(path);
		
		//업로드한 파일 dto 얻기(dto에 넣는다고 사진 올라가는 거 아니고 이제부터 하는 거 설정해줘야 올라감)
		dto.setCarphoto(carupload.getOriginalFilename());
		
		//실제 업로드
		try {
			carupload.transferTo(new File(path+"\\"+carupload.getOriginalFilename()));  //트라이캣치하기!
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dao.insertMyCar(dto);
		
		return "redirect:carlist";
	}
	
	//day1023
	@GetMapping("detail")
	public String detail(Long num,Model model)
	{
		
		MyCarDto dto=dao.getData(num);
		model.addAttribute("dto", dto);
		return "detail";
	}
	
	//update_get data
	@GetMapping("/updateform")
	public ModelAndView getDataMyCar(Long num)
	{
		ModelAndView model = new ModelAndView();
		
		MyCarDto dto=dao.getData(num);
		
		model.addObject("dto", dto);
		
		model.setViewName("uform");
		
		return model;
	}
	
	@PostMapping("/update")
	public String updateMyCar(@ModelAttribute MyCarDto dto)
	{
		dao.updateMyCar(dto);
		
		return "redirect:carlist";
	}
	
	@GetMapping("/delete")
	public String deleteMyCar(Long num)
	{
		dao.deleteMyCar(num);
		
		return "redirect:carlist";
	}
}
