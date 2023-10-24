package movie.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDaoInter extends JpaRepository<MovieDto, Long>{

	//DaoInter파일에서 extends만 해주는 이유는?
	//JPA 같은 경우 레파지토리를 상속 받으면 자동으로 인터페이스를 상속 받아 사용할 수 있기때문이다.
	//작성방법은 extends JpaRepository<Dto이름, Dto에서 Id값 준 형식>
}
