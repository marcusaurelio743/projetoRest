package br.com.springboot.curso_springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_springboot.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Query(value = "select u from Usuario u where upper(trim(u.Nome)) like %?1%")
	public List<Usuario> buscarNome(String name);
		
	

}
