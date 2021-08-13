package com.alfonso.alkemy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.alfonso.alkemy.entity.Usuario;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);

	public Usuario findByEmail(String email);
	
	@Query("select u from Usuario u where u.username=?1")
	public Usuario findByUsername2(String username);
	
	public List<Usuario> findByRolOrderByNombreAsc(String rol);

}
