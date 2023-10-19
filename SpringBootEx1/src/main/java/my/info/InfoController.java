package my.info;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InfoController {

	@GetMapping("/my/info")
	@ResponseBody
	public HashMap<String, String> info()
	{
		HashMap<String, String> info=new HashMap<>();
		
		info.put("name", "장순영");
		info.put("age", "23");
		info.put("addr", "서울시");
		
		return info;
	}
}
