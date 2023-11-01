package boot.data.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import boot.data.dto.MemBoardDto;

@Mapper
public interface MemBoardMapperInter {

	public int getTotalCount();
	public void updateReadCount(String num);
	public void insertBoard(MemBoardDto dto);
	public MemBoardDto getData(String num);
	public int getMaxNum();  //content(상세페이지)에서 필요//내가 쓴 글을 getMaxNum으로 최대값을 올리면 바로 쓰자마자 보여준다.  
	public List<MemBoardDto> getList(HashMap<String, Integer> map);  //전체 출력+페이징
	
}
