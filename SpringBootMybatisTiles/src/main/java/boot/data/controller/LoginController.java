package boot.data.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import boot.data.dto.MemberDto;
import boot.data.service.MemberServiece;

@Controller
public class LoginController {

	@Autowired
	MemberServiece service;
	
	@GetMapping("/login/main")
	public String loginform(HttpSession session, Model model)  //day1030_인자값 추가(session,model), 로그인 시 이름 넘기려고 model로 저장하기
	{
		//폼의 아이디
		String myid=(String)session.getAttribute("myid");
		//로그인 상태인지 아닌지 판단하기
		String loginok=(String)session.getAttribute("loginok");
		//한번도 실행하지 않으면 null
		if(loginok==null)
			return "/login/loginform";
		else {
			//로그인중이라면 request에 name 저장
			String name=service.getName(myid);
			model.addAttribute("name", name);
			
			return "/sub/login/logoutform";
		}
	}
	
	@PostMapping("/login/loginprocess")
	public String loginproc(@RequestParam String id, @RequestParam String pass,
			@RequestParam(required = false) String cbsave, HttpSession session)
	{
		HashMap<String, String> map=new HashMap<>();
		int check=service.loginPassCheck(id, pass);
		
		//System.out.println(check);
		
		if(check==1) {
			
			session.setMaxInactiveInterval(60*60*8); //8시간 설정 : 60*60*8
			
			session.setAttribute("myid", id); //id를 myid로 저장함
			session.setAttribute("loginok", "yes"); //yes를 loginok로 저장
			session.setAttribute("saveok", cbsave); //아이디저장 체크 누르면 cbsave로 저장인건가?
			
			MemberDto mdto=service.getDataById(id);
			
			session.setAttribute("loginphoto", mdto.getPhoto()); //photo를 세션에 저장한 것
			
			return "redirect:main";
			
		} else {
			return "/member/passfail"; //passfail폴더 생성 후 return하기/
		}
	}
	
	@GetMapping("/login/logoutprocess")
	public String logout(HttpSession session)
	{
		session.removeAttribute("loginok");
		
		return "redirect:main";
	}
	
	@GetMapping("/login/gaip")
	public String gaip() {
		return "/sub/login/registration";
	}
}
