package gclab27.movieapi;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDao extends JpaRepository<Movie, Long> {
	
	List<Movie> findByTitleContaining(String keyword);
	
	List<Movie> findByCategory(String category);
	
	
	
	

}
