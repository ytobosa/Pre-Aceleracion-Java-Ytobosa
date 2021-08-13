package com.alfonso.alkemy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.alfonso.alkemy.entity.Personaje;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonajeRepository extends JpaRepository<Personaje, Long> {

	@Query(value="select per from Personaje per where per.nombre = ?1")
	public List<Personaje> findByNombrePers(String nombre);

	@Query(value="select per from Personaje per where per.peso = ?1")
	public List<Personaje> findBypeso(double peso);

	@Query(value="select per from Personaje per where per.edad = ?1")
	public List<Personaje> findbyEdad(int edad);

	public List<Personaje> findByPeliculaSerie_id(Long idPeliculaSerie);

	@Query(value="select per.imagen, per.nombre from Personaje per ")
	public List<Object[]> findListaPersonajes();
}
