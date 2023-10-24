package movie.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity  //JPA에서는 테이블을 Entity라고 부름
@Table(name="mycine")
@Data
public class MovieDto {
	
	@Id   //기본키 매핑 (=식별아이디:시퀀스를 기본키로 할 것)
	@GeneratedValue(strategy = GenerationType.AUTO) //기본키 전략
	private long num;   //@Id의 권장 자료형은 long이다
 	
	@Column
	private String title;
	
	@Column
	private String poster;
	
	@Column
	private String director;
	
	@Column
	private String opendate;

}
