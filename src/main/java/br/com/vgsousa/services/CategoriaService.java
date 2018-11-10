package br.com.vgsousa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.vgsousa.domain.Categoria;
import br.com.vgsousa.repositories.CategoriaRepository;
import br.com.vgsousa.services.exceptions.DataIntegrityException;
import br.com.vgsousa.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repositorio;

	
	public List<Categoria> findAll() {
		return repositorio.findAll();
	}
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repositorio.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repositorio.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		
		try {
			repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria com produtos");
		}
	}
}
