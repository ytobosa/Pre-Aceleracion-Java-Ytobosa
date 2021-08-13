package com.alfonso.alkemy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.alfonso.alkemy.interfaces.IGeneroService;

@RestController
public class GeneroController {
	@Autowired IGeneroService serviceGenero;
	
	

}
