package com.alfonso.alkemy.service;

import java.util.*;
import java.util.stream.Collectors;

import com.alfonso.alkemy.DTO.*;
import com.alfonso.alkemy.entity.Personaje;
import com.alfonso.alkemy.repository.IPersonajeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfonso.alkemy.entity.PeliculaSerie;
import com.alfonso.alkemy.interfaces.IPeliculaSerieService;
import com.alfonso.alkemy.repository.IPeliculaRepository;

@Service
public class PeliculaSerieService implements IPeliculaSerieService {
    @Autowired
    IPeliculaRepository peliSerieRepo;
    @Autowired
    IPersonajeRepository personajeRepository;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<PeliculaDto> findAll() {
        List<Object[]> pelis = peliSerieRepo.findListaPelis();
        List<PeliculaDto> peliculas = new ArrayList<>();
        for (Object[] objects : pelis) {
            PeliculaDto pelicula = new PeliculaDto();
            pelicula.setImagen((String) objects[0]);
            pelicula.setTitulo((String) objects[1]);
            pelicula.setFechaCreacion((Date) objects[2]);
            peliculas.add(pelicula);
        }
        return peliculas;
    }

    @Override
    public EntPeliculaDto findById(Long id) {
        PeliculaSerie pelicula = peliSerieRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException("No existe un personaje con el id: " + id));
        List<EntPersonajeDto> personajesDto = pelicula.getPersonajes().stream()
                .map(this::modelToDTOPersonaje).collect(Collectors.toList());
        EntPeliculaDto peliculaDto = modelToDTOPelicula(pelicula);
        peliculaDto.setPersonajes(personajesDto);
        return peliculaDto;
    }


    @Override
    public EntPeliculaDto save(PeliculaSerie peliSerie) {
        PeliculaSerie pelicula = peliSerieRepo.save(peliSerie);
        return modelToDTOPelicula(pelicula) ;
    }

    @Override
    public PeliculaSerie update(PeliculaSerie PeliSerie) {
        return peliSerieRepo.save(PeliSerie);
    }

    @Override
    public void deleteById(Long id) {
        peliSerieRepo.deleteById(id);
    }

    @Override
    public EntPeliculaDto findByTitulo(String titulo) {
        PeliculaSerie pelicula =peliSerieRepo.findByTituloPeli(titulo);
        return modelToDTOPelicula(pelicula);
    }

    @Override
    public List<PeliculaGeneroDto> findByGenero(Long idGenero) {

        List<PeliculaGeneroDto> peliculaDto = peliSerieRepo.findByGenero_id(idGenero).stream()
                .map(this::modelToDTOGenero).collect(Collectors.toList());
        if (peliculaDto.isEmpty()) throw new NoSuchElementException(" No se encuentran peliculas con el " +
                "genero: " + idGenero);
        return peliculaDto;
    }

    @Override
    public List<EntPeliculaDto> findAllAsc() {
        List<EntPeliculaDto> peliculaDto = peliSerieRepo.findAllByOrderByIdAsc().stream()
                .map(this::modelToDTOPelicula).collect(Collectors.toList());
        if (peliculaDto.isEmpty()) throw new NoSuchElementException(" No hay peliculas para mostrar");
        return peliculaDto;
    }

    @Override
    public List<EntPeliculaDto> findAllDesc() {
        List<EntPeliculaDto> peliculaDto = peliSerieRepo.findAllByOrderByIdDesc().stream()
                .map(this::modelToDTOPelicula).collect(Collectors.toList());
        if (peliculaDto.isEmpty()) throw new NoSuchElementException(" No hay peliculas para mostrar");
        return peliculaDto;
    }

    private EntPeliculaDto modelToDTOPelicula(PeliculaSerie pelicula) {
        return modelMapper.map(pelicula, EntPeliculaDto.class);
    }

    private EntPersonajeDto modelToDTOPersonaje(Personaje personaje) {
        return modelMapper.map(personaje, EntPersonajeDto.class);
    }

    private PeliculaGeneroDto modelToDTOGenero(PeliculaSerie pelicula) {
        return modelMapper.map(pelicula, PeliculaGeneroDto.class);
    }
}
