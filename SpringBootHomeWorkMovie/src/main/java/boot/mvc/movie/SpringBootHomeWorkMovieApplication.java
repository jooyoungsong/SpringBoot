package boot.mvc.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("movie.data") //({})를 쓰는 이유는 배열처럼 여러개 쓸 때 사용한다.
@EntityScan("movie.data") //해당 패키지 안에 있는 dto를 인식해라
@EnableJpaRepositories("movie.data")   //해당 패키지 안에 있는 dao를 인식해라
public class SpringBootHomeWorkMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootHomeWorkMovieApplication.class, args);
	}

}
