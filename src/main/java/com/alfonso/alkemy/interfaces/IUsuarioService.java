package com.alfonso.alkemy.interfaces;

import java.util.List;

import com.alfonso.alkemy.entity.Usuario;

public interface IUsuarioService {

	public List<Usuario> listUsuarios(String rol);
	public Usuario getUsuario(Long id);
	public Usuario guardarUsuario(Usuario alumno);
	public Usuario updateUsuario(Usuario alumno);
	public void delete(Long id);
	public Usuario findByUsername(String username);
	public Usuario findByEmail(String email);
}
