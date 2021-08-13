package com.alfonso.alkemy.DTO;
import com.alfonso.alkemy.entity.Personaje;
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
public class EntPeliculaDto implements Serializable {


        private Long id;
        private String imagen;
        private String titulo;
        private Date fechaCreacion;
        private double peso;
        private int calificacion;
        private List<EntPersonajeDto> personajes;

    }

