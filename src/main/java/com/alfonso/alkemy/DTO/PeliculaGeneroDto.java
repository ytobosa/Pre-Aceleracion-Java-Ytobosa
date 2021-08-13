package com.alfonso.alkemy.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaGeneroDto {

    private Long id;
    private String imagen;
    private String titulo;
    private Date fechaCreacion;
    private double peso;
    private int calificacion;
}

