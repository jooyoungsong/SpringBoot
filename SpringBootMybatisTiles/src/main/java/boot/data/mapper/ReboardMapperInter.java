package boot.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import boot.data.dto.ReboardDto;

@Mapper
public interface ReboardMapperInter {

	public int getMaxNum();  //groupnum과 num이 겹치지 않기 위해서 getMaxNum을 구해야 함, num은 1,2,3...인데 groupnum은 1,3...등 다르게 감!
	public int getTotalCount(Map<String, String> map); //총갯수계산, 페이징처리 시 필요 + 검색어 보낼 때 여러개 보내니깐(map사용): searchcolumn,searchword 사용위함
	public List<ReboardDto> getPagingList(Map<String,Object> map); //검색단어랑 검색페이지
	public void insertReboard(ReboardDto dto);
	public void updateRestep(Map<String, Integer>map); //댓글or대댓글 순서 때문에 step주고, level은 들여쓰기 때문에
	public void updateReadCount(int num); //조회수기능
	public ReboardDto getData(int num);
	public void updateReboard(ReboardDto dto); //수정
	public void deleteReboard(int num); //삭제
	public void updateLikes(int num); //좋아요기능
}
