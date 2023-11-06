package boot.data.service;

import java.util.List;
import java.util.Map;

import boot.data.dto.ReboardDto;

public interface ReboardServiceInter {

	public int getMaxNum();
	public int getTotalCount(String searchcolumn,String searchword); //총갯수계산, 페이징처리 시 필요 + 검색어 보낼 때 여러개 보내니깐(map사용): searchcolumn,searchword 사용위함
	public List<ReboardDto> getPagingList(String searchcolumn,String searchword,int startnum,int perpage); //검색어가 있을 때는 검색페이지도 같이 필요함
	public void insertReboard(ReboardDto dto);
	public void updateRestep(int regroup,int restep); //댓글or대댓글 순서
	public void updateReadCount(int num); //조회수기능
	public ReboardDto getData(int num);
	public void updateReboard(ReboardDto dto); //수정
	public void deleteReboard(int num); //삭제
	public void updateLikes(int num); //좋아요기능
}
