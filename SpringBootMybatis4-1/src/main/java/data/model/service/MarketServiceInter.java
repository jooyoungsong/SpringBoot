package data.model.service;

import java.util.List;

import data.model.dto.MarketDto;

public interface MarketServiceInter {

	public int getTotalCount();  //총갯수
	public List<MarketDto> getAllSangpums();  //전체상품조회
	public void insertMarket(MarketDto dto);  //상품 삽입
	public MarketDto getData(String num);     //업데이트1
	public void updateMarket(MarketDto dto);  //업데이트2
	public void deleteMarket(String num);     //삭제
}
