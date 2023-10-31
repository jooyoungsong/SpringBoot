package boot.data.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import boot.data.dto.MemberDto;
import boot.data.service.MemberServiece;

@Controller
public class MemberController {
	
	@Autowired
	MemberServiece service;
	
	/*
 	@GetMapping("/member/myinfo") public String info() 
 	{ 
 		return "/member/myinfo";
	}
	*/
	
	@GetMapping("/member/myinfo")
	public ModelAndView memberform(@RequestParam String id) {
		
		ModelAndView model=new ModelAndView();
		
		MemberDto dto=service.getDataById(id);
		
		model.addObject("dto", dto);
		
		model.setViewName("/member/myinfo");
		
		return model;
	}

	@GetMapping("/member/list")
	public ModelAndView list()
	{
		ModelAndView model=new ModelAndView();
		
		//전체 출력을 추가해줌
		List<MemberDto> list=service.getAllMembers();
		
		model.addObject("list", list);
		model.addObject("totalCount", list.size());  //총 몇개가 출력되었는 지 숫자로 보여주려고 추가함
		
		model.setViewName("/member/memberlist");
		
		return model;
	}
	
	@GetMapping("/member/form") 
	public String form()
	{
		return "/member/addform";
	}
	
	//아이디체크 _success/fail_0인지 1인지 받아와야 함
	@GetMapping("/member/idcheck")  //임의주소라 아무주소나 써도 되는 것
	@ResponseBody  //responsebody로 해야 json으로 받을 수 있음
	public Map<String, Integer> idcheck(@RequestParam String id)   //json은 map으로 받아야 한다.
	{
		Map<String, Integer> map=new HashMap<>();
		
		int n=service.getSearchId(id);  //받아온 n값
		
		map.put("count", n); //0 또는 1이 출력되면 count로 받아오기
		
		return map;
	}
	
	//insert (일단 list로 가게 만드세요_admin이 아니면 gaipsuccess로 이동하게 할 예정_member>gaipsuccess.jsp 폴더 생성하기)
	@PostMapping("/member/insert")
	public String insert(@ModelAttribute MemberDto dto,
			MultipartFile myphoto,HttpSession session,Model model)
	{
		
		//String path=request.getSession().getServletContext().getRealPath("/membersave");
		String path=session.getServletContext().getRealPath("/membersave");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmss");
		
		String fileName=sdf.format(new Date())+myphoto.getOriginalFilename();
		
		//dto저장
		dto.setPhoto(fileName);
		
		//업로드
		try {
			myphoto.transferTo(new File(path+"\\"+fileName));
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("dto", dto);
		
		if(!dto.getId().equals("admin")) {
		
			service.insertMember(dto);
			return "/member/gaipsuccess";
		}else {
			service.insertMember(dto);
			return "redirect:list";
		}	
	}
	
	/*
	 @GetMapping("/member/delete") public String delete(String num) {
	 service.deleteMember(num);
	  
	 return "redirect:list"; }
	 */
	
	@GetMapping("/member/detail")
	public String getData(Model model,String num)
	{
		MemberDto dto=service.getData(num);
		
		model.addAttribute("dto", dto);
		
		return "/member/myinfo";
	}
	
	
	//10월30일_삭제는 ajax
	@GetMapping("/member/delete")
	@ResponseBody
	public void deleteMember(@RequestParam String num,HttpSession session)
	{
		//사진 삭제 day1031
		String path=session.getServletContext().getRealPath("/membersave");
		String photo=service.getDataByNum(num).getPhoto();
		File file=new File(path+"\\"+photo);
		file.delete();

		//day1030
		service.deleteMember(num);
	}
	
	//나의 정보에서 삭제
		@GetMapping("/member/deleteme")
		@ResponseBody  //이거는 ajax를 쓰려고 responsebody 주는 거야!
		public void deleteinfo(String num,HttpSession session)
		{
			service.deleteMember(num);
			
			String path=session.getServletContext().getRealPath("/membersave");
			String photo=service.getDataByNum(num).getPhoto();
			File file=new File(path+"\\"+photo);
			file.delete();
			
			session.removeAttribute("loginok");
			session.removeAttribute("myid");
			session.removeAttribute("loginphoto");
			session.removeAttribute("saveok");
			
		}
	
	//나의 정보에서(디테일페이지)_사진만 수정
	@PostMapping("/member/updatephoto")
	@ResponseBody
	public void photoload(String num, MultipartFile photo, HttpSession session) {
		
		//업로드 할 경로
		String path=session.getServletContext().getRealPath("/membersave");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHss");
		String fileName = sdf.format(new Date())+photo.getOriginalFilename();
		
		//업로드
		try {
			photo.transferTo(new File(path+"/"+fileName));
			
			service.updatePhoto(fileName, num);  //db 사진 수정
			
			session.setAttribute("loginphoto", fileName); //session사진 수정
			
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("/member/updatemember")
	@ResponseBody
	public void updateMember(@ModelAttribute MemberDto dto)
	{
		service.updateMember(dto);
	}
	
}
