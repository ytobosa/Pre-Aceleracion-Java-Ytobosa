package com.alfonso.alkemy.interfaces;

import java.util.List;
import java.util.Optional;

import com.alfonso.alkemy.DTO.EntPersonajeDto;

import com.alfonso.alkemy.DTO.PersonajeDto;
import com.alfonso.alkemy.entity.Personaje;

public interface IPersonajeService {

	List<PersonajeDto> findpersonajes();
	public EntPersonajeDto findById(Long id);
	public Personaje update(Personaje personaje);
	public EntPersonajeDto save(Personaje personaje);
	public void deleteById(Long id);
	public List<EntPersonajeDto> findByName(String nombre);
	public List<EntPersonajeDto> findByEdad(int edad);
	public List<EntPersonajeDto> findBypelicula(Long idPelicula);
	public List<EntPersonajeDto> findByPeso(double peso);
}
