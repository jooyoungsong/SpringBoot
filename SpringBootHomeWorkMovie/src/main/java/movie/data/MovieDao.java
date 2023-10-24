package movie.data;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDao {		//implement를 해야하는 데 받으면 많기 때문에 필요한 것만 써준다. => @Repository

	@Autowired 
	MovieDaoInter inter;
	
	//insert
	public void insertMovie(MovieDto dto)
	{
		inter.save(dto);
	}
	
	//select (전체조회)
	public List<MovieDto> getAllData()
	{
		return inter.findAll(Sort.by(Sort.Direction.ASC,"opendate"));
	}
	
	//select (디테일페이지)
	public MovieDto getData(long num)
	{
		return inter.getReferenceById(num);
	}
}
