package boot.data.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.data.dto.MbanswerDto;
import boot.data.mapper.MbanswerMapperInter;
import boot.data.service.MemberServiece;

@RestController
@RequestMapping("/mbanswer")
public class MbanswerController {

	@Autowired
	MbanswerMapperInter mapper;
	
	@Autowired
	MemberServiece mservice;
	
	@PostMapping("/ainsert")
	public void insert(@ModelAttribute MbanswerDto dto,HttpSession session)
	{
		//세션에 로그인한 id
		String myid=(String) session.getAttribute("myid");
		//id에 대한 작성자
		String name=mservice.getName(myid);
		
		//dto에 담기
		dto.setMyid(myid);
		dto.setName(name);
		
		//insert
		mapper.insertMbanswer(dto);
	}
	
	@GetMapping("/alist")
	public List<MbanswerDto> list(String num)
	{
		
		List<MbanswerDto> list=new ArrayList<>();
		
		list=mapper.getAllAnswer(num);
		
		return list;  //실제 경로 쓰지 않아도 된다_왜? ajax를 사용하기 때문
	}
	
	@GetMapping("/adelete")
	public void delete(String idx)
	{
		System.out.println(idx);
		mapper.deleteAnswer(idx);
		
	}
	
	//수정_ content를 모달창으로 띄우기
	@GetMapping("/adata")
	public MbanswerDto getData(String idx)
	{
		
		return mapper.getAnswer(idx);
	}
	
	
	//수정
	@PostMapping("/aupdate")
	public void aupdate(@ModelAttribute MbanswerDto dto)
	{
		mapper.updateMbanswer(dto);
	}
}
