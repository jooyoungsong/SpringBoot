package hello.boot;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

	@GetMapping("/sist/hello")
	public HashMap<String, String> hello()
	{
		HashMap<String, String> data=new HashMap<>();
		
		data.put("message", "오늘은 스프링부트 배우는날");
		
		return data;
	}
}
