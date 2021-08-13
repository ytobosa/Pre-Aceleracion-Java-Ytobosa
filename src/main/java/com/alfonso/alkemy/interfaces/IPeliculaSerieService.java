package com.alfonso.alkemy.interfaces;

import java.util.List;
import java.util.Optional;

import com.alfonso.alkemy.DTO.EntPeliculaDto;
import com.alfonso.alkemy.DTO.PeliculaDto;
import com.alfonso.alkemy.DTO.PeliculaGeneroDto;
import com.alfonso.alkemy.entity.PeliculaSerie;


public interface IPeliculaSerieService {
	
	public List<PeliculaDto> findAll();
	public EntPeliculaDto findById(Long id);
	public EntPeliculaDto save(PeliculaSerie PeliSerie);
	public PeliculaSerie update(PeliculaSerie PeliSerie);
	public void deleteById(Long id);
	public EntPeliculaDto findByTitulo(String titulo);
	public List<PeliculaGeneroDto> findByGenero(Long genero);
	public List<EntPeliculaDto> findAllAsc();
	public List<EntPeliculaDto> findAllDesc();
}
