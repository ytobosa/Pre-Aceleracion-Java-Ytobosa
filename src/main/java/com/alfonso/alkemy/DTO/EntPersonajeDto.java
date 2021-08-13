package com.alfonso.alkemy.DTO;

import java.io.Serializable;
import java.util.List;

import com.alfonso.alkemy.entity.PeliculaSerie;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor

public class EntPersonajeDto implements Serializable {
	private Long id;
	private String imagen;
	private String nombre;
	private int edad;
	private double peso;
	private String historia;
	private List<PeliculaDto> peliculas;

}

 