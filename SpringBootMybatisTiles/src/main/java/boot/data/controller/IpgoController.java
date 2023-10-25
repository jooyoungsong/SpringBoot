package boot.data.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import boot.data.dto.IpgoDto;
import boot.data.mapper.IpgoMapperInter;

@Controller
public class IpgoController {

	@Autowired
	IpgoMapperInter mapper;

	@GetMapping("/") // 단순 포워딩만 시키기- 접속하면 여기로 뜨게 하기
	public String start() {
		// return "redirect:ipgo/list";
		return "/layout/main";  //layout1 + Tiles 설정하고 와서 위에 return 형식 말고 이런  / 형식으로 바꿔줘함 (/폴더명/파일명으로 작성하기! why? 이 형식으로 작성한다고 Tiles.xml에 설정했기 때문)
	}

	@GetMapping("/ipgo/list") // 시작지점을 이렇게 했음
	public ModelAndView list() {
		ModelAndView model = new ModelAndView();

		int totalCount = mapper.getTotalCount();
		List<IpgoDto> list = mapper.getAllIpgos(); // insert이후 목록 뽑아낼 때

		model.addObject("totalCount", totalCount);
		model.addObject("list", list);
		// list 담아줘야지 model.setViewName("ipgolist");
		model.setViewName("/ipgo/ipgolist"); // tiles

		return model;
	}

	@GetMapping("/ipgo/ipgoform")
	public String form() {
		return "/ipgo/ipgoform";
	}

	@PostMapping("/ipgo/insert") //db에만 담는 거야! 
	 public String insert(@ModelAttribute IpgoDto dto,
	@RequestParam ArrayList<MultipartFile> photo, //@RequestParam MultipartFilephoto 는 사진 하나일 때, 지금은 사진 여러개니깐 arraylist!
	HttpSession session) 
	 { 
		 String path=session.getServletContext().getRealPath("/upload");
		 System.out.println(path);
	  
	//dto에 넣어줄 변수 지정해주기 
	String uploadName = "";
	 
	 if(photo.get(0).getOriginalFilename().equals("")) //지금 사진 여러개 하는 데 그중에서 첫번째가 ""이면 no 사진 없다고 표시해준 것! get(0)은 첫번째 사진이라고 생각하면 돼 
		 uploadName="no"; 
	 else 
	 {
		 for(MultipartFile f: photo) //photo에서 받은 걸 for문으로 돌려준다는 것 
			 { 	
			 	SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss"); 
			 	String fName=sdf.format(new Date())+"_"+f.getOriginalFilename();
			 	uploadName+=fName+","; //파일이름에 ,를 붙여서 uploadName에 누적시키기
	  
			 	//업로드 
			 	try {
			 		f.transferTo(new File(path+"\\"+fName));  //try-catch 
			 		} catch (IllegalStateException e) {
			 			// TODO Auto-generated catch block
			 			e.printStackTrace();
			 		} catch (IOException e) {
			 			// TODO Auto-generated catch block
			 			e.printStackTrace();
			 } 
	} 

	// for문 나와서 photo 마지막에 ","(컴마)를 제거해줘야한다.
	uploadName.substring(0,uploadName.length()-1);
}

dto.setPhotoname(uploadName);

mapper.insertIpgo(dto);

return"redirect:list";}

}
