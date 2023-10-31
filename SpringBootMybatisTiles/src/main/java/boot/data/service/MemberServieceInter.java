package boot.data.service;

import java.util.List;
import java.util.Map;

import boot.data.dto.MemberDto;

public interface MemberServieceInter {

	public void insertMember(MemberDto dto);
	public List<MemberDto> getAllMembers();
	public int getSearchId(String id);
	public MemberDto getData(String num);  //나의 정보 _ 상세페이지
	public void deleteMember(String num);
	public String getName(String id); //로그인할 때 이름 가져오기
	public int loginPassCheck(String id,String pass);  //로그인할 때 비밀번호 체크하기// membermapperinter 가져오는데 여기서는 map을 풀어서 쓴다. // membermapperinter는 sql 파라미터타입에 map으로 써줘야 되기에 map으로 쓰고 여기는 아님
	public MemberDto getDataById(String id);
	public void updatePhoto(String photo,String num);  //나의 정보_사진만 수정
	public void updateMember(MemberDto dto); 
	public MemberDto getDataByNum(String num); //day1031 
}
