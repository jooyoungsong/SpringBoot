package boot.data.service;

import java.util.List;

import boot.data.dto.MemberDto;

public interface MemberServieceInter {

	public void insertMember(MemberDto dto);
	public List<MemberDto> getAllMembers();
	public int getSearchId(String id);
	public MemberDto getData(String num);  //나의 정보 _ 상세페이지
	public void deleteMember(String num);
}
