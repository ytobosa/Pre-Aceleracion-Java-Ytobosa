package com.alfonso.alkemy.repository;

import java.util.List;

import com.alfonso.alkemy.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alfonso.alkemy.entity.PeliculaSerie;


public interface IPeliculaRepository extends JpaRepository<PeliculaSerie, Long>{

    @Query(value="select pel.imagen, pel.titulo, pel.fechaCreacion from PeliculaSerie pel")
    public List<Object[]> findListaPelis();

    @Query(value="select pel from PeliculaSerie pel where pel.titulo = ?1")
    public PeliculaSerie findByTituloPeli(String nombre);

    public List<PeliculaSerie> findByGenero_id(Long idGenero);

    public List<PeliculaSerie> findAllByOrderByIdAsc();

    public List<PeliculaSerie> findAllByOrderByIdDesc();


}
