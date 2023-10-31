package boot.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import boot.data.dto.MemberDto;

@Mapper
public interface MemberMapperInter {

	public void insertMember(MemberDto dto);
	public List<MemberDto> getAllMembers();
	public int getSearchId(String id);   //아이디 존재여부 찾을 때
	public MemberDto getData(String num);  //나의 정보 _ 상세페이지
	public void deleteMember(String num);
	public String getName(String id); //로그인할 때 이름 가져오기
	public int loginPassCheck(Map<String, String> map);  //로그인할 때 비밀번호 체크하기
	public MemberDto getDataById(String id);
	public void updatePhoto(Map<String, String> map);  //나의 정보_사진만 수정
	public void updateMember(MemberDto dto); 
	public MemberDto getDataByNum(String num); //day1031 
	
}
