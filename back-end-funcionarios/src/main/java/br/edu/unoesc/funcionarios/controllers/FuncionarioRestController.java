package br.edu.unoesc.funcionarios.controllers;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.edu.unoesc.funcionarios.model.Funcionario;
import br.edu.unoesc.funcionarios.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioRestController {
	
	@Autowired 
	private FuncionarioService servico;
	
	@GetMapping
	public ResponseEntity<Iterable<Funcionario>> listar() {
		List<Funcionario> funcionarios = servico.listar();
		
		if (funcionarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		System.out.println(funcionarios);
		return ResponseEntity.ok(funcionarios);
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Funcionario>> porNome(@RequestParam("nome") String nome) {
		List<Funcionario> funcionarios = servico.buscarPorNome(nome);
		
		if (funcionarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(funcionarios); 
	}

    @PostMapping()
    public ResponseEntity<Void> incluir(@RequestBody Funcionario funcionario) {
    	funcionario = servico.incluir(funcionario);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        									 .path("/{id}")
        									 .buildAndExpand(funcionario.getId())
        									 .toUri();
        
        System.out.println(uri.toString());
        
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> atualizar(@PathVariable("id") Long id, @RequestBody Funcionario funcionario) {
    	if (servico.porId(id).isEmpty()) {
    		return ResponseEntity.notFound().build();
    	}
    	
        return ResponseEntity.ok(servico.alterar(id, funcionario));
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
    	try {
    		servico.excluir(id);   		
    	} catch (ObjectNotFoundException e) {
    		return ResponseEntity.notFound().build();
    	}
    	
    	return ResponseEntity.noContent().build();
    }
}