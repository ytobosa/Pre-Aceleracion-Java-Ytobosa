package com.alfonso.alkemy.controllers;

import java.util.HashMap;
import java.util.Map;

import com.alfonso.alkemy.entity.Usuario;
import com.alfonso.alkemy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
//Se configuran los dominios permitidos, soporta una lista d dominios
//Se pueden especificar los metodos permitidos, las cabeceras
@CrossOrigin(origins= {"http://localhost:4200","*"})
public class UserController {

	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private UsuarioService usuarioService;

	@Secured({"ROLE_ADMIN"})
	@GetMapping(value="/encriptacion/{pass}")
	public ResponseEntity<?> pruebaEncripta(@PathVariable String pass) {
		String encriptado = encoder.encode(pass);
		System.out.println("Password encriptado: "+encriptado);
		Map<String, Object> response = new HashMap<>();
		response.put("mensaje", "Se ha encriptado "+encriptado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value="/auth/register")
	public ResponseEntity<?> registroUsuario(@Valid @RequestBody Usuario usuario) {
		Map<String, Object> response = new HashMap<>();
		Usuario userMail = usuarioService.findByEmail(usuario.getEmail());
		if (userMail == null) {
			String encriptado = encoder.encode(usuario.getPassword());
			usuario.setPassword(encriptado);
			usuarioService.guardarUsuario(usuario);
			usuarioService.sendEmail(usuario.getEmail(),"Registro satisfactorio",
					"Registro satisfactorio AlkemyApp");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		}
		response.put("mensaje", "El email ya se encuentra registrado");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@GetMapping("/auth/{id}")
	public ResponseEntity<?> getUsuario(@PathVariable("id") Long id) {
		Usuario usr = usuarioService.getUsuario(id);
		return new ResponseEntity(usr, HttpStatus.OK);
	}
	@GetMapping("/usr")
	public ResponseEntity<?> getMail(@RequestParam String mail) {
		System.out.println("mail" +mail);
		Usuario usr = usuarioService.findByEmail(mail);
		System.out.println(" user"+usr);
		return new ResponseEntity(usr, HttpStatus.OK);
	}
	@GetMapping("/mail")
	public void envioMail(){
		usuarioService.sendEmail("ytobosa@gmail.com","Prueba", "Prueba" );
		System.out.println("envio Mail");
	}

}
