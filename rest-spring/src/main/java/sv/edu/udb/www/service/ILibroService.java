package sv.edu.udb.www.service;

import java.util.List;

import sv.edu.udb.www.entity.Libro;
 
 
public interface ILibroService {
	
	 public List<Libro> listar();
		
		public Libro insertar(Libro libro);
		
		public Libro obtener(Long id); 
		
		public void eliminar(Long id);
		
	

}
