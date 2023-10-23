package mycar.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MyCarDaoInter extends JpaRepository<MyCarDto, Long>{ //extends써주고 JpaRepository<MyCarDto, Long> 써준다.

}
