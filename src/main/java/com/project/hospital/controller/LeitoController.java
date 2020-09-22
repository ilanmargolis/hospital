package com.project.hospital.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	private LeitoMapper mapper;

	public LeitoController(LeitoService leitoService, LeitoMapper mapper) {
		super();
		this.leitoService = leitoService;
		this.mapper = mapper;
	}

	@GetMapping
	public ResponseEntity<List<Leito>> getAllLeitos() {
		List<Leito> leitos = leitoService.findAll();

		return new ResponseEntity<>(leitos, HttpStatus.OK);
	}

	@GetMapping(value = "/{letio_id}")
	public ResponseEntity<Leito> getLeitoById(@PathVariable(value = "leito_id") Long id) {
		Leito leito = leitoService.findById(id);

		if (leito == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(leito, HttpStatus.OK);
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LeitoBaseDTO> createLeito(@RequestBody LeitoBaseDTO leitoBaseDTO) {
		Leito leito = mapper.toLeito(leitoBaseDTO);
		leito = leitoService.save(leito);
		if (leito == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(mapper.toLeitoBaseDTO(leito), HttpStatus.CREATED);
		}
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LeitoBaseDTO> updateLeito(@PathVariable(value = "id") Long id,
			@RequestBody LeitoBaseDTO leitoBaseDTO) {
		leitoBaseDTO.setId(id);

		Leito leito = leitoService.update(mapper.toLeito(leitoBaseDTO));

		if (leito == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(mapper.toLeitoBaseDTO(leito), HttpStatus.OK);
		}
	}

}