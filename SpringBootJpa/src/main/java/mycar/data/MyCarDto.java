package mycar.data;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity  //JPA에서는 테이블 만드는 전체 구성이 Entity라고 부름
@Table(name = "mycar") //자동으로 mycar라는 테이블이 mysql에 만들어 진다
@Data
public class MyCarDto {

	@Id //각엔티티 구별할 식별아이디를 갖도록 설계(시퀀스)
	@GeneratedValue(strategy = GenerationType.AUTO)  //JPA 기본키 전략 4개 중 1개를 사용함
	private long num;
	
	@Column(name = "carname")
	private String carname;
	
	@Column  //이름같으면 생략가능
	private int carprice;
	
	@Column
	private String carcolor;
	
	@Column
	private String carguip;
	
	@CreationTimestamp //엔티티가 생성되는 시점의 시간 자동등록
	@Column(updatable = false)  //수정 시 이 컬럼은 수정하지 않겠다는 뜻으로 하는 것. updatable=false
	// updatable=false 없을 시, 날짜가 null로 찍힌다.
	private Timestamp guipday;
	
	
	//사진 추가_day1023 첫 시작!
	@Column
	private String carphoto;   //이거하고 addform가서 사진 넣자!

	
}
