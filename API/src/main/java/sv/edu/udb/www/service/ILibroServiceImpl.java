package sv.edu.udb.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sv.edu.udb.www.dao.ILibroDao;
import sv.edu.udb.www.entity.Libro;

@Service
public class ILibroServiceImpl implements ILibroService {

	@Autowired
	private ILibroDao libroDao;

	@Override
	@Transactional(readOnly = true)
	public List<Libro> listar() {

		return (List<Libro>) libroDao.findAll();
	}

	@Override
	@Transactional
	public Libro insertar(Libro libro) {

		return libroDao.save(libro);

	}

	@Override
	@Transactional(readOnly = true)
	public Libro obtener(Long id) {

		return libroDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void eliminar(Long id) {

		libroDao.deleteById(id);

	}

}
