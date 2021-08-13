package com.alfonso.alkemy.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "genero")
public class Genero implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7855363905178659769L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String imagen;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "genero", cascade = CascadeType.ALL)
	List<PeliculaSerie> peliculas;

	
}
