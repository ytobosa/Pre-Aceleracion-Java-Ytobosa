package com.alfonso.alkemy.controllers;

import com.alfonso.alkemy.DTO.EntPeliculaDto;
import com.alfonso.alkemy.DTO.EntPersonajeDto;
import com.alfonso.alkemy.DTO.PeliculaDto;
import com.alfonso.alkemy.DTO.PeliculaGeneroDto;
import com.alfonso.alkemy.entity.PeliculaSerie;
import com.alfonso.alkemy.entity.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.alfonso.alkemy.interfaces.IPeliculaSerieService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController

public class PeliculaSerieController {

    @Autowired
    IPeliculaSerieService peliSerieService;

    @GetMapping("/movies")
    @ResponseStatus(HttpStatus.OK)
    public List<PeliculaDto> listPeliculas() {
        return peliSerieService.findAll();
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<?> getPelicula(@PathVariable("id") Long id) {
        EntPeliculaDto pelicula = null;
        Map<String, Object> response = new HashMap<>();
        try {
            pelicula = peliSerieService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar  la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ;
        if (pelicula == null) {
            response.put("mensaje",
                    "El id de la pelicula : ".concat(id.toString().concat(" no existe en la base de datos!!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        ;
        return new ResponseEntity(pelicula, HttpStatus.OK);
    }

    /*Guardar PeliculaSerie*/
    @PostMapping("/movies/save")
    public ResponseEntity<?> save(@Valid @RequestBody PeliculaSerie pelicula, BindingResult result) {
        EntPeliculaDto peliculaNew = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            peliculaNew = peliSerieService.save(pelicula);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La pelicula ha sido creada con exito!!");
        response.put("Pelicula", peliculaNew);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<?> deletePelicula(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            peliSerieService.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar la pelicula de la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La pelicula ha sido eliminada con exito!!");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<?> updatePelicula(@Valid @RequestBody PeliculaSerie peliculaSerie, BindingResult result,
                                            @PathVariable Long id) {
        EntPeliculaDto peliculaActual = peliSerieService.findById(id);
        PeliculaSerie peliculaUpdate = null;
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }
        if (peliculaActual == null) {
            response.put("mensaje", "Error: No se pudo editar la pelicula id: "
                    .concat(id.toString().concat(" no existe en la base de datos!!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            peliculaUpdate = peliSerieService.update(peliculaSerie);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar la pelicula en la base de datos");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "La pelicula ha sido actualizada con exito!!");
        response.put("pelicula", peliculaUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /*Consulta de Pelicula por titulo*/
    @GetMapping("/movies/title")
    @ResponseStatus(HttpStatus.OK)
    public EntPeliculaDto personajesPorTitulo(@RequestParam String title) {
        return peliSerieService.findByTitulo(title);
    }

    @GetMapping("/movies/gender")
    @ResponseStatus(HttpStatus.OK)
    public List<PeliculaGeneroDto> peliculaPorGenero(@RequestParam  Long idGenero) {
        return peliSerieService.findByGenero(idGenero);
    }

    @GetMapping("/movies/order/ASC")
    @ResponseStatus(HttpStatus.OK)
    public List<EntPeliculaDto> peliculaAsc() {
        return peliSerieService.findAllAsc();
    }

    @GetMapping("/movies/order/DESC")
    @ResponseStatus(HttpStatus.OK)
    public List<EntPeliculaDto> peliculaDesc() {
        return peliSerieService.findAllDesc();
    }
}