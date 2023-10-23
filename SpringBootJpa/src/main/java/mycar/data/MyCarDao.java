package mycar.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;


@Repository
public class MyCarDao { //daointer에서 있는 걸 다 받으면 많아서 @repository 후에 필요한 것만 쓸게요. 그래서 implements MyCarDaoInter 지움

	@Autowired
	MyCarDaoInter carInter;
	
	//insert
	public void insertMyCar(MyCarDto dto)
	{
		carInter.save(dto); //insert는 여기서 save임  //save는 자체적으로 insert인지 update인지 판단해줌,대신 name의 값을 잘 줘야 함!
		//id타입 유무에 따라 자동으로 insert(없으면) or update()로 갈린다.
	}

	
	//Jpa공부
	
	//전체출력
	public List<MyCarDto> getAllDatas()
	{
		//return carInter.findAll();  //findall 전체 조회,출력을 의미함
		return carInter.findAll(Sort.by(Sort.Direction.DESC, "carprice")); //가격이 높은 순으로 분류
		//sort라는 findAll로 해보기_sort의 direction으로 하기
	}
	
	//num에 대한 값(dto) 반환
	public MyCarDto getData(Long num)
	{
		return carInter.getReferenceById(num);
	}
	
	//update_select 제외한 나머지는 다 void
	public void updateMyCar(MyCarDto dto)
	{
		carInter.save(dto);
	}
	
	//delete
	public void deleteMyCar(Long num)
	{
		carInter.deleteById(num);
	}
	
}
