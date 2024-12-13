package com.generation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.model.PostagemModel;
import com.generation.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

	@Autowired
	private PostagemRepository postagemRepository;

	@GetMapping
	public List<PostagemModel> getAll() {
		return postagemRepository.findAll();
	}
}
