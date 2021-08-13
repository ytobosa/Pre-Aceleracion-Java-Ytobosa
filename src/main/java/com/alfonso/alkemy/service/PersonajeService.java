package com.alfonso.alkemy.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.alfonso.alkemy.DTO.PeliculaDto;
import com.alfonso.alkemy.entity.PeliculaSerie;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alfonso.alkemy.DTO.PersonajeDto;
import com.alfonso.alkemy.DTO.EntPersonajeDto;
import com.alfonso.alkemy.entity.Personaje;
import com.alfonso.alkemy.interfaces.IPersonajeService;
import com.alfonso.alkemy.repository.IPeliculaRepository;
import com.alfonso.alkemy.repository.IPersonajeRepository;

@Service
public class PersonajeService implements IPersonajeService {

    @Autowired
    IPersonajeRepository personajeRepo;
    @Autowired
    IPeliculaRepository peliculaRepo;
    @Autowired
    ModelMapper modelMapper;

    public List<PersonajeDto> findpersonajes() {
        List<Object[]> Pers = personajeRepo.findListaPersonajes();
        List<PersonajeDto> personajes = new ArrayList<>();
        for (Object[] objects : Pers) {
            PersonajeDto personaje = new PersonajeDto();
            personaje.setImagen((String) objects[0]);
            personaje.setNombre((String) objects[1]);
            personajes.add(personaje);
        }
        return personajes;
    }

    @Override
    public EntPersonajeDto findById(Long id) {
        Personaje personaje = personajeRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException("No existe un personaje con el id: " + id));

        List<PeliculaDto> peliculas = personaje.getPeliculaSerie().stream()
                .map(this::modelToDTOPelicula).collect(Collectors.toList());
        EntPersonajeDto personajeDto = modelToDTOPersonaje(personaje);
        personajeDto.setPeliculas(peliculas);
        return personajeDto;
    }

    @Override
    public Personaje update(Personaje personaje) {
        return personajeRepo.save(personaje);
    }

    @Override
    public EntPersonajeDto save(Personaje personaje) {
        Personaje personajeNew = personajeRepo.save(personaje);
        return modelToDTOPersonaje(personajeNew);
    }

    @Override
    public void deleteById(Long id) {
        personajeRepo.deleteById(id);
    }

    @Override
    public List<EntPersonajeDto> findByName(String nombre) {
        List<EntPersonajeDto> personajeDto=personajeRepo.findByNombrePers(nombre).stream()
                .map(this::modelToDTOPersonaje)
          .collect(Collectors.toList());
        if (personajeDto.isEmpty()) throw new NoSuchElementException(" No se encuentra personaje " +
                "con el nombre: "+ nombre);
        return personajeDto;
    }

    @Override
    public List<EntPersonajeDto> findByEdad(int edad) {
        List<EntPersonajeDto> personajeDto = personajeRepo.findbyEdad(edad).stream()
                .map(this::modelToDTOPersonaje).collect(Collectors.toList());
        if (personajeDto.isEmpty()) throw new NoSuchElementException(" No se encuentra personaje con Edad: "
                + edad);
        return personajeDto;
    }

    @Override
    public List<EntPersonajeDto> findBypelicula(Long idPelicula) {
        List<EntPersonajeDto> personajeDto = personajeRepo.findByPeliculaSerie_id(idPelicula).stream()
                .map(this::modelToDTOPersonaje).collect(Collectors.toList());
        if (personajeDto.isEmpty()) throw new NoSuchElementException(" No se encuentran personajes con el " +
                "id de pelicula: " + idPelicula);
        return personajeDto;
    }

    @Override
    public List<EntPersonajeDto> findByPeso(double peso) {
        List<EntPersonajeDto> personajeDto = personajeRepo.findBypeso(peso).stream()
                .map(this::modelToDTOPersonaje).collect(Collectors.toList());
        if (personajeDto.isEmpty()) throw new NoSuchElementException(" No se encuentran personajes con el " +
                "peso: " + peso);
        return personajeDto;
    }

    private EntPersonajeDto modelToDTOPersonaje(Personaje personaje) {
        return modelMapper.map(personaje, EntPersonajeDto.class);
    }

    private PeliculaDto modelToDTOPelicula(PeliculaSerie pelicula) {
        return modelMapper.map(pelicula, PeliculaDto.class);

    }

}

