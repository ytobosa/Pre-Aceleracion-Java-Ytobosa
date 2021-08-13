package com.alfonso.alkemy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personaje")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Personaje implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8709587659857046906L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String imagen;
	private String nombre;
	private int edad;
	private double peso;
	private String historia;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name="personajes_peliculas", joinColumns= @JoinColumn(name="idpersonaje"),
	inverseJoinColumns=@JoinColumn(name="idpelicula"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"idpersonaje", "idpelicula"})})
	private List<PeliculaSerie>  peliculaSerie;
}
