package com.maynoralvarez.usuario;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/crud")
public class UsuarioController{

	UsuarioService uc;
	public UsuarioController(UsuarioService uc) {
		this.uc = uc;
	}
	
	 //metodo pageable desde UsuarioService
	@GetMapping("/paginacion")
	public ResponseEntity Paginacion(Pageable pageable) {
		return this.uc.Paginacion(pageable);
	}
	
	// llamando metodo listar de la clase UsuarioService
	@GetMapping("/listar")
	public ResponseEntity listado () {
		return this.uc.listaUsuarios();		
	}
	
	// llamando metodo crear de la clase UsuarioService
	@PostMapping("/crear")
	public ResponseEntity crear(@RequestBody Usuario usuario) {
		return this.uc.crear(usuario);
	}
	
	//pedir detalle de usuario desde UsuarioService
	@GetMapping("/detalle")
	public ResponseEntity detalle(@RequestParam ("id") Long id) {
		return this.uc.detalle(id);
	}
	
	//eleminar usario con metodo desde UsuarioService
	@DeleteMapping("/borrar")
	public ResponseEntity borrar(@RequestParam ("id") Long id) {
		return this.uc.borrar(id);
	}
	
	//editar datos de usuario llamando a UsuarioService
	@PutMapping("/actualizar")
	public ResponseEntity actualizar(@RequestBody Usuario usuario) {
		return this.uc.actualizar(usuario);
	}
	
}
