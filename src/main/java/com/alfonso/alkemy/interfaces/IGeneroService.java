package com.alfonso.alkemy.interfaces;

import java.util.List;
import java.util.Optional;

import com.alfonso.alkemy.entity.Genero;
public interface IGeneroService {
	
	public List<Genero> findAll();
	public Optional<Genero> findById(Long id);
	
	
	
}
