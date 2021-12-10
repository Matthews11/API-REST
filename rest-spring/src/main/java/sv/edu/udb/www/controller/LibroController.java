package sv.edu.udb.www.controller; 
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sv.edu.udb.www.entity.Libro;
import sv.edu.udb.www.service.ILibroService;

@RestController
@RequestMapping("/libro")
public class LibroController {

	@Autowired
	private ILibroService libroService;

	@GetMapping("/listar")
	public ResponseEntity<?> listar() {
		List<Libro> libro = libroService.listar();
		Map<String, Object> mensaje = new HashMap<>();

		if (libro.size() > 0) {
			return new ResponseEntity<List<Libro>>(libro, HttpStatus.OK);
		} else {
			mensaje.put("respuesta", "No hay registros en la base de datos");

			return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/obtener/{id}")
	public ResponseEntity<?> obtener(@PathVariable("id") Long id) {
		Libro libro = null;
		Map<String, Object> mensaje = new HashMap<>();

		try {
			libro = libroService.obtener(id);
		} catch (DataAccessException e) {
			mensaje.put("respuesta", "Error al realizar la consulta en la base de datos ");
			mensaje.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (libro == null) {
			mensaje.put("respuesta", "El ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Libro>(libro, HttpStatus.OK);
	}

	@PostMapping("/ingresar")
	public ResponseEntity<?> guardar( @RequestBody Libro libro) {
		Libro libro2 = null;
		Map<String, Object> mensaje = new HashMap<>();
		  
		try {
			libro2 = libroService.insertar(libro);
		} catch (DataAccessException e) {
			mensaje.put("respuesta", "Error al realizar la consulta en la base de datos ");
			mensaje.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		 
		mensaje.put("respuesta", "El libro ha sido creado con exito ");
		mensaje.put("libro", libro2);
		return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.CREATED);

	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<?> actualizar(@PathVariable("id") Long id, @RequestBody Libro libro) {
		Libro libroActual = libroService.obtener(id);
		Libro libroModificado = null;
		Map<String, Object> mensaje = new HashMap<>();

		if (libroActual == null) {
			mensaje.put("respuesta", "El ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.NOT_FOUND);
		}
		try {

			libroActual.setTitulo(libro.getTitulo());
			libroActual.setDescripcion(libro.getDescripcion());
			libroActual.setIdioma(libro.getIdioma());
			libroActual.setGenero(libro.getGenero());
			libroActual.setFecha(libro.getFecha());

			libroModificado = libroService.insertar(libroActual);

		} catch (DataAccessException e) {
			mensaje.put("respuesta", "Error al realizar la consulta en la base de datos ");
			mensaje.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		mensaje.put("respuesta", "El libro ha sido actualizado con exito ");
		mensaje.put("libro", libroModificado);
		return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.CREATED);
	}

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
		Map<String, Object> mensaje = new HashMap<>();
		try {
			libroService.eliminar(id);
		} catch (DataAccessException e) {
			mensaje.put("respuesta", "Error al eliminar el libro en la base de datos ");
			mensaje.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		mensaje.put("respuesta", "Libro eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.CREATED);
	}
	
	@PostMapping("/DE/{id}")
	public ResponseEntity<?> DE(@PathVariable("id") Long id) {
		Map<String, Object> mensaje = new HashMap<>();
		try {
			libroService.eliminar(id);
		} catch (DataAccessException e) {
			mensaje.put("respuesta", "Error al eliminar el libro en la base de datos ");
			mensaje.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		mensaje.put("respuesta", "Libro eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(mensaje, HttpStatus.CREATED);
	}

}
