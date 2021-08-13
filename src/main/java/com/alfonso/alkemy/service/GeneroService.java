package com.alfonso.alkemy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.alfonso.alkemy.entity.Genero;
import com.alfonso.alkemy.interfaces.IGeneroService;
import com.alfonso.alkemy.repository.IGeneroRepository;


@Service
public class GeneroService implements IGeneroService {

@Autowired
IGeneroRepository generoRepo;

@Override
public List<Genero> findAll() {
	return generoRepo.findAll();
}

@Override
public Optional<Genero> findById(Long id) {
	return null;
}


}  
