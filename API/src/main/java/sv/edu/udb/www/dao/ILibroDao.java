package sv.edu.udb.www.dao;
 
import org.springframework.data.repository.CrudRepository;
 
import sv.edu.udb.www.entity.Libro;

public interface ILibroDao extends CrudRepository<Libro, Long> {

	 
	
}
