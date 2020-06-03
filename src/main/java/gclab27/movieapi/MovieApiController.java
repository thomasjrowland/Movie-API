package gclab27.movieapi;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieApiController {
	
	@Autowired
	private MovieDao repository;
	
	@GetMapping("/movies/get-movies")
	List<Movie> all(@RequestParam(value="title", required = false) String search) {
		
		if (search != null) {
			return repository.findByTitleContaining(search);
		} else {
			return repository.findAll();
		}
		
	}
	
	@GetMapping("/movies/get-category")
	List<Movie> allByCategory(@RequestParam(value="category") String category) {
		
		return repository.findByCategory(category);
		
	}
	
	@GetMapping("/movies/get-by-id/{id}")
	Movie oneById(@PathVariable("id") Long id) {
		return repository.findById(id).orElse(null);
	}
	
	@GetMapping("/movies/random-movie")
	Movie randomPick() {
		
		List<Movie> list = repository.findAll();
		
		int min = 0;
		int max = list.size() - 1;
		
		int randNum = (int) (Math.random() * (max - min + 1) + min);
		
		Movie movie = list.get(randNum);
		
		return movie;
	}
	
	@GetMapping("/movies/random-by-category")
	Movie randomByCategory(@RequestParam(value="category") String category) {
		
		List<Movie> catList = allByCategory(category);
		
		int min = 0;
		int max = catList.size() - 1;
		
		int randNum = (int) (Math.random() * (max - min + 1) + min);
		
		Movie movie = catList.get(randNum);
		
		return movie;
	}
	
	@GetMapping("/movies/random-list")
	Set<Movie> randomList(@RequestParam(value="qty") Integer qty) {
		
		Set<Movie> randList = new HashSet<>();
		
		do {
			
			randList.add(randomPick());
			
		} while (qty > randList.size());
		
		return randList;
				
	}
	
	@GetMapping("/movies/get-categories")
	Set<String> categoryList() {
		
		List<Movie> allMovies = repository.findAll();
		
		Set<String> categories = new HashSet<>();
		
		for (Movie movie : allMovies) {
			categories.add(movie.getCategory());
		}
		
		return categories;
		
	}
	

}
