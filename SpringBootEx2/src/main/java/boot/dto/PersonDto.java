package boot.dto;

import lombok.Data;

@Data  
//@Data를 적으면, lombok을 써줄 수 있다.  // @Setter와 @Getter를 한 번에 써주는 기능이 @Data이다.
//@Setter
//@Getter
public class PersonDto {

	private String name;
	private String addr;
	private String hp;
	
	//lombok 중, @Data를 사용하면 setter, getter를 생략할 수 있다.
}
