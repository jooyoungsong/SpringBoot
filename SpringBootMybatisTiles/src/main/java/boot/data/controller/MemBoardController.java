package boot.data.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import boot.data.dto.MemBoardDto;
import boot.data.service.MemBoardService;
import boot.data.service.MemberServiece;

@Controller
@RequestMapping("/memboard")
public class MemBoardController {

	@Autowired
	MemBoardService service;
	
	@Autowired  //아이디에 따라 이름 얻어주려고 지금 이거 만듦
	MemberServiece mservice;
	
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(defaultValue = "1") int currentPage)
	{
		ModelAndView model = new ModelAndView();

		//int totalCount=service.getTotalCount();
		
		// 페이징 처리에 필요한 변수 선언
	      int totalCount = service.getTotalCount(); // 글의 전체 개수
	      int totalPage; // 총 페이지수
	      int startPage; // 각블럭에서 보여질 시작페이지
	      int endPage; // 각블럭에서 보여질 끝페이지
	      int startNum; // db에서 가져올 글의 시작번호(mysql은 첫글이 0,오라클은 1)
	      int perPage = 3; // 한페이지당 보여질 글의 갯수
	      int perBlock = 5; // 한블럭당 보여질 페이지 개수
		
	      // 총페이지수 구하기
	      // 총글의 갯수/한페이지당 보여질 개수로 나눔(7/5=1)
	      // 나머지가 1이라도 있으면 무조건 1페이지 추가(1+1=2페이지가 필요)
	      totalPage = totalCount / perPage + (totalCount % perPage == 0 ? 0 : 1);

	      // 각블럭당 보여야할 시작페이지
	      // perBlock=5일경우는 현재페이지 1~5 시작:1 끝:5
	      // 현재페이지 13 시작:11 끝:15
	      startPage = (currentPage - 1) / perBlock * perBlock + 1;

	      endPage = startPage + perBlock - 1;

	      // 총페이지가 23일경우 마지막블럭은 25가아니라 23이다
	      if (endPage > totalPage)
	         endPage = totalPage;

	      // 각페이지에서 보여질 시작번호
	      // 1페이지: 0,2페이지:5 3페이지:10....
	      startNum = (currentPage - 1) * perPage;

	      // 각페이지당 출력할 시작번호 구하기 no
	      // 총글개수가 23이면 1페이지 23,2페이지는 18,3페이지 13.....
	      // 출력시 1씩 감소하며 출력
	      int no = totalCount - (currentPage - 1) * perPage;

		
		//select
		List<MemBoardDto> list = service.getList(startNum, perPage);
		
		//model.addObject("totalCount", totalCount);
		model.addObject("list", list);
		model.addObject("totalCount", totalCount);
		model.addObject("startPage", startPage);
		model.addObject("endPage", endPage);
		model.addObject("totalPage", totalPage);
		model.addObject("no", no);
		model.addObject("currentPage", currentPage);

		
		model.setViewName("/memboard/memlist");
		
		return model;
	}
	
	@GetMapping("/form")
	public String form()
	{
		return "/memboard/addform";
	}
	
	@PostMapping("/insert") //addform의 form action="insert" 을 써준다.
	public String insert(@ModelAttribute MemBoardDto dto, HttpSession session,Model model)
	//private MultipartFile upload;를 dto에 담아줘서 여기서 multipartfile을 안 가져와도 되었던 것
	{
		
		String path=session.getServletContext().getRealPath("/savefile");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		
		//사진 업로드 할 때 이름만 바꿔주기
		//업로드 안 한경우 no
		if(dto.getUpload().getOriginalFilename().equals(""))
			dto.setUploadfile("no"); //no라고 저장하겠다
		else { //업로드 한 경우
			String uploadFile=sdf.format(new Date())+dto.getUpload().getOriginalFilename();
			dto.setUploadfile(uploadFile);
			
			//실제 업로드 하기
			try {
				dto.getUpload().transferTo(new File(path+"\\"+uploadFile));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//세션에서 아이디 얻기 위해 dto에 저장
		String myid=(String)session.getAttribute("myid");
		dto.setMyid(myid);
		
		//이름
		//이름을 서비스에서 얻는 방법과 session에서 얻는 방법 모두 가능
		String name=mservice.getName(myid);  //서비스에서 얻음
		//String name=(String)session.getAttribute("loginname"); //세션에서 얻음
		dto.setName(name);
		
		//insert 시키기
		service.insertBoard(dto);
		
		return "redirect:content?num="+service.getMaxNum();
	}
	
	@GetMapping("/content")
	public ModelAndView content(String num)
	{
		ModelAndView model=new ModelAndView();
		
		//조회수 증가
		service.updateReadCount(num);
		
		MemBoardDto dto=service.getData(num);
		model.addObject("dto", dto);		
		
		//업로드 파일의 확장자 얻기
		int dotLoc=dto.getUploadfile().lastIndexOf('.');  // 업로드 파일에서 마지막 점(.)의 위치를 잡아낸다
		String ext=dto.getUploadfile().substring(dotLoc+1); //substring이 다음 글자부터 끝 글자까지 추출해준다. (.의 다음부터 끝까지)
		
		System.out.println(dotLoc+","+ext);
		
		if(ext.equalsIgnoreCase("jpg")||ext.equalsIgnoreCase("gif")
				||ext.equalsIgnoreCase("png")||ext.equalsIgnoreCase("jpeg"))//확장자가 이미지일 것 같은 것 다 ||또는으로 해놓기
			model.addObject("bupload", true); //확장자가 위와 같으면 bupload라는 이름으로 true값을 보내도록 지정한다  => content 페이지로 가서 작성하기
		else {
			model.addObject("bupload", false); //확장자가 위와 같지 않으면 bupload라는 이름으로 false값을 보내도록 지정한다
		}
			
		model.setViewName("/memboard/content");
		
		return model;
	}
	
}
