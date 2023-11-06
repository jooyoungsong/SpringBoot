package boot.data.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import boot.data.dto.ReboardDto;
import boot.data.mapper.ReboardMapperInter;

@Service
public class ReboardService implements ReboardServiceInter {

	@Autowired
	ReboardMapperInter mapperinter;
	
	@Override
	public int getMaxNum() {
		// TODO Auto-generated method stub
		return mapperinter.getMaxNum();
	}

	@Override
	public int getTotalCount(String searchcolumn, String searchword) {
		// TODO Auto-generated method stub
		Map<String, String> map=new HashMap<>();
		map.put("searchword", searchword);
		map.put("searchcolumn", searchcolumn);
		
		return mapperinter.getTotalCount(map);
	}

	@Override
	public List<ReboardDto> getPagingList(String searchcolumn, String searchword, int startnum, int perpage) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<>();
		map.put("searchcolumn", searchcolumn);
		map.put("searword", searchword);
		map.put("startnum", startnum);
		map.put("perpage", perpage);
		
		return mapperinter.getPagingList(map);
	}

	@Override
	public void insertReboard(ReboardDto dto) {
		// TODO Auto-generated method stub
		int num =dto.getNum();
		int regroup=dto.getRegroup();
		int restep=dto.getRestep();
		int relevel=dto.getRelevel();
		
		if(num==0) //새글일 경우
		{
			regroup=this.getMaxNum()+1;
			restep=0;
			relevel=0;
		}else {	//답글일 경우
			//같은 그룹(regroup) 중에서 restep보다 큰 값들은 모두 일괄적으로 +1이 된다.
			this.updateRestep(regroup, restep);
			//그리고 나서 전달받은 값보다 1크게 db에 저장
			restep++;  
			relevel++;
		}
		
		//변경된 값들을 다시 dto에 담는다
		dto.setRegroup(regroup);
		dto.setRestep(restep);
		dto.setRelevel(relevel);
		
		mapperinter.insertReboard(dto); //변경된 dto를 mapperinter에 담는다.
	}

	@Override
	public void updateRestep(int regroup, int restep) {
		// TODO Auto-generated method stub

		Map<String, Integer> map=new HashMap<>();
		
		map.put("regroup", regroup);
		map.put("restep", restep);		
		
		mapperinter.updateRestep(map);
	}

	@Override
	public void updateReadCount(int num) {
		// TODO Auto-generated method stub

		mapperinter.updateReadCount(num);
	}

	@Override
	public ReboardDto getData(int num) {
		// TODO Auto-generated method stub
		return mapperinter.getData(num);
	}

	@Override
	public void updateReboard(ReboardDto dto) {
		// TODO Auto-generated method stub
		mapperinter.updateReboard(dto);
	}

	@Override
	public void deleteReboard(int num) {
		// TODO Auto-generated method stub
		mapperinter.deleteReboard(num);
	}

	@Override
	public void updateLikes(int num) {
		// TODO Auto-generated method stub
		mapperinter.updateLikes(num);
	}

}
