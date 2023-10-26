package boot.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login/main")
	public String loginform()
	{
		return "/sub/login/loginform";  //Tiles.xml폴더에서 layout2 설정 이후에 여기서 /sub 붙여줌
	}
}
