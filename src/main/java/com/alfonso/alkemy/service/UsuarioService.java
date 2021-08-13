package com.alfonso.alkemy.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfonso.alkemy.entity.Usuario;
import com.alfonso.alkemy.interfaces.IUsuarioService;
import com.alfonso.alkemy.repository.IUsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private IUsuarioRepository usuarioDao;

	@Autowired
	private JavaMailSender mailSender;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			logger.error("Error en el login: no existe el usuario '"+username+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el usuario '"+username+"' en el sistema!");
		}
		
		List<GrantedAuthority> authorities = usuario.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public Usuario findByEmail(String email) {
		System.out.println("Servicio" + email);
		return usuarioDao.findByEmail(email);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> listUsuarios(String rol) {
		return usuarioDao.findByRolOrderByNombreAsc(rol);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario getUsuario(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario guardarUsuario(Usuario usuario) {
		Usuario usr=usuarioDao.save(usuario);
		sendEmail(usr.getEmail(),"Bienvenido","Se registro satisfactoriamente a AppAlkemy" );
		return usr;
	}

	@Override
	@Transactional
	public Usuario updateUsuario(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

	//Pasamos por parametro: destinatario, asunto y el mensaje
	public void sendEmail(String to, String subject, String content) {

		SimpleMailMessage email = new SimpleMailMessage();

		email.setTo(to);
		email.setSubject(subject);
		email.setText(content);

		mailSender.send(email);
	}

}
