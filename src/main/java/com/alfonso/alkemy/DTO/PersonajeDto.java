package com.alfonso.alkemy.DTO;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PersonajeDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1176301092141949598L;
	private String nombre;
	private String imagen;
	
}