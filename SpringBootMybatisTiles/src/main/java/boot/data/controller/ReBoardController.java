package boot.data.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import boot.data.dto.MemBoardDto;
import boot.data.dto.ReboardDto;
import boot.data.service.ReboardService;

@Controller
@RequestMapping("/reboard")
public class ReBoardController {

	@Autowired
	ReboardService service;
	
	@GetMapping("/list")  //day1101 board/에서 reboard로 수정
	public ModelAndView list(@RequestParam(defaultValue = "1") int currentPage,
			@RequestParam(value = "searchcolumn",required = false) String sc,
			@RequestParam(value = "searchword",required = false) String sw)
	{
		ModelAndView model=new ModelAndView();
		
	
		// 페이징 처리에 필요한 변수 선언
	      int totalCount = service.getTotalCount(sc,sw); // 글의 전체 개수
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
		List<ReboardDto> list = service.getPagingList(sc,sw,startNum, perPage);
		
		//model.addObject("totalCount", totalCount);
		model.addObject("list", list);
		model.addObject("totalCount", totalCount);
		model.addObject("startPage", startPage);
		model.addObject("endPage", endPage);
		model.addObject("totalPage", totalPage);
		model.addObject("no", no);
		model.addObject("currentPage", currentPage);
		
		System.out.println("totalCount="+totalCount);
		
		model.setViewName("/reboard/boardlist");
		
		return model;
	}
	
	@GetMapping("/form")
	public String reform(@RequestParam(defaultValue = "0") int num,
			@RequestParam(defaultValue = "0") int regroup, //생성되는 게 없을 때 0으로 하고, 넘어오는 값 있으면 +1씩 더하면 된다.
			@RequestParam(defaultValue = "0") int restep,
			@RequestParam(defaultValue = "0") int relevel,
			@RequestParam(defaultValue = "1") int currentPage, //currentpage는 1페이지부터니깐 1이 defaultvalue
			Model model)
	{
		//답글일 경우에만 넘어오는 값들이다
		
		//새글일 경우는 모두 null임, 그렇기에 defaultValue만 값으로 전달
		model.addAttribute("num", num);
		model.addAttribute("regroup", regroup);
		model.addAttribute("restep", restep);
		model.addAttribute("relevel", relevel);
		model.addAttribute("currentPage", currentPage);
		
		//새글이면 "", 답글이면 원글의 제목 가져오기
		String subject="";
		if(num>0) //답글이면 num이 0보다 큼, num은 increment로 자동으로 올라가는데 새글일때는 num을 받아오지 못하니깐 처음 우리가 정했던 defaultvalue=0이
		{
			subject=service.getData(num).getSubject();
		}
		model.addAttribute("subject", subject);
		
		return "/reboard/addform";
	}
	
	@PostMapping("/insert")
	public String insertReboard(ReboardDto dto,ArrayList<MultipartFile> upload,HttpSession session,Model model)
	{
		if(session.getAttribute("loginok")!=null)
		{
		String path=session.getServletContext().getRealPath("/rephoto");
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		
		String photo="";
		
		if(upload.get(0).getOriginalFilename().equals(""))
		{
			photo=null;
		}else {
			for(MultipartFile multi:upload)
			{
				String fileName=sdf.format(new Date())+multi.getOriginalFilename();
				photo+=fileName+",";
				
				//업로드하기
				try {
					multi.transferTo(new File(path+"\\"+fileName));
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			photo=photo.substring(0, photo.length()-1);
		}
		
		//dto에 파일이름이랑 같이 저장하기
		dto.setPhoto(photo);
		
		/* addform에서 hidden으로 안 넘겼으면 해당 내용 작성해야하는데, hidden으로 넘겨서 작성하지 않아도 된다.
		 * //세션에서 아이디 얻어서 dto에 저장하기 String id=(String)session.getAttribute("id");
		 * dto.setId(id);
		 * 
		 * //세션에서 얻어온 id로부터 이름 얻어오기 String name=(String)session.getAttribute("name");
		 * dto.setName(name);
		 */
		
		
		//insert 시키기
		service.insertReboard(dto);

		return "redirect:content?num="+service.getMaxNum();		
		}else {
			return "/login/loginform";
		}
	}
	
	@GetMapping("/content")
	public String content(@RequestParam int num,String currentPage,Model model)
	{
		//조회수 증가
		service.updateReadCount(num);
		
		//dto
		ReboardDto dto=service.getData(num);
		
		model.addAttribute("dto", dto);
		model.addAttribute("currentPage", currentPage);
		
		return "/reboard/content";
	}
	
	//추천수 증가_ajax로!
	@GetMapping("/likes")
	@ResponseBody
	public Map<String, Integer> likes(int num)
	{
		service.updateLikes(num);
		int likes=service.getData(num).getLikes();
		
		Map<String, Integer> map=new HashMap<>();
		map.put("likes", likes);
		
		return map;
	}
	
	//삭제
}
