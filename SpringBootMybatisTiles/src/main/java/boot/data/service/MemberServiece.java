package boot.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import boot.data.dto.MemberDto;
import boot.data.mapper.MemberMapperInter;

@Service
public class MemberServiece implements MemberServieceInter {

	@Autowired
	MemberMapperInter mapperInter;
	
	@Override
	public void insertMember(MemberDto dto) {
		// TODO Auto-generated method stub
		mapperInter.insertMember(dto);
	}

	@Override
	public List<MemberDto> getAllMembers() {
		// TODO Auto-generated method stub
		return mapperInter.getAllMembers();
	}

	@Override
	public int getSearchId(String id) {
		// TODO Auto-generated method stub
		return mapperInter.getSearchId(id);
	}

	@Override
	public void deleteMember(String num) {
		// TODO Auto-generated method stub
		mapperInter.deleteMember(num);
	}

	@Override
	public MemberDto getData(String num) {
		// TODO Auto-generated method stub
		return mapperInter.getData(num);
	}

}
