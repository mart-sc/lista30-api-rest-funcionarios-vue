package br.edu.unoesc.funcionarios.service;


import java.util.List;
import java.util.Optional;

import br.edu.unoesc.funcionarios.model.Funcionario;

public interface FuncionarioService {
	void popularTabelaInicial();

	Funcionario incluir(Funcionario funcionario);
	Funcionario alterar(Long id, Funcionario funcionario);
	void excluir(Long id);

	List<Funcionario> listar();
	
	Funcionario buscar(Long id);	  // Lança uma exceção caso o não exista o funcionario com id procurado
	List<Funcionario> buscarPorNome(String nome);
	Optional<Funcionario> porId(Long id);
}