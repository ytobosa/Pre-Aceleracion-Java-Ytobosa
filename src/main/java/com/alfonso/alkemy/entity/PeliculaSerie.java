package com.alfonso.alkemy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "peliculaserie")
public class PeliculaSerie implements Serializable {


    /**
     *
     */
    private static final long serialVersionUID = -213183324232643649L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagen;
    private String titulo;
    private Date fechaCreacion;
    private double peso;
    private int calificacion;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genero_id")
    private Genero genero;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "personajes_peliculas", joinColumns = @JoinColumn(name = "idpelicula"),
            inverseJoinColumns = @JoinColumn(name = "idpersonaje"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"idpersonaje", "idpelicula"})})
    List<Personaje> personajes;

}
