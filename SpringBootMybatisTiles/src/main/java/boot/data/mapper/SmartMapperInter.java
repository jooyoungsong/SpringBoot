package boot.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import boot.data.dto.MbanswerDto;
import boot.data.dto.SmartDto;

@Mapper
public interface SmartMapperInter {

	//smartMapperInter 처음에 있던 거 지우고 Mbanswermapper
	public void insertSshop(SmartDto dto);
	public List<MbanswerDto> getAllSangpums();
	public MbanswerDto getSdata(int num);
	public void updateSshop(SmartDto dto);
	public void deleteSshop(int idx);
	
}
