package com.maynoralvarez.usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UsuarioService{
 
	@Autowired
	private UsuarioRepository uc;
	
	public void UsuarioService(UsuarioRepository uc) {
		this.uc = uc;
	}
	
	
	// listar todos los usuarios existentes
	public ResponseEntity listaUsuarios(){
		return new ResponseEntity<>(this.uc.findAll(), HttpStatus.OK);
	}
	
	// paginar listado de Usuarios
	public ResponseEntity Paginacion(Pageable pageable){
		return new ResponseEntity<>(this.uc.findAll(pageable), HttpStatus.OK);
	}
	
	
	// agregar nombre y cargo a Usuario (id auto incrementable)
	public ResponseEntity crear(@RequestBody Usuario usuario) {
		this.uc.save(usuario);
		return new ResponseEntity<>("Se ha Creado el Usuario", HttpStatus.CREATED);
	}
	
	//obtener el detalle de Usuario en base a su Id 
	public ResponseEntity detalle(@RequestParam("id") Long id) {
		Optional<Usuario> usuario = this.uc.findById(id);
		if(usuario.isPresent()) {
			return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Id ingresado no Existente",HttpStatus.CONFLICT);
		}
	}
	
	
	// eliminar usuario en base a su id
	public ResponseEntity borrar(@RequestParam("id")Long id) {
		Optional<Usuario> usuario = this.uc.findById(id);
		if(usuario.isPresent()) {
			this.uc.deleteById(id);
			return new ResponseEntity<>("Usuario Eliminado", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Id Ingresado no Existente", HttpStatus.CONFLICT);
		}
	}
	
	
	// editar nombre o cargo de un usuario (id no editable)
	public ResponseEntity actualizar(@RequestBody Usuario usuario) {
		Optional<Usuario> ac = this.uc.findById(usuario.getId());
		if(usuario.getId() != null && ac.isPresent()) {
			this.uc.save(usuario);
			return new ResponseEntity<>("El Usuario ha sido Actualizado", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Id Ingresado no Existente", HttpStatus.CONFLICT);
		}
	}
}
