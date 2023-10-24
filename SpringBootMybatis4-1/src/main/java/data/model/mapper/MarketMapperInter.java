package data.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import data.model.dto.MarketDto;

@Mapper
public interface MarketMapperInter {
	
	public int getTotalCount();  //총갯수
	public List<MarketDto> getAllSangpums();  //전체상품조회
	public void insertMarket(MarketDto dto);  //상품 삽입
	public MarketDto getData(String num);     //업데이트1
	public void updateMarket(MarketDto dto);  //업데이트2
	public void deleteMarket(String num);     //삭제
	
}
