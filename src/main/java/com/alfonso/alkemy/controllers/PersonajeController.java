package com.alfonso.alkemy.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.alfonso.alkemy.DTO.EntPersonajeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.alfonso.alkemy.DTO.PersonajeDto;
import com.alfonso.alkemy.entity.Personaje;
import com.alfonso.alkemy.interfaces.IPeliculaSerieService;
import com.alfonso.alkemy.interfaces.IPersonajeService;

@RestController

public class PersonajeController {

	@Autowired
	IPersonajeService personajeService;
	@Autowired
	IPeliculaSerieService peliculaService;

	/*Lista de Personajes*/
	@GetMapping("/characters")
	@ResponseStatus(HttpStatus.OK)
	public List<PersonajeDto> lisPersonajes() {
		return personajeService.findpersonajes();
	}

	/*Guardar Personaje*/
	@PostMapping("/characters/save")
	public ResponseEntity<?> save(@Valid @RequestBody Personaje personaje, BindingResult result) {
		EntPersonajeDto personajeNew = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err ->  "El campo '"+ err.getField()+ "' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			personajeNew = personajeService.save(personaje);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El personaje ha sido creado con exito!!");
		response.put("Personaje", personajeNew );
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/*Eliminación de Personaje*/
	@DeleteMapping("/characters/{id}")
	public ResponseEntity<?> deletePersonaje(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			personajeService.deleteById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el personaje en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El personaje ha sido eliminado con exito!!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/*Actualización de personajes*/
	@PutMapping("/characters/{id}")
	public ResponseEntity<?> updatePersonaje(@Valid @RequestBody Personaje personaje, BindingResult result,
											 @PathVariable Long id) {
		EntPersonajeDto personajeActual = personajeService.findById(id);
		Personaje personajeUpdate = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (personajeActual == null) {
			response.put("mensaje", "Error: No se pudo editar el personaje id: "
					.concat(id.toString().concat(" no existe en la base de datos!!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			personajeUpdate = personajeService.update(personaje);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el personaje en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El Personaje ha sido actualizado con exito!!");
		response.put("personaje", personajeUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/*Detalle del Personaje*/
	@GetMapping("/characters/{id}")
	public ResponseEntity<?> getPersonaje(@PathVariable("id") Long id) {

		EntPersonajeDto personaje = null;
		Map<String, Object> response = new HashMap<>();
		try {
			personaje = personajeService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar  la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		};
		if (personaje == null) {
			response.put("mensaje",
					"El id del personaje : ".concat(id.toString().concat(" no existe en la base de datos!!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		};
		return new ResponseEntity(personaje, HttpStatus.OK);
	}

	/*Consulta de Personaje por nombre*/
	@GetMapping("/characters/name")
	@ResponseStatus(HttpStatus.OK)
	public List<EntPersonajeDto> PersonajesPorNombre(@RequestParam String name) {
		return personajeService.findByName(name);
	}

	/*Consulta de Personaje por Edad*/
	@GetMapping("/characters/age")
	@ResponseStatus(HttpStatus.OK)
	public List<EntPersonajeDto> PersonajesPorEdad(@RequestParam  int age ) {
		return personajeService.findByEdad(age);
	}

	/*Consulta de Personaje por IdPelicula*/
	@GetMapping("/characters/movies")
	@ResponseStatus(HttpStatus.OK)
	public List<EntPersonajeDto> PersonajesPorEdad(@RequestParam Long idMovie) {
		return personajeService.findBypelicula(idMovie);
	}

	/*Consulta de Personaje por Peso*/
	@GetMapping("characters/peso/{peso}")
	@ResponseStatus(HttpStatus.OK)
	public List<EntPersonajeDto> PersonajesPorEdad(@PathVariable double peso) {
		return personajeService.findByPeso(peso);
	}
}
