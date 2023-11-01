package boot.data.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Alias("memboard")
public class MemBoardDto {
	
	private String num;
	private String name;  //나중에 추가함
	private String myid;
	private String subject;
	private String content;
	private String uploadfile;
	private MultipartFile upload;  //addform에서 name과 일치해야 함
	private int readcount;
	private Timestamp writeday;
	
}
