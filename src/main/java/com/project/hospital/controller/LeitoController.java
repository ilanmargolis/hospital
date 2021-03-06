package com.project.hospital.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.hospital.dto.LeitoBaseDTO;
import com.project.hospital.mapper.LeitoMapper;
import com.project.hospital.model.Leito;
import com.project.hospital.service.LeitoService;

@RestController
@RequestMapping(path = "/leito", produces = MediaType.APPLICATION_JSON_VALUE)
public class LeitoController {
	private LeitoService leitoService;
	
	public LeitoController(LeitoService leitoService) {
		super();
		this.leitoService = leitoService;
	}
	@CrossOrigin
	@GetMapping
	public ResponseEntity<List<Leito>> getLeitos(){
		List<Leito> leitos = leitoService.findAll();
		return new ResponseEntity<>(leitos, HttpStatus.OK);
	}
	@CrossOrigin
	@GetMapping(value = "/{leito_id}")
	public ResponseEntity<Leito> getLeito(@PathVariable(value = "leito_id") Long id){
		Leito leito = leitoService.findById(id);
		if(leito == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(leito, HttpStatus.OK);
		}
	}
	@CrossOrigin
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Leito> createLeito(@RequestBody Leito leito) {
		Leito leitoSaved = leitoService.save(leito);
		if (leitoSaved == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(leitoSaved, HttpStatus.CREATED);
		}
	}
	@CrossOrigin
	@PutMapping(value = "/{leito_id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Leito> updateLeito(@PathVariable(value = "leito_id") Long id, @RequestBody Leito leito) {
		leito.setId(id);
		Leito leitoSaved = leitoService.update(leito);
		if (leitoSaved == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(leitoSaved, HttpStatus.OK);
		}
	}
	@CrossOrigin
	@DeleteMapping(value = "/{leito_id}")
	public ResponseEntity<Void> deleteLeito(@PathVariable(value = "leito_id") Long id){
		leitoService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@CrossOrigin
	@DeleteMapping
	public ResponseEntity<Void> deleteLeitos(){
		leitoService.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
