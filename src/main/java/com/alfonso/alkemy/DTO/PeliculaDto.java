package com.alfonso.alkemy.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PeliculaDto implements Serializable {
    private Long id;
    private String imagen;
    private String titulo;
    private Date fechaCreacion;

}
