package boot.data.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import boot.data.dto.MemberDto;

@Mapper
public interface MemberMapperInter {

	public void insertMember(MemberDto dto);
	public List<MemberDto> getAllMembers();
	public int getSearchId(String id);   //아이디 존재여부 찾을 때
	public MemberDto getData(String num);  //나의 정보 _ 상세페이지
	public void deleteMember(String num);
}
