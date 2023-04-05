package br.edu.unoesc.funcionarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.unoesc.funcionarios.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	public List<Funcionario> findByNomeContainingIgnoreCase(String nome);
}