package boot.data.service;

import java.util.HashMap;
import java.util.List;

import boot.data.dto.MemBoardDto;

public interface MemBoardServiceInter {

	public int getTotalCount();
	public void updateReadCount(String num);
	public void insertBoard(MemBoardDto dto);
	public MemBoardDto getData(String num);
	public int getMaxNum();  //content에서 필요//최신글이 위로 올라가기 위해 필요함
	public List<MemBoardDto> getList(int start,int perpage);
}
