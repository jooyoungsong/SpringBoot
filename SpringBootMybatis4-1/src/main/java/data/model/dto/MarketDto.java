package data.model.dto;

import java.sql.Timestamp;

import org.apache.ibatis.type.Alias;

import lombok.Data;


@Data //롬복_setter&getter를 대신 해줌
@Alias("mdto")  //예전에 Spring에서 배웠던 mybatis에서 했던 걸 여기서 바로 할 수 있음
//Alias가 dto의 타입(=resultType,parameterType)이라고 생각하면 된다.
public class MarketDto {

	private String num;
	private int price;
	private String sang;
	private String photoname;
	private Timestamp ipgoday;
	
	
}
