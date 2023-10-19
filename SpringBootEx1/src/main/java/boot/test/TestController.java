package boot.test;

import java.util.List;
import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import boot.mvc.ex1.ShopDto;
import boot.mvc.ex1.TestDto;

@RestController
public class TestController {

	@GetMapping("/test")
	public TestDto hello()
	{
		TestDto dto=new TestDto();
		dto.setName("순영이");
		dto.setAddr("서울시 강남구");
		
		return dto;
	}
	
	@GetMapping("/shop/list")
	public List<ShopDto> list()
	{
		List<ShopDto> list=new Vector<>();
		
		ShopDto d1=new ShopDto();
		d1.setSang("쵸코파이");
		d1.setSu(2);
		d1.setDan(1000);
		list.add(d1);
		
		ShopDto d2=new ShopDto();
		d2.setSang("메로나");
		d2.setSu(20);
		d2.setDan(1200);
		
		list.add(d2);
		
		return list;
	}
	
	
	
}
